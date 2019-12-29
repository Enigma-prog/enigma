/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;

public class CelBodyHorizontalCoordinates {

   private double azimuth;
   private double altitude;

   public CelBodyHorizontalCoordinates(final SeFrontend seFrontend, final double jdUt,
                                       final CelBodySingleCoordinates eclCoord, final Location location,
                                       final int flags) {
      calculate(seFrontend, jdUt, eclCoord, location, flags);
   }

   private void calculate(final SeFrontend seFrontend, final double jdUt, final CelBodySingleCoordinates eclCoord,
                          final Location location, final int flags) {
      double[] result = seFrontend.getHorizontalPositionForCelBody(jdUt, eclCoord, location, flags);
      azimuth = result[0];
      altitude = result[1];   // true altitude, index 2 = apparent altitude
   }

   public double getAzimuth() {
      return azimuth;
   }

   public double getAltitude() {
      return altitude;
   }
}
