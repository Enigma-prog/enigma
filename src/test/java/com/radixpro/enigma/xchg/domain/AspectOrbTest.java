/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AspectOrbTest {

   private final Aspects aspect = Aspects.OPPOSITION;
   private final int orbPercentage = 90;
   private AspectOrb aspectOrb;

   @Before
   public void setUp() {
      aspectOrb = new AspectOrb(aspect, orbPercentage);
   }

   @Test
   public void getAspect() {
      assertEquals(aspect, aspectOrb.getAspect());
   }

   @Test
   public void getOrbPercentage() {
      assertEquals(orbPercentage, aspectOrb.getOrbPercentage());
   }
}