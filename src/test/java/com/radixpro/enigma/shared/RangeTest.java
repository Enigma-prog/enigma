/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RangeTest {

   private Range range;

   @Before
   public void setUp() throws Exception {
      range = new Range(100, 200);
   }

   @Test
   public void checkValueNoChange() {
      assertEquals(150, range.checkValue(150));
   }

   @Test
   public void checkValueToLarge() {
      assertEquals(120, range.checkValue(220));
   }

   @Test
   public void checkValueToSmall() {
      assertEquals(150, range.checkValue(50));
   }

   @Test
   public void checkValueNegative() {
      assertEquals(150, range.checkValue(-50));
   }

}