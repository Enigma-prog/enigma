/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.PropertyDocumentMapper;
import com.radixpro.enigma.shared.Property;
import lombok.NonNull;
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

   public void insert(final Property pair) throws DatabaseException {
      WriteResult insertResult;
      try {
         openCollectionAndDatabase();
         insertResult = collection.insert(mapper.object2Document(pair));
      } catch (Exception e) {
         LOG.error("Exception when inserting property: " + pair.toString() + " . Original message: " + e.getMessage());
         throw new DatabaseException("Exception when inserting property");
      } finally {
         closeCollectionAndDatabase();
      }
      if (insertResult.getAffectedCount() != 1) {
         LOG.error("Could not insert Property because there is a duplicate key: " + pair.toString());
         throw new DatabaseException("Could not insert Property because there is a duplicate key.");
      }
   }

   public void update(final Property pair) throws DatabaseException {
      WriteResult updateResult;
      try {
         openCollectionAndDatabase();
         updateResult = collection.update(mapper.object2Document(pair));
      } catch (Exception e) {
         LOG.error("Exception when updating property: " + pair.toString() + " . Original message: " + e.getMessage());
         throw new DatabaseException("Exception when updating property.");
      } finally {
         closeCollectionAndDatabase();
      }
      if ((updateResult.getAffectedCount() == 0)) {
         LOG.error("Could not update Property because it does not exist: " + pair.toString());
         throw new DatabaseException("Could not update Property because it does not exist.");
      }
   }

   public void delete(final Property pair) throws DatabaseException {
      openCollectionAndDatabase();
      try {
         collection.remove(mapper.object2Document(pair));
      } catch (Exception e) {
         LOG.error("Exception when deleing property: " + pair.toString() + " . Original message: " + e.getMessage());
         throw new DatabaseException("Exception when deleing property.");
      } finally {
         closeCollectionAndDatabase();
      }
   }

   public List<Property> read(@NonNull final String key) throws DatabaseException {
      List<Property> propList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Document doc = collection.find(Filters.eq("key", key)).firstOrDefault();
         if (doc != null) propList.add(mapper.document2Object(doc));
      } catch (Exception e) {
         LOG.error("Exception when reading property using arg: " + key + ": " + e.getMessage());
         throw new DatabaseException("Exception when reading Property.");
      } finally {
         closeCollectionAndDatabase();
      }
      return propList;
   }

   public List<Property> readAll() throws DatabaseException {
      List<Property> propList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor properties = collection.find();
         for (Document foundProp : properties) {
            propList.add(mapper.document2Object(foundProp));
         }
      } catch (Exception e) {
         LOG.error("Exception when reading all properties. " + e.getMessage());
         throw new DatabaseException("Exception when reading all Porperties.");
      } finally {
         closeCollectionAndDatabase();
      }
      return propList;
   }

   public long getMaxId() throws DatabaseException {
      long maxId = 0;
      try {
         openCollectionAndDatabase();
         final Cursor props = collection.find(sort("_id", SortOrder.Descending).thenLimit(0, 1));
         for (Document prop : props) {
            maxId = (long) prop.get("_id");
         }
      } catch (Exception e) {
         LOG.error("Exception when reading max id for property. " + e.getMessage());
         throw new DatabaseException("Exception when reading max id for Properties.");
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