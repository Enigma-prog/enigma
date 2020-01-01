/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CelestialBodiesTest {

   private CelestialBodies celBody;

   @Before
   public void setUp() {
      celBody = CelestialBodies.JUPITER;
   }

   @Test
   public void getId() {
      assertEquals(5, celBody.getId());
   }
}