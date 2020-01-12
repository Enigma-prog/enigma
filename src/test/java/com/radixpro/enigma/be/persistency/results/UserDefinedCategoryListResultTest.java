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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserDefinedCategoryListResultTest {

   @Mock
   private UserDefinedCategory userDefinedCategoryMock1;
   @Mock
   private UserDefinedCategory userDefinedCategoryMock2;
   private List<UserDefinedCategory> categoryList;
   private final DatabaseResults databaseResult = DatabaseResults.OK;
   private UserDefinedCategoryListResult categoryResultList;

   @Before
   public void setUp() {
      categoryList = new ArrayList<>();
      categoryList.add(userDefinedCategoryMock1);
      categoryList.add(userDefinedCategoryMock2);
      categoryResultList = new UserDefinedCategoryListResult(categoryList, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, categoryResultList.getDatabaseResult());
   }

   @Test
   public void getCategories() {
      assertEquals(categoryList, categoryResultList.getCategories());
   }
}