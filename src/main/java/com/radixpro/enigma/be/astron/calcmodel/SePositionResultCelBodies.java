/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.calcmodel;

/**
 * Container for the result of a SE calculation for celestial bodieshouses, holds array with calculated positions and
 * a possible error Message.
 */
public class SePositionResultCelBodies {

   private final double[] allPositions;
   private final String errorMsg;

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
