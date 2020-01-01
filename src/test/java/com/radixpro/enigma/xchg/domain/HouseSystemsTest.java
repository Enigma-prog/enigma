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

public class HouseSystemsTest {

   private HouseSystems houseSystem;
   private final String seId = "B";
   private final int id = 11;

   @Before
   public void setUp() {
      houseSystem = HouseSystems.ALCABITIUS;
   }

   @Test
   public void getSeId() {
      assertEquals(seId, houseSystem.getSeId());
   }

   @Test
   public void getId() {
      assertEquals(id, houseSystem.getId());
   }

   @Test
   public void getNrOfCusps() {
      assertEquals(12, houseSystem.getNrOfCusps());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("gen.lookup.houses.alcabitius.name", houseSystem.getRbKeyForName());
   }

   @Test
   public void getRbKeyForDescription() {
      assertEquals("gen.lookup.houses.alcabitius.description", houseSystem.getRbKeyForDescription());
   }

   @Test
   public void isCounterClockwise() {
      assertTrue(houseSystem.isCounterClockwise());
   }

   @Test
   public void isQuadrantSystem() {
      assertTrue(houseSystem.isQuadrantSystem());
   }

   @Test
   public void isCuspIsStart() {
      assertTrue(houseSystem.isCuspIsStart());
   }
}