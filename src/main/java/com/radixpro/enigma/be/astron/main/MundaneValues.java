/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.EquatorialPosition;
import com.radixpro.enigma.be.astron.assist.HorizontalPosition;
import com.radixpro.enigma.be.astron.assist.HousePosition;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import com.radixpro.enigma.xchg.domain.Location;
import com.radixpro.enigma.xchg.domain.SeFlags;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculated positions for houses and other mundane points.
 */
@Getter
public class MundaneValues {

   private List<HousePosition> cusps;  // values start at position 1
   private HousePosition mc;
   private HousePosition ascendant;
   private HousePosition eastpoint;
   private HousePosition vertex;
   private double armc;

   public MundaneValues(@NonNull final SeFrontend seFrontend, final double jdUt, final int flags,
                        @NonNull final Location location, @NonNull final HouseSystems system) {
      calculate(seFrontend, jdUt, flags, location, system);
   }

   private void calculate(@NonNull final SeFrontend seFrontend, final double jdUt, final int flags,
                          @NonNull final Location location, @NonNull final HouseSystems system) {
      val seIdAsInt = system.getSeId().charAt(0);
      val positions = seFrontend.getPositionsForHouses(jdUt, flags, location, seIdAsInt, system.getNrOfCusps());
      cusps = new ArrayList<>();
      for (int i = 0; i < positions.getCusps().length; i++) {
         cusps.add(constructFullPosition(seFrontend, positions.getCusps()[i], jdUt, location));
      }
      ascendant = constructFullPosition(seFrontend, positions.getAscMc()[0], jdUt, location);
      mc = constructFullPosition(seFrontend, positions.getAscMc()[1], jdUt, location);
      armc = positions.getAscMc()[2];
      vertex = constructFullPosition(seFrontend, positions.getAscMc()[3], jdUt, location);
      eastpoint = constructEastpoint(positions.getAscMc()[4]);
   }

   private HousePosition constructFullPosition(@NonNull final SeFrontend seFrontend, final double longitude,
                                               final double jdUt, @NonNull final Location location) {
      val latitude = 0.0;
      val distance = 1.0;
      val flags = (int) SeFlags.HORIZONTAL.getSeValue();
      val eclCoord = new double[]{longitude, latitude, distance};
      val equatorialPosition = new EquatorialPosition(seFrontend, longitude, jdUt);
      val horizontalPosition = new HorizontalPosition(seFrontend, jdUt, eclCoord, location, flags);
      return new HousePosition(longitude, equatorialPosition, horizontalPosition);
   }

   private HousePosition constructEastpoint(final double longitude) {
      var rightAscension = armc + 90.0;
      if (rightAscension >= 360.0) {
         rightAscension -= 360.0;
      }
      val declination = 0.0;
      val equatorialPosition = new EquatorialPosition(rightAscension, declination);
      val altitude = 0.0;
      val azimuth = 270.0;
      val horizontalPosition = new HorizontalPosition(azimuth, altitude);
      return new HousePosition(longitude, equatorialPosition, horizontalPosition);
   }

}
