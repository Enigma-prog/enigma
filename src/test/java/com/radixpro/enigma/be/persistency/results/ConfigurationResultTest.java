/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationResultTest {

   private final DatabaseResults databaseResult = DatabaseResults.OK;
   @Mock
   private Configuration configurationMock;
   private ConfigurationResult configurationResult;

   @Before
   public void setUp() {
      configurationResult = new ConfigurationResult(configurationMock, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, configurationResult.getDatabaseResult());
   }

   @Test
   public void getConfig() {
      assertEquals(configurationMock, configurationResult.getConfig());
   }
}
