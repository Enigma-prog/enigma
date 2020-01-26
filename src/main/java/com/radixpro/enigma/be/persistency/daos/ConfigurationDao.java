/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.ConfigurationObjectDocumentMapper;
import com.radixpro.enigma.be.persistency.results.ConfigurationListResult;
import com.radixpro.enigma.be.persistency.results.ConfigurationResult;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.xchg.domain.Configuration;
import org.apache.log4j.Logger;
import org.dizitart.no2.*;
import org.dizitart.no2.filters.Filters;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.FindOptions.sort;

public class ConfigurationDao {

   private static final Logger LOG = Logger.getLogger(ConfigurationDao.class);
   private static final String COLLECTION_NAME = "configurations";
   private final ConfigurationObjectDocumentMapper mapper;
   private Nitrite nitriteDb;
   private NitriteCollection collection;

   public ConfigurationDao() {
      mapper = new ConfigurationObjectDocumentMapper();
   }

   public DatabaseResults insert(final Configuration config) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult insertResult = collection.insert(mapper.object2Document(config));
         if (insertResult.getAffectedCount() != 1) {
            databaseResult = DatabaseResults.NOT_UNIQUE;
         }
      } catch (Exception e) {
         LOG.error("Exception when inserting configuration. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults update(final Configuration config) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult updateResult = collection.update(mapper.object2Document(config));
         if ((updateResult.getAffectedCount() == 0)) {
            databaseResult = DatabaseResults.NOT_FOUND;
         }
      } catch (Exception e) {
         LOG.error("Exception when updating configuration. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults delete(final Configuration config) {
      openCollectionAndDatabase();
      collection.remove(mapper.object2Document(config));
      closeCollectionAndDatabase();
      return DatabaseResults.OK;
   }

   public ConfigurationResult read(final long id) {
      var databaseResult = DatabaseResults.OK;
      Configuration config = null;
      try {
         openCollectionAndDatabase();
         config = mapper.document2Object(collection.find(Filters.eq("_id", id)).firstOrDefault());
      } catch (Exception e) {
         LOG.error("Exception when reading configuration. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new ConfigurationResult(config, databaseResult);
   }

   public ConfigurationListResult readAll() {
      var databaseResult = DatabaseResults.OK;
      List<Configuration> configList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor userDefinedCategories = collection.find();
         for (Document foundCat : userDefinedCategories) {
            configList.add(mapper.document2Object(foundCat));
         }
      } catch (Exception e) {
         LOG.error("Exception when reading all configurations. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new ConfigurationListResult(configList, databaseResult);
   }

   public long getMaxId() {
      long maxId = 0;
      try {
         openCollectionAndDatabase();
         final Cursor configs = collection.find(sort("_id", SortOrder.Descending).thenLimit(0, 1));
         for (Document config : configs) {
            maxId = (long) config.get("_id");
         }
      } catch (Exception e) {
         LOG.error("Exception when reading max id for configurations. " + e.getMessage());
         maxId = -1L;                                // TODO extend error handling
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
