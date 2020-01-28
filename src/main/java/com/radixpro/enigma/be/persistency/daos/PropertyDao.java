/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.PropertyDocumentMapper;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.be.persistency.results.PropertyResult;
import com.radixpro.enigma.shared.Property;
import org.apache.log4j.Logger;
import org.dizitart.no2.*;
import org.dizitart.no2.filters.Filters;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.FindOptions.sort;

public class PropertyDao {

   private static final Logger LOG = Logger.getLogger(PropertyDao.class);
   private static final String COLLECTION_NAME = "property";
   private final PropertyDocumentMapper mapper;
   private Nitrite nitriteDb;
   private NitriteCollection collection;

   public PropertyDao() {
      mapper = new PropertyDocumentMapper();
   }

   public DatabaseResults insert(final Property pair) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult insertResult = collection.insert(mapper.object2Document(pair));
         if (insertResult.getAffectedCount() != 1) {
            databaseResult = DatabaseResults.NOT_UNIQUE;
         }
      } catch (Exception e) {
         LOG.error("Exception when inserting property. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults update(final Property pair) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult updateResult = collection.update(mapper.object2Document(pair));
         if ((updateResult.getAffectedCount() == 0)) {
            databaseResult = DatabaseResults.NOT_FOUND;
         }
      } catch (Exception e) {
         LOG.error("Exception when updating property. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults delete(final Property pair) {
      openCollectionAndDatabase();
      collection.remove(mapper.object2Document(pair));
      closeCollectionAndDatabase();
      return DatabaseResults.OK;
   }

   public PropertyResult read(final String key) {
      var databaseResult = DatabaseResults.OK;
      List<Property> propList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Document doc = collection.find(Filters.eq("key", key)).firstOrDefault();
         if (doc != null) propList.add(mapper.document2Object(doc));
      } catch (Exception e) {
         LOG.error("Exception when reading property. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new PropertyResult(propList, databaseResult);
   }

   public PropertyResult readAll() {
      var databaseResult = DatabaseResults.OK;
      List<Property> propList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor properties = collection.find();
         for (Document foundProp : properties) {
            propList.add(mapper.document2Object(foundProp));
         }
      } catch (Exception e) {
         LOG.error("Exception when reading all properties. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new PropertyResult(propList, databaseResult);
   }

   public long getMaxId() {
      long maxId = 0;
      try {
         openCollectionAndDatabase();
         final Cursor props = collection.find(sort("_id", SortOrder.Descending).thenLimit(0, 1));
         for (Document prop : props) {
            maxId = (long) prop.get("_id");
         }
      } catch (Exception e) {
         LOG.error("Exception when reading max id for property. " + e.getMessage());
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