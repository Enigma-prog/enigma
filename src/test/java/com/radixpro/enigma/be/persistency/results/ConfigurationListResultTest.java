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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationListResultTest {

   private final DatabaseResults databaseResult = DatabaseResults.OK;
   List<Configuration> configurationList;
   @Mock
   private Configuration configurationMock1;
   @Mock
   private Configuration configurationMock2;
   private ConfigurationListResult configurationListResult;

   @Before
   public void setUp() {
      configurationList = new ArrayList<>();
      configurationList.add(configurationMock1);
      configurationList.add(configurationMock2);
      configurationListResult = new ConfigurationListResult(configurationList, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, configurationListResult.getDatabaseResult());
   }

   @Test
   public void getConfigurations() {
      assertEquals(configurationList, configurationListResult.getConfigurations());
   }
}
