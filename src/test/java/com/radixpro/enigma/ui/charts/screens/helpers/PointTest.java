/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest {

   private final int xValue = 330;
   private final int yValue = 220;
   private Point point;

   @Before
   public void setUp() throws Exception {
      point = new Point(xValue, yValue);
   }

   @Test
   public void getXPos() {
      assertEquals(xValue, point.getXPos());
   }

   @Test
   public void getYPos() {
      assertEquals(yValue, point.getYPos());
   }
}