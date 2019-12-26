/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.model.HouseSystemsToCalculate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseSystemsToCalculateTest {

   private HouseSystemsToCalculate houseSystem;
   private final String seId = "B";
   private final int id = 11;

   @Before
   public void setUp() {
      houseSystem = HouseSystemsToCalculate.ALCABITIUS;
   }

   @Test
   public void getSeId() {
      assertEquals(seId, houseSystem.getSeId());
   }

   @Test
   public void getId() {
      assertEquals(id, houseSystem.getId());
   }
}