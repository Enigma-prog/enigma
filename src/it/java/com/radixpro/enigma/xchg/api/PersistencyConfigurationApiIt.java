/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for persistency of Configuration.
 */
public class PersistencyConfigurationApiIt {

   private PersistedConfigurationApi api;
   private Configuration configuration1;
   private Configuration configuration2;

   @Before
   public void setUp() {
      api = new PersistedConfigurationApi();
      configuration1 = createConfiguration(1L, "one");
      configuration2 = createConfiguration(2L, "two");
      List<Configuration> configList = api.readAll();
      // clean up
      for (Configuration config : configList) {
         api.delete(config);
      }
   }

   @Test
   public void insert() {
      String result = api.insert(configuration1);
      assertEquals("OK", result);
   }

   @Test
   public void insertDuplicate() {
      String result = api.insert(configuration1);
      String result2 = api.insert(configuration1);
      assertEquals("OK", result);
      assertEquals("ERROR", result2);
   }

   @Test
   public void update() {
      api.insert(configuration1);
      var updateConfig = createConfiguration(1L, "ein");
      String result = api.update(updateConfig);
      Configuration resultConfig = api.read(1).get(0);
      assertEquals("ein", resultConfig.getName());
   }

   @Test
   public void updateNotExist() {
      api.insert(configuration1);
      var updateConfig = createConfiguration(4, "four");
      String result = api.update(updateConfig);
      assertEquals("NOTFOUND", result);
      assertEquals(1, 1);
   }

   @Test
   public void read() {
      api.insert(configuration1);
      api.insert(configuration2);
      Configuration config = api.read(1).get(0);
      assertEquals("one", config.getName());
   }

   @Test
   public void search() {
      api.insert(configuration1);
      api.insert(configuration2);
      Configuration config = api.search("one").get(0);
      assertEquals("one", config.getName());
   }

   @Test
   public void searchNotFound() {
      api.insert(configuration1);
      api.insert(configuration2);
      List configs = api.search("seven");
      assertEquals(0, configs.size());
   }


   @Test
   public void readAll() {
      api.insert(configuration1);
      api.insert(configuration2);
      List<Configuration> configList = api.readAll();
      assertEquals(2, configList.size());
   }

   @Test
   public void getMaxId() {
      assertEquals(0, api.getMaxId());
      api.insert(configuration2);
      assertEquals(2, api.getMaxId());
   }


   private Configuration createConfiguration(final long id, final String name) {
      long parentId = 0L;
      String description = "Description";
      List<ConfiguredCelObject> celObjList = new ArrayList<>();
      celObjList.add(new ConfiguredCelObject(CelestialObjects.SUN, "a", 100.0, true));
      celObjList.add(new ConfiguredCelObject(CelestialObjects.MOON, "b", 100.0, true));

      AstronConfiguration astronConfiguration = new AstronConfiguration(HouseSystems.PLACIDUS, Ayanamshas.NONE, EclipticProjections.TROPICAL,
            ObserverPositions.TOPOCENTRIC, celObjList);
      List<ConfiguredAspect> supportedAspects = new ArrayList<>();
      supportedAspects.add(new ConfiguredAspect(Aspects.CONJUNCTION, 100, "B", true));
      supportedAspects.add(new ConfiguredAspect(Aspects.OPPOSITION, 95, "C", true));
      AspectConfiguration aspectConfig = new AspectConfiguration(supportedAspects, 7.7, AspectOrbStructure.ASPECT, false);
      DelinConfiguration delinConfig = new DelinConfiguration(aspectConfig);
      return new Configuration(id, parentId, name, description, astronConfiguration, delinConfig);
   }

}
