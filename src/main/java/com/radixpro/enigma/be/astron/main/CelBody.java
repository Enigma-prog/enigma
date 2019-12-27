/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.*;
import com.radixpro.enigma.be.astron.core.SeFrontend;

import java.util.List;

public class CelBody {

   private CelBodySingleCoordinates eclipticalPosition;
   private CelBodySingleCoordinates equatorialPosition;
   private CelBodyHorizontalCoordinates horizontalPosition;
   private int eclipticalFlags;
   private int equatorialFlags;
   private int horizontalFlags;

   public CelBody(final SeFrontend seFrontend, final Double jdEt, final CelBodiesToCalculate celBody,
                  final Location location, final List<SeFlags> flagList) {
      defineFlags(flagList);
      calculate(seFrontend, jdEt, celBody, location);
   }

   private void defineFlags(final List<SeFlags> flagList) {
      eclipticalFlags = (int) new CombinedFlags(flagList).getCombinedValue();
      flagList.add(SeFlags.EQUATORIAL);
      equatorialFlags = (int) new CombinedFlags(flagList).getCombinedValue();
      horizontalFlags = (int) SeFlags.HORIZONTAL.getSeValue();
   }

   private void calculate(final SeFrontend seFrontend, final Double jdEt, final CelBodiesToCalculate celBody,
                          final Location location) {
      eclipticalPosition = new CelBodySingleCoordinates(seFrontend, jdEt, celBody, eclipticalFlags);
      equatorialPosition = new CelBodySingleCoordinates(seFrontend, jdEt, celBody, equatorialFlags);
      horizontalPosition = new CelBodyHorizontalCoordinates(seFrontend, jdEt, eclipticalPosition, location,
            horizontalFlags);
   }

   public CelBodySingleCoordinates getEclipticalPosition() {
      return eclipticalPosition;
   }

   public CelBodySingleCoordinates getEquatorialPosition() {
      return equatorialPosition;
   }

   public CelBodyHorizontalCoordinates getHorizontalPosition() {
      return horizontalPosition;
   }

}
