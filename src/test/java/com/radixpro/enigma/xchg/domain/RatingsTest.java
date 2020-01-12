/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingsTest {

   private final Ratings rating = Ratings.C;

   @Test
   public void getId() {
      assertEquals(4, rating.getId());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("gen.lookup.ratings.c.name", rating.getRbKeyForName());
   }

   @Test
   public void getRbKeyForDescription() {
      assertEquals("gen.lookup.ratings.c.description", rating.getRbKeyForDescription());
   }

   @Test
   public void total() {
      assertEquals(8, Ratings.values().length);
   }

   @Test
   public void ratingForId() {
      assertEquals(Ratings.A, rating.ratingForId(2));
   }

   @Test
   public void ratingForIdNotFound() {
      assertEquals(Ratings.ZZ, rating.ratingForId(1000));
   }

}