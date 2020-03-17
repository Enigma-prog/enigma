/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectTriangleTest {

   @Test
   public void getPointAtEndOfHypFirstQuadrant() {
      int hypothenusa = 100;
      double angle = 10.0;
      Point result = new RectTriangle(hypothenusa, angle).getPointAtEndOfHyp();
      assertEquals(17, result.getYPos());
      assertEquals(98, result.getXPos());
   }

   @Test
   public void getPointAtEndOfHypSecondQuadrant() {
      int hypothenusa = 100;
      double angle = 152.0;
      Point result = new RectTriangle(hypothenusa, angle).getPointAtEndOfHyp();
      assertEquals(47, result.getYPos());
      assertEquals(-88, result.getXPos());
   }


}