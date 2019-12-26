/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

/**
 * Container for the result of a SE calculation for celestial bodies.
 */
public class SePositionResultCelBodies {

   private final double[] allPositions;
   private final String errorMsg;


   /**
    * Container for the positions of a celestial object.
    *
    * @param allPositions array with the following values from 0..5 : main position, deviation, distance, speed of main position,
    *                     speed of deviation, speed of distance.
    * @param errorMsg     Errormessage or empty String.
    */
   public SePositionResultCelBodies(final double[] allPositions, final String errorMsg) {
      this.allPositions = allPositions;
      this.errorMsg = errorMsg;
   }

   public double[] getAllPositions() {
      return allPositions;
   }

   public String getErrorMsg() {
      return errorMsg;
   }

}
