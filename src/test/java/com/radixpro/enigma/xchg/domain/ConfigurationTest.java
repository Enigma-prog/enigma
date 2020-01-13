/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationTest {

   private final long id = 3L;
   private final long parentId = 1L;
   private final String name = "Some config";
   private final String description = "Description of some config.";
   @Mock
   private ConfigAstron configAstronMock;
   @Mock
   private ConfigDelin configDelinMock;
   private Configuration config;

   @Before
   public void setUp() {
      config = new Configuration(id, parentId, name, description, configAstronMock, configDelinMock);
   }

   @Test
   public void getId() {
      assertEquals(id, config.getId());
   }

   @Test
   public void getParentId() {
      assertEquals(parentId, config.getParentId());
   }

   @Test
   public void getConfigAstron() {
      assertEquals(configAstronMock, config.getConfigAstron());
   }

   @Test
   public void getConfigDelin() {
      assertEquals(configDelinMock, config.getConfigDelin());
   }

   @Test
   public void getName() {
      assertEquals(name, config.getName());
   }

   @Test
   public void getDescription() {
      assertEquals(description, config.getDescription());
   }

}