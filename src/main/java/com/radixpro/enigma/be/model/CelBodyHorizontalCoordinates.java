/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.core.SeFrontend;

public class CelBodyHorizontalCoordinates {

   private double azimuth;
   private double altitude;

   public CelBodyHorizontalCoordinates(final SeFrontend seFrontend, final double jdEt,
                                       final CelBodySingleCoordinates eclCoord, final double geoLat,
                                       final double geoLong, final int flags) {
      calculate(seFrontend, jdEt, eclCoord, geoLat, geoLong, flags);
   }

   private void calculate(final SeFrontend seFrontend, final double jdEt, final CelBodySingleCoordinates eclCoord,
                          final double geoLat, final double geoLong, final int flags) {
      double[] result = seFrontend.getHorizontalPositionForCelBody(jdEt, eclCoord, geoLat, geoLong, flags);
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
