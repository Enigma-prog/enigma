/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AyanamshasToCalculateTest {

   private AyanamshasToCalculate ayanamsha;

   @Before
   public void setUp() {
      ayanamsha = AyanamshasToCalculate.KUGLER_2;
   }

   @Test
   public void getSeId() {
      assertEquals(10, ayanamsha.getSeId());
   }
}