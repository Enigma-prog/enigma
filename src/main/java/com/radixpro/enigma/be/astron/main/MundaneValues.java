/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.*;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import com.radixpro.enigma.xchg.domain.SeFlags;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculated positions for houses and other mundane points.
 */
public class MundaneValues {

   private List<HousePosition> cusps;  // values start at position 1
   private HousePosition mc;
   private HousePosition ascendant;
   private HousePosition eastpoint;
   private HousePosition vertex;
   private double armc;

   public MundaneValues(final SeFrontend seFrontend, final double jdUt, final int flags, final Location location,
                        final HouseSystems system) {
      calculate(seFrontend, jdUt, flags, location, system);
   }

   private void calculate(final SeFrontend seFrontend, final double jdUt, final int flags, final Location location,
                          final HouseSystems system) {
      final char seIdAsInt = system.getSeId().charAt(0);
      final SePositionResultHouses positions = seFrontend.getPositionsForHouses(jdUt, flags, location, seIdAsInt,
            system.getNrOfCusps());
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

   private HousePosition constructFullPosition(final SeFrontend seFrontend, final double longitude,
                                               final double jdUt, final Location location) {
      final double latitude = 0.0;
      final double distance = 1.0;
      final int flags = (int) SeFlags.HORIZONTAL.getSeValue();
      final double[] eclCoord = new double[]{longitude, latitude, distance};
      final EquatorialPosition equatorialPosition = new EquatorialPosition(seFrontend, longitude, jdUt);
      final HorizontalPosition horizontalPosition = new HorizontalPosition(seFrontend, jdUt, eclCoord, location, flags);
      return new HousePosition(longitude, equatorialPosition, horizontalPosition);
   }

   private HousePosition constructEastpoint(final double longitude) {
      double rightAscension = armc + 90;
      if (rightAscension >= 360.0) {
         rightAscension -= 360.0;
      }
      double declination = 0.0;
      EquatorialPosition equatorialPosition = new EquatorialPosition(rightAscension, declination);
      double altitude = 0.0;
      double azimuth = 270.0;
      HorizontalPosition horizontalPosition = new HorizontalPosition(azimuth, altitude);
      return new HousePosition(longitude, equatorialPosition, horizontalPosition);
   }

   public List<HousePosition> getCusps() {
      return cusps;
   }

   public HousePosition getMc() {
      return mc;
   }

   public HousePosition getAscendant() {
      return ascendant;
   }

   public HousePosition getEastpoint() {
      return eastpoint;
   }

   public HousePosition getVertex() {
      return vertex;
   }

   public double getArmc() {
      return armc;
   }
}
