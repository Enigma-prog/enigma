/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test for persistency of UserDefinedCategory.
 */
public class PersistencyUserDefinedCategoryApiIt {

   private PersistedUserDefinedCategoryApi api;
   private UserDefinedCategory userDefinedCategory1;
   private UserDefinedCategory userDefinedCategory2;


   @Before
   public void setUp() {
      api = new PersistedUserDefinedCategoryApi();
      userDefinedCategory1 = new UserDefinedCategory(1, "one");
      userDefinedCategory2 = new UserDefinedCategory(2, "two");
      List<UserDefinedCategory> catList = api.readAll();
      // clean up
      for (UserDefinedCategory cat : catList) {
         api.delete(cat);
      }
   }

   @Test
   public void insert() {
      try {
         api.insert(userDefinedCategory1);
         assertTrue("No exception occurred.", true);
      } catch (Exception e) {
         fail("Exception when inserting UserDefinedCategory.");
      }
   }

   @Test
   public void update() {
      api.insert(userDefinedCategory1);
      var updateCat = new UserDefinedCategory(1, "ein");
      api.update(updateCat);
      UserDefinedCategory resultCat = api.read(1).get(0);
      assertEquals("ein", resultCat.getText());
   }

   @Test
   public void read() {
      api.insert(userDefinedCategory1);
      api.insert(userDefinedCategory2);
      var cat = api.read(1).get(0);
      assertEquals("one", cat.getText());
   }

   @Test
   public void search() {
      api.insert(userDefinedCategory1);
      api.insert(userDefinedCategory2);
      UserDefinedCategory cat = api.search("two").get(0);
      assertEquals("two", cat.getText());
   }

   @Test
   public void searchNotFound() {
      api.insert(userDefinedCategory1);
      api.insert(userDefinedCategory2);
      List<UserDefinedCategory> result = api.search("five");
      assertEquals(0, result.size());
   }


   @Test
   public void readAll() {
      api.insert(userDefinedCategory1);
      api.insert(userDefinedCategory2);
      List<UserDefinedCategory> catList = api.readAll();
      assertEquals(2, catList.size());
   }

   @Test
   public void getMaxId() {
      assertEquals(0, api.getMaxId());
      api.insert(userDefinedCategory2);
      assertEquals(2, api.getMaxId());
   }

}
