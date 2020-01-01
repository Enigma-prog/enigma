/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.xchg.domain.Ayanamshas;
import com.radixpro.enigma.xchg.domain.CelestialBodies;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CalculationSettingsTest {

   private final HouseSystems houseSystem = HouseSystems.CAMPANUS;
   private final Ayanamshas ayanamsha = Ayanamshas.HUBER;
   private final boolean sidereal = false;
   private final boolean topocentric = true;
   private final boolean heliocentric = false;
   private List<CelestialBodies> celBodies;
   private CalculationSettings calculationSettings;

   @Before
   public void setUp() {
      celBodies = new ArrayList<>();
      celBodies.add(CelestialBodies.SUN);
      celBodies.add(CelestialBodies.MOON);
      calculationSettings = new CalculationSettings(celBodies, houseSystem, ayanamsha, sidereal, topocentric,
            heliocentric);
   }

   @Test
   public void getCelBodies() {
      assertEquals(celBodies, calculationSettings.getCelBodies());
   }

   @Test
   public void getHouseSystem() {
      assertEquals(houseSystem, calculationSettings.getHouseSystem());
   }

   @Test
   public void getAyanamsha() {
      assertEquals(ayanamsha, calculationSettings.getAyanamsha());
   }

   @Test
   public void isSidereal() {
      assertEquals(sidereal, calculationSettings.isSidereal());
   }

   @Test
   public void isTopocentric() {
      assertEquals(topocentric, calculationSettings.isTopocentric());
   }

   @Test
   public void isHeliocentric() {
      assertEquals(heliocentric, calculationSettings.isHeliocentric());
   }
}