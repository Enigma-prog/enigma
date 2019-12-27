/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CelBodiesToCalculateTest {

   private CelBodiesToCalculate celBody;

   @Before
   public void setUp() {
      celBody = CelBodiesToCalculate.JUPITER;
   }

   @Test
   public void getId() {
      assertEquals(5, celBody.getId());
   }
}