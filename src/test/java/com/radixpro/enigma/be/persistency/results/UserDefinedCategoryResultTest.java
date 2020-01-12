/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserDefinedCategoryResultTest {

   @Mock
   private UserDefinedCategory categoryMock;
   private UserDefinedCategoryResult categoryResult;
   private final DatabaseResults databaseResult = DatabaseResults.OK;

   @Before
   public void setUp() {
      categoryResult = new UserDefinedCategoryResult(categoryMock, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, categoryResult.getDatabaseResult());
   }

   @Test
   public void getUserDefinedCategory() {
      assertEquals(categoryMock, categoryResult.getUserDefinedCategory());
   }
}