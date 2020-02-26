/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import lombok.Getter;
import lombok.NonNull;

/**
 * Container for the result of a SE calculation for celestial bodies.
 */
@Getter
public class SePositionResultCelObjects {

   private final double[] allPositions;
   private final String errorMsg;


   /**
    * Container for the positions of a celestial object.
    *
    * @param allPositions array with the following values from 0..5 : main position, deviation, distance, speed of main position,
    *                     speed of deviation, speed of distance.
    * @param errorMsg     Errormessage or empty String.
    */
   public SePositionResultCelObjects(@NonNull final double[] allPositions, @NonNull final String errorMsg) {
      this.allPositions = allPositions;
      this.errorMsg = errorMsg;
   }


}
