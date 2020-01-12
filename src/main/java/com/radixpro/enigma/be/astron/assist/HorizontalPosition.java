/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.Location;

public class HorizontalPosition {

   private double azimuth;
   private double altitude;

   /**
    * Calculate horizontal position.
    *
    * @param seFrontend Instance of SeFrontend
    * @param jdUt       Julian day number
    * @param eclCoord   ecliptical coördinates: index 0 = longitude, 1 = latitude, 2 = distance
    * @param location   geographic longitude and latitude
    * @param flags      settings for calculation
    */
   public HorizontalPosition(final SeFrontend seFrontend, final double jdUt,
                             final double[] eclCoord, final Location location,
                             final int flags) {
      calculate(seFrontend, jdUt, eclCoord, location, flags);
   }

   public HorizontalPosition(final double azimuth, final double altitude) {
      this.azimuth = azimuth;
      this.altitude = altitude;
   }

   private void calculate(final SeFrontend seFrontend, final double jdUt, final double[] eclCoord,
                          final Location location, final int flags) {
      double[] result = seFrontend.getHorizontalPosition(jdUt, eclCoord, location, flags);
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
