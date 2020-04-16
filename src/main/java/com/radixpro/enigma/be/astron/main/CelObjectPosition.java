/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.CombinedFlags;
import com.radixpro.enigma.be.astron.assist.HorizontalPosition;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.CelObjectSinglePosition;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import com.radixpro.enigma.xchg.domain.Location;
import com.radixpro.enigma.xchg.domain.SeFlags;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Coordiantes for a celestial object: ecliptical, equatorial and horizontal.
 */
public class CelObjectPosition {

   @Getter
   private CelObjectSinglePosition eclipticalPosition;
   @Getter
   private CelObjectSinglePosition equatorialPosition;
   @Getter
   private HorizontalPosition horizontalPosition;
   @Getter
   private final CelestialObjects celestialBody;
   private int eclipticalFlags;
   private int equatorialFlags;
   private int horizontalFlags;

   /**
    * Constructor defines all members.
    *
    * @param seFrontend Instantiation (singleton) of SeFrontend.
    * @param jdUt       Julian Day number in UT.
    * @param celBody    The celestial body.
    * @param location   The location.
    * @param flagList   A list with fglags to be used.
    */
   public CelObjectPosition(final SeFrontend seFrontend, final double jdUt, final CelestialObjects celBody,
                            final Location location, final List<SeFlags> flagList) {
      this.celestialBody = checkNotNull(celBody);
      final List<SeFlags> localFlagList = new ArrayList<>(checkNotNull(flagList)); // need copy to prevent changing the content of flaglist.
      defineFlags(localFlagList);
      calculate(checkNotNull(seFrontend), jdUt, checkNotNull(location));
   }

   private void defineFlags(final List<SeFlags> localFlagList) {
      eclipticalFlags = (int) new CombinedFlags(localFlagList).getCombinedValue();
      localFlagList.add(SeFlags.EQUATORIAL);
      equatorialFlags = (int) new CombinedFlags(localFlagList).getCombinedValue();

      horizontalFlags = (int) SeFlags.HORIZONTAL.getSeValue();
   }

   private void calculate(final SeFrontend seFrontend, final double jdUt, final Location location) {
      eclipticalPosition = new CelObjectSinglePosition(seFrontend, jdUt, celestialBody, eclipticalFlags);
      equatorialPosition = new CelObjectSinglePosition(seFrontend, jdUt, celestialBody, equatorialFlags);
      val eclipticalCoordinates = new double[]{eclipticalPosition.getMainPosition(),
            eclipticalPosition.getDeviationPosition(), eclipticalPosition.getDistancePosition()};
      horizontalPosition = new HorizontalPosition(seFrontend, jdUt, eclipticalCoordinates, location,
            horizontalFlags);
   }

}
