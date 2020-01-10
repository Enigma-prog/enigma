/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.be.persistency.results.UserDefinedCategoryResult;
import com.radixpro.enigma.be.persistency.results.UserDefinedCategoryResultList;
import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class UserDefinedCategoryDao {

   private Nitrite nitriteDb;
   private ObjectRepository<UserDefinedCategory> repository;


   public DatabaseResults insert(final UserDefinedCategory category) {
      var databaseResult = DatabaseResults.OK;
      try {
         openRepositoryAndDatabase();
         final WriteResult insertResult = repository.insert(category);
         if (insertResult.getAffectedCount() != 1) {
            databaseResult = DatabaseResults.NOT_UNIQUE;
         }
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeRepositoryAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults update(final UserDefinedCategory category) {
      var databaseResult = DatabaseResults.OK;
      try {
         openRepositoryAndDatabase();
         final WriteResult updateResult = repository.update(category);
         if ((updateResult.getAffectedCount() == 0)) {
            databaseResult = DatabaseResults.NOT_FOUND;
         }
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeRepositoryAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults delete(final UserDefinedCategory category) {
      openRepositoryAndDatabase();
      repository.remove(category);
      closeRepositoryAndDatabase();
      return DatabaseResults.OK;
   }

   public UserDefinedCategoryResult read(final int catId) {
      var databaseResult = DatabaseResults.OK;
      UserDefinedCategory cat = new UserDefinedCategory(-1, "");
      try {
         openRepositoryAndDatabase();
         cat = repository.find(eq("id", catId)).firstOrDefault();
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeRepositoryAndDatabase();
      }
      return new UserDefinedCategoryResult(cat, databaseResult);
   }

   public UserDefinedCategoryResultList readAll() {
      var databaseResult = DatabaseResults.OK;
      List<UserDefinedCategory> catList = new ArrayList<>();
      try {
         openRepositoryAndDatabase();
         final Cursor<UserDefinedCategory> userDefinedCategories = repository.find();
         for (UserDefinedCategory foundCat : userDefinedCategories) {
            catList.add(foundCat);
         }
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeRepositoryAndDatabase();
      }
      return new UserDefinedCategoryResultList(catList, databaseResult);
   }


   private void openRepositoryAndDatabase() {
      EnigmaDatabase enigmaDb = new EnigmaDatabase();
      nitriteDb = enigmaDb.openDatabase();
      repository = nitriteDb.getRepository(UserDefinedCategory.class);
   }

   private void closeRepositoryAndDatabase() {
      repository.close();
      nitriteDb.close();
   }


}
