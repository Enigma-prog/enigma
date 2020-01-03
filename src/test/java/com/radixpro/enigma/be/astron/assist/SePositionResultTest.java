/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SePositionResultTest {

   private final String errorMsg = "An error occurred.";
   private final double pos1 = 1.1;
   private final double pos2 = 2.2;
   private final double pos3 = 3.3;
   private final double pos4 = 4.4;
   private final double pos5 = 5.5;
   private final double pos6 = 6.6;
   private final double delta = 0.00000001;
   private SePositionResultCelObjects sePositionResult;

   @Before
   public void setUp() {
      double[] positions = {pos1, pos2, pos3, pos4, pos5, pos6};
      sePositionResult = new SePositionResultCelObjects(positions, errorMsg);
   }

   @Test
   public void getAllPositions() {
      assertEquals(pos1, sePositionResult.getAllPositions()[0], delta);
   }

   @Test
   public void getErrorMsg() {
      assertEquals(errorMsg, sePositionResult.getErrorMsg());
   }
}