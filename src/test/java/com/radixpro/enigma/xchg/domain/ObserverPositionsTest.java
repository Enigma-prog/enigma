/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObserverPositionsTest {

   private final ObserverPositions observerPosition = ObserverPositions.HELIOCENTRIC;

   @Test
   public void getId() {
      assertEquals(3, observerPosition.getId());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("observerpositions.heliocentric", observerPosition.getRbKeyForName());
   }


   @Test
   public void getObserverPositionForId() {
      assertEquals(ObserverPositions.TOPOCENTRIC, observerPosition.getObserverPositionForId(2));
   }

   @Test
   public void getObserverPositionForIdNotFound() {
      assertEquals(ObserverPositions.UNKNOWN, observerPosition.getObserverPositionForId(1000));
   }

   @Test
   public void total() {
      assertEquals(4, ObserverPositions.values().length);
   }

}