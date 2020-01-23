/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.shared.Property;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PropertyResultTest {

   private final DatabaseResults databaseResult = DatabaseResults.OK;
   @Mock
   private Property propertyMock;
   private PropertyResult result;

   @Before
   public void setUp() throws Exception {
      result = new PropertyResult(propertyMock, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, result.getDatabaseResult());
   }

   @Test
   public void getKeyValuePair() {
      assertEquals(propertyMock, result.getProperty());
   }
}