/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObserverPositionsTest {

   private ObserverPositions observerPosition = ObserverPositions.HELIOCENTRIC;

   @Test
   public void getId() {
      assertEquals(3, observerPosition.getId());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("gen.lookup.observerpositions.heliocentric.name", observerPosition.getRbKeyForName());
   }

   @Test
   public void getRbKeyForDescription() {
      assertEquals("gen.lookup.observerpositions.heliocentric.description", observerPosition.getRbKeyForDescription());
   }

   @Test
   public void total() {
      assertEquals(3, ObserverPositions.values().length);
   }

}