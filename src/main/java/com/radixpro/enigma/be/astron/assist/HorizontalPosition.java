/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.Location;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Horizontal coordinates: azimuth and altitude. Converts from ecliptical coordinates to horizontal coordinates.
 */
@Getter
public class HorizontalPosition {

   private double azimuth;
   private double altitude;

   /**
    * Calculate horizontal position.
    *
    * @param seFrontend Instance (singleton) of SeFrontend.
    * @param jdUt       Julian day number for UT.
    * @param eclCoord   ecliptical coördinates: index 0 = longitude, 1 = latitude, 2 = distance.
    * @param location   geographic longitude and latitude.
    * @param flags      settings for calculation.
    */
   public HorizontalPosition(@NonNull final SeFrontend seFrontend, final double jdUt,
                             @NonNull final double[] eclCoord, @NonNull final Location location,
                             final int flags) {
      calculate(seFrontend, jdUt, eclCoord, location, flags);
   }

   /**
    * Constructor using known coordinates.
    *
    * @param azimuth  Azimuth in degrees.
    * @param altitude Altitude in degrees.
    */
   public HorizontalPosition(final double azimuth, final double altitude) {
      this.azimuth = azimuth;
      this.altitude = altitude;
   }

   private void calculate(@NonNull final SeFrontend seFrontend, final double jdUt, @NonNull final double[] eclCoord,
                          @NonNull final Location location, final int flags) {
      val result = seFrontend.getHorizontalPosition(jdUt, eclCoord, location, flags);
      azimuth = result[0];
      altitude = result[1];   // true altitude, index 2 = apparent altitude
   }
}
