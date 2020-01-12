/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConfigAstronTest {

   private ConfigAstron configAstron;

   @Before
   public void setUp() {
      List<CelestialObjects> selectedCelestialObjects = new ArrayList<>();
      selectedCelestialObjects.add(CelestialObjects.MOON);
      selectedCelestialObjects.add(CelestialObjects.SUN);
      configAstron = new ConfigAstron(HouseSystems.CAMPANUS, Ayanamshas.NONE, EclipticProjections.TROPICAL,
            ObserverPositions.TOPOCENTRIC, selectedCelestialObjects);
   }

   @Test
   public void getHouseSystem() {
      assertEquals(HouseSystems.CAMPANUS, configAstron.getHouseSystem());
   }

   @Test
   public void getAyanamsha() {
      assertEquals(Ayanamshas.NONE, configAstron.getAyanamsha());
   }

   @Test
   public void getEclipticProjection() {
      assertEquals(EclipticProjections.TROPICAL, configAstron.getEclipticProjection());
   }

   @Test
   public void getObserverPosition() {
      assertEquals(ObserverPositions.TOPOCENTRIC, configAstron.getObserverPosition());
   }

   @Test
   public void getCelestialObjects() {
      assertEquals(2, configAstron.getCelestialObjects().size());
      assertEquals(CelestialObjects.MOON, configAstron.getCelestialObjects().get(0));
   }
}