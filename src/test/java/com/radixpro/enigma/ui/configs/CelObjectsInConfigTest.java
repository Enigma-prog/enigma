/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.testsupport.RosettaPreparer;
import com.radixpro.enigma.ui.configs.helpers.CelObjectsInConfig;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import com.radixpro.enigma.xchg.domain.config.ConfiguredCelObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CelObjectsInConfigTest {

   private CelObjectsInConfig celObjectsInConfig;

   @Before
   public void setUp() throws Exception {
      new RosettaPreparer().setRosetta();
      celObjectsInConfig = new CelObjectsInConfig(Rosetta.getRosetta());
   }

   @Test
   public void constructProperties() {
      List<PresentableProperty> props = celObjectsInConfig.constructProperties(createConfiguredCelObjects());
      assertEquals("Classic bodies", props.get(0).getName());
      assertEquals("Sun", props.get(0).getValue().trim());
      assertEquals("Modern bodies", props.get(1).getName());
      assertEquals("Uranus", props.get(1).getValue().trim());
//      assertEquals("Extra Plutonians", props.get(2).getName());
//      assertEquals("Makemake", props.get(2).getValue().trim());
//      assertEquals("Asteroids", props.get(3).getName());
//      assertEquals("Ceres", props.get(3).getValue().trim());
//      assertEquals("Centaurs", props.get(4).getName());
//      assertEquals("Cheiron", props.get(4).getValue().trim());
//      assertEquals("Intersections", props.get(5).getName());
//      assertEquals("Mean Node", props.get(5).getValue().trim());
   }

   // TODO add tests for hypothetical planets
   private List<ConfiguredCelObject> createConfiguredCelObjects() {
      List<ConfiguredCelObject> configuredCelObjects = new ArrayList();
      configuredCelObjects.add(new ConfiguredCelObject(CelestialObjects.SUN, "a", 100, true));
      configuredCelObjects.add(new ConfiguredCelObject(CelestialObjects.URANUS, "b", 70, true));
      configuredCelObjects.add(new ConfiguredCelObject(CelestialObjects.CHEIRON, "c", 40, true));
//      configuredCelObjects.add(new ConfiguredCelObject(CelestialObjects.CERES, "d", 10, false));
      configuredCelObjects.add(new ConfiguredCelObject(CelestialObjects.MEAN_NODE, "e", 60, true));
//      configuredCelObjects.add(new ConfiguredCelObject(CelestialObjects.MAKEMAKE, "f", 10, false));
      return configuredCelObjects;
   }

}