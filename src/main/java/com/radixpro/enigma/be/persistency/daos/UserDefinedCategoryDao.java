/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.UserDefinedCategoryObjectDocumentMapper;
import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.dizitart.no2.*;
import org.dizitart.no2.filters.Filters;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.FindOptions.sort;

public class UserDefinedCategoryDao {

   private static final Logger LOG = Logger.getLogger(UserDefinedCategoryDao.class);
   private Nitrite nitriteDb;

   private static final String COLLECTION_NAME = "userdefinedcategory";
   private NitriteCollection collection;
   private final UserDefinedCategoryObjectDocumentMapper mapper;

   public UserDefinedCategoryDao() {
      mapper = new UserDefinedCategoryObjectDocumentMapper();
   }

   public void insert(@NonNull final UserDefinedCategory category) throws DatabaseException {
      WriteResult insertResult;
      try {
         openCollectionAndDatabase();
         insertResult = collection.insert(mapper.object2Document(category));
      } catch (Exception e) {
         LOG.error("Exception when inserting user defined category: " + category.toString() + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when inserting UserDefinedCategory.");
      } finally {
         closeCollectionAndDatabase();
      }
      if (insertResult.getAffectedCount() != 1) {
         LOG.error("Could not insert UserDefinedCategory as it is not unique: " + category);
         throw new DatabaseException("Could not insert UserDefinedCategory as it is not unique.");
      }
   }

   public void update(@NonNull final UserDefinedCategory category) throws DatabaseException {
      WriteResult updateResult;
      try {
         openCollectionAndDatabase();
         updateResult = collection.update(mapper.object2Document(category));
      } catch (Exception e) {
         LOG.error("Exception when updating user defined category: " + category.toString() + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when updating UserDefinedCategory.");
      } finally {
         closeCollectionAndDatabase();
      }
      if ((updateResult.getAffectedCount() == 0)) {
         LOG.error("Could not update UserDefinedCategory as it does ont exist: " + category);
         throw new DatabaseException("Could not update UserDefinedCategory as it does not exist.");
      }
   }

   public void delete(@NonNull final UserDefinedCategory category) throws DatabaseException {
      openCollectionAndDatabase();
      try {
         collection.remove(mapper.object2Document(category));
      } catch (Exception e) {
         LOG.error("Exception when deleting UserDefinedCategory : " + category.toString() + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when deleting UserDefinedCategory.");
      }
      closeCollectionAndDatabase();
   }

   public List<UserDefinedCategory> read(final long catId) throws DatabaseException {
      List<UserDefinedCategory> catList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Document doc = collection.find(Filters.eq("_id", catId)).firstOrDefault();
         if (doc != null) catList.add(mapper.document2Object(doc));
      } catch (Exception e) {
         LOG.error("Exception when reading user defined category using arg: " + catId + ": " + e.getMessage());
         throw new DatabaseException("Exception when reading UserDefinedCategory.");
      } finally {
         closeCollectionAndDatabase();
      }
      return catList;
   }

   public List<UserDefinedCategory> search(@NonNull final String searchText) throws DatabaseException {
      List<UserDefinedCategory> catList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Document doc = collection.find(Filters.eq("text", searchText)).firstOrDefault();
         if (doc != null) catList.add(mapper.document2Object(doc));
      } catch (Exception e) {
         LOG.error("Exception when searching user defined category using arg: . " + searchText + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when searching UserDefinedCategory.");
      } finally {
         closeCollectionAndDatabase();
      }
      return catList;
   }

   public List<UserDefinedCategory> readAll() throws DatabaseException {
      List<UserDefinedCategory> catList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor userDefinedCategories = collection.find();
         for (Document foundCat : userDefinedCategories) {
            catList.add(mapper.document2Object(foundCat));
         }
      } catch (Exception e) {
         LOG.error("Exception when reading all user defined categories. " + e.getMessage());
         throw new DatabaseException("Exception when reading all user defined categories.");
      } finally {
         closeCollectionAndDatabase();
      }
      return catList;
   }

   public long getMaxId() throws DatabaseException {
      long maxId = 0;
      try {
         openCollectionAndDatabase();
         final Cursor cats = collection.find(sort("_id", SortOrder.Descending).thenLimit(0, 1));
         for (Document cat : cats) {
            maxId = (long) cat.get("_id");
         }
      } catch (Exception e) {
         LOG.error("Error when retrieving max id for UserDefinedCategory: " + e.getMessage());
         throw new DatabaseException("Error when retrieving max id for UserDefinedCategory.");
      } finally {
         closeCollectionAndDatabase();
      }
      return maxId;
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
