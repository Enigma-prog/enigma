/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.CelBodySinglePosition;
import com.radixpro.enigma.be.astron.assist.CombinedFlags;
import com.radixpro.enigma.be.astron.assist.HorizontalPosition;
import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import com.radixpro.enigma.xchg.domain.SeFlags;

import java.util.ArrayList;
import java.util.List;

public class CelBodyPosition {

   private CelBodySinglePosition eclipticalPosition;
   private CelBodySinglePosition equatorialPosition;
   private HorizontalPosition horizontalPosition;
   private int eclipticalFlags;
   private int equatorialFlags;
   private int horizontalFlags;

   public CelBodyPosition(final SeFrontend seFrontend, final Double jdUt, final CelestialObjects celBody,
                          final Location location, final List<SeFlags> flagList) {
      final List<SeFlags> localFlagList = new ArrayList<>(flagList); // need copy to prevent changing the content of flaglist.
      defineFlags(localFlagList);
      calculate(seFrontend, jdUt, celBody, location);
   }

   private void defineFlags(final List<SeFlags> localFlagList) {
      eclipticalFlags = (int) new CombinedFlags(localFlagList).getCombinedValue();
      localFlagList.add(SeFlags.EQUATORIAL);
      equatorialFlags = (int) new CombinedFlags(localFlagList).getCombinedValue();

      horizontalFlags = (int) SeFlags.HORIZONTAL.getSeValue();
   }

   private void calculate(final SeFrontend seFrontend, final Double jdUt, final CelestialObjects celBody,
                          final Location location) {
      eclipticalPosition = new CelBodySinglePosition(seFrontend, jdUt, celBody, eclipticalFlags);
      equatorialPosition = new CelBodySinglePosition(seFrontend, jdUt, celBody, equatorialFlags);
      double[] eclipticalCoordinates = new double[]{eclipticalPosition.getMainPosition(),
            eclipticalPosition.getDeviationPosition(), eclipticalPosition.getDistancePosition()};
      horizontalPosition = new HorizontalPosition(seFrontend, jdUt, eclipticalCoordinates, location,
            horizontalFlags);
   }

   public CelBodySinglePosition getEclipticalPosition() {
      return eclipticalPosition;
   }

   public CelBodySinglePosition getEquatorialPosition() {
      return equatorialPosition;
   }

   public HorizontalPosition getHorizontalPosition() {
      return horizontalPosition;
   }

}
