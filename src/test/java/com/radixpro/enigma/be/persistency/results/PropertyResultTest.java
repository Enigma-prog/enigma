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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PropertyResultTest {

   private final DatabaseResults databaseResult = DatabaseResults.OK;
   @Mock
   private Property pairMock1;
   @Mock
   private Property pairMock2;
   private List<Property> pairList;
   private PropertyResult pairResultList;

   @Before
   public void setUp() throws Exception {
      pairList = new ArrayList<>();
      pairList.add(pairMock1);
      pairList.add(pairMock2);
      pairResultList = new PropertyResult(pairList, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, pairResultList.getDatabaseResult());
   }

   @Test
   public void getKeyValuePairs() {
      assertEquals(pairList, pairResultList.getProperties());
   }
}