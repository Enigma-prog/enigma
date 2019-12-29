/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.main.EquatorialPosition;

/**
 * Dto for all positions for a house-cusp, or other mundane point.
 */
public class FullPositionResultHouses {

   private final EquatorialPosition equatorialPosition;
   private final double longitude;

   public FullPositionResultHouses(final double longitude, final EquatorialPosition equatorialPosition) {
      this.longitude = longitude;
      this.equatorialPosition = equatorialPosition;
   }

   public EquatorialPosition getEquatorialPosition() {
      return equatorialPosition;
   }

   public double getLongitude() {
      return longitude;
   }
}
