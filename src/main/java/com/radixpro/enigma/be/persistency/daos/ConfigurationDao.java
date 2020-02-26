/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.ConfigurationObjectDocumentMapper;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import lombok.NonNull;
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

   public void insert(@NonNull final Configuration config) throws DatabaseException {
      WriteResult insertResult;
      try {
         openCollectionAndDatabase();
         insertResult = collection.insert(mapper.object2Document(config));
      } catch (Exception e) {
         LOG.error("Exception when inserting configuration. " + config.toString() + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when inserting configuration.");
      } finally {
         closeCollectionAndDatabase();
      }
      if (insertResult.getAffectedCount() != 1) {
         LOG.error("Exception when inserting configuration, probably duplicate key: " + config.toString());
         throw new DatabaseException("Exception when inserting configuration, probably duplicate key.");
      }
   }

   public void update(@NonNull final Configuration config) throws DatabaseException {
      WriteResult updateResult;
      try {
         openCollectionAndDatabase();
         updateResult = collection.update(mapper.object2Document(config));
      } catch (Exception e) {
         LOG.error("Exception when updating configuration: " + config.toString() + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when updating configuration.");

      } finally {
         closeCollectionAndDatabase();
      }
      if ((updateResult.getAffectedCount() == 0)) {
         LOG.error("Exception when updating configuration, original does not exist: " + config.toString());
         throw new DatabaseException("Exception when inserting configuration, poriginal does not exist.");
      }
   }

   public void delete(@NonNull final Configuration config) throws DatabaseException {
      openCollectionAndDatabase();
      try {
         collection.remove(mapper.object2Document(config));
      } catch (Exception e) {
         LOG.error("Exception when deleting Configuration " + config.toString() + " . Original message: "
               + e.getMessage());
         throw new DatabaseException("Exception when deleting Configuration.");
      } finally {
         closeCollectionAndDatabase();
      }
   }

   public List<Configuration> read(final long id) throws DatabaseException {
      List<Configuration> configList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         Document doc = collection.find(Filters.eq("_id", id)).firstOrDefault();
         if (doc != null) configList.add(mapper.document2Object(doc));
      } catch (Exception e) {
         LOG.error("Exception when reading configuration. " + e.getMessage());
         throw new DatabaseException("Exception when reading configuration. Orignal message: " + e.getMessage());
      } finally {
         closeCollectionAndDatabase();
      }
      return configList;
   }

   public List<Configuration> search(@NonNull final String searchName) throws DatabaseException {
      List<Configuration> configList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         Document doc = collection.find(Filters.eq("name", searchName)).firstOrDefault();
         if (doc != null) configList.add(mapper.document2Object(doc));
      } catch (Exception e) {
         LOG.error("Exception when searching configuration using arg " + searchName + ": " + e.getMessage());
         throw new DatabaseException("Exception when searching for configuration.");
      } finally {
         closeCollectionAndDatabase();
      }
      return configList;
   }

   public List<Configuration> readAll() throws DatabaseException {
      List<Configuration> configList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor userDefinedCategories = collection.find();
         for (Document foundCat : userDefinedCategories) {
            configList.add(mapper.document2Object(foundCat));
         }
      } catch (Exception e) {
         LOG.error("Exception when reading all configurations. " + e.getMessage());
         throw new DatabaseException("Exception when reading all configurations.");
      } finally {
         closeCollectionAndDatabase();
      }
      return configList;
   }

   public long getMaxId() throws DatabaseException {
      long maxId = 0;
      try {
         openCollectionAndDatabase();
         final Cursor configs = collection.find(sort("_id", SortOrder.Descending).thenLimit(0, 1));
         for (Document config : configs) {
            maxId = (long) config.get("_id");
         }
      } catch (Exception e) {
         LOG.error("Exception when reading max id for configurations. " + e.getMessage());
         throw new DatabaseException("Exception when retrieving max Id for Configurations.");
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
