/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
      String result = api.insert(userDefinedCategory1);
      assertEquals("OK", result);
   }

   @Test
   public void insertDuplicate() {
      String result = api.insert(userDefinedCategory1);
      String result2 = api.insert(userDefinedCategory1);
      assertEquals("OK", result);
      assertEquals("ERROR", result2);
   }

   @Test
   public void update() {
      api.insert(userDefinedCategory1);
      var updateCat = new UserDefinedCategory(1, "ein");
      String result = api.update(updateCat);
      UserDefinedCategory resultCat = api.read(1);
      assertEquals("ein", resultCat.getText());
   }

   @Test
   public void updateNotExist() {
      api.insert(userDefinedCategory1);
      var updateCat = new UserDefinedCategory(4, "four");
      String result = api.update(updateCat);
      assertEquals("NOTFOUND", result);
      assertEquals(1, 1);
   }

   @Test
   public void read() {
      api.insert(userDefinedCategory1);
      api.insert(userDefinedCategory2);
      UserDefinedCategory cat = api.read(1);
      assertEquals("one", cat.getText());
   }

   @Test
   public void readAll() {
      api.insert(userDefinedCategory1);
      api.insert(userDefinedCategory2);
      List<UserDefinedCategory> catList = api.readAll();
      assertEquals(2, catList.size());
   }

}
