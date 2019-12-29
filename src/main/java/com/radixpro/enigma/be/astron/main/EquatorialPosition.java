/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.core.SeFrontend;

/**
 * Equatorial position: right ascendsiona nd declination.
 * Converts from ecliptic coördinates to equatorial coördinates. Can only be used for house positions as a zero latitude
 * is assumed. Ther eis no need for conversion of planetary positions as they already contain equatorial values.
 */
public class EquatorialPosition {

   private double rightAscension;
   private double declination;

   public EquatorialPosition(final SeFrontend seFrontend, final double longitude, final double jdUt) {
      calculatePositions(seFrontend, longitude, jdUt);
   }

   private void calculatePositions(final SeFrontend seFrontend, final double longitude, final double jdUt) {
      final double latitude = 0.0;
      final double distance = 1.0;
      final double[] eclipticPositions = {longitude, latitude, distance};
      final double obliquity = new Obliquity(seFrontend, jdUt).getTrueObliquity();
      double[] equatorialPositions = seFrontend.convertToEquatorial(eclipticPositions, obliquity);
      rightAscension = equatorialPositions[0];
      declination = equatorialPositions[1];
   }

   public double getRightAscension() {
      return rightAscension;
   }

   public double getDeclination() {
      return declination;
   }
}

