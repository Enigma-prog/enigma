/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import lombok.Getter;
import lombok.NonNull;

/**
 * Dto for all positions for a house-cusp, or other mundane point.
 */
@Getter
public class HousePosition {

   private final EquatorialPosition equatorialPosition;
   private final HorizontalPosition horizontalPosition;
   private final double longitude;


   public HousePosition(final double longitude, @NonNull final EquatorialPosition equatorialPosition,
                        @NonNull final HorizontalPosition horizontalPosition) {
      this.longitude = longitude;
      this.equatorialPosition = equatorialPosition;
      this.horizontalPosition = horizontalPosition;
   }

}
