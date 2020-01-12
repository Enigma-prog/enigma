/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.be.persistency.results.UserDefinedCategoryListResult;
import com.radixpro.enigma.be.persistency.results.UserDefinedCategoryResult;
import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.dizitart.no2.*;
import org.dizitart.no2.filters.Filters;

import java.util.ArrayList;
import java.util.List;

public class UserDefinedCategoryDao {

   private Nitrite nitriteDb;

   private static final String COLLECTION_NAME = "userdefinedcategory";
   private NitriteCollection collection;

   public DatabaseResults insert(final UserDefinedCategory category) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult insertResult = collection.insert(category2Document(category));
         if (insertResult.getAffectedCount() != 1) {
            databaseResult = DatabaseResults.NOT_UNIQUE;
         }
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults update(final UserDefinedCategory category) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult updateResult = collection.update(category2Document(category));
         if ((updateResult.getAffectedCount() == 0)) {
            databaseResult = DatabaseResults.NOT_FOUND;
         }
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults delete(final UserDefinedCategory category) {
      openCollectionAndDatabase();
      collection.remove(category2Document(category));
      closeCollectionAndDatabase();
      return DatabaseResults.OK;
   }

   public UserDefinedCategoryResult read(final long catId) {
      var databaseResult = DatabaseResults.OK;
      UserDefinedCategory cat = new UserDefinedCategory(-1L, "");
      try {
         openCollectionAndDatabase();
         cat = document2Category(collection.find(Filters.eq("_id", catId)).firstOrDefault());
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new UserDefinedCategoryResult(cat, databaseResult);
   }

   public UserDefinedCategoryListResult readAll() {
      var databaseResult = DatabaseResults.OK;
      List<UserDefinedCategory> catList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor userDefinedCategories = collection.find();
         for (Document foundCat : userDefinedCategories) {
            catList.add(document2Category(foundCat));
         }
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new UserDefinedCategoryListResult(catList, databaseResult);
   }

   private Document category2Document(final UserDefinedCategory category) {
      return Document.createDocument("_id", category.getId()).put("text", category.getText());
   }

   private UserDefinedCategory document2Category(final Document document) {
      long id = (long) document.get("_id");
      var text = (String) document.get("text");
      return new UserDefinedCategory(id, text);
   }

   private void openCollectionAndDatabase() {
      EnigmaDatabase enigmaDb = new EnigmaDatabase();
      nitriteDb = enigmaDb.openDatabase();
      collection = nitriteDb.getCollection(COLLECTION_NAME);
   }

   private void closeCollectionAndDatabase() {
      collection.close();
      nitriteDb.close();
   }


}
