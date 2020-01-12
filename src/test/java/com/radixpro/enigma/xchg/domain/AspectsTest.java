/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AspectsTest {

   private static final double MARGIN = 0.00000001;
   private Aspects aspect;

   @Before
   public void setUp() throws Exception {
      aspect = Aspects.OPPOSITION;
   }

   @Test
   public void getId() {
      assertEquals(2, aspect.getId());
   }

   @Test
   public void getAngle() {
      assertEquals(180.0, aspect.getAngle(), MARGIN);
   }

   @Test
   public void getImportance() {
      assertEquals(1, aspect.getImportance());
   }

   @Test
   public void isEcliptical() {
      assertTrue(aspect.isEcliptical());
   }

   @Test
   public void getFullRbId() {
      assertEquals("gen.lookup.aspects.opposition", aspect.getFullRbId());
   }
}