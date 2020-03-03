/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.ChartDataObjectDocumentMapper;
import com.radixpro.enigma.xchg.domain.ChartData;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.dizitart.no2.*;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.FindOptions.sort;
import static org.dizitart.no2.filters.Filters.eq;


public class ChartDataDao {

   private static final Logger LOG = Logger.getLogger(ChartDataDao.class);
   private static final String COLLECTION_NAME = "chartdata";
   private final ChartDataObjectDocumentMapper mapper;
   private Nitrite nitriteDb;
   private NitriteCollection collection;
   private static final String ORG_MSG = " Original message: ";

   public ChartDataDao() {
      mapper = new ChartDataObjectDocumentMapper();
   }

   public void insert(@NonNull final ChartData chartData) throws DatabaseException {
      WriteResult insertResult;
      try {
         openCollectionAndDatabase();
         insertResult = collection.insert(mapper.object2Document(chartData));
      } catch (Exception e) {
         LOG.error("Exception when inserting chart: " + chartData.toString() + ORG_MSG
               + e.getMessage());
         throw new DatabaseException("Exception when inserting chart.");
      } finally {
         closeCollectionAndDatabase();
      }
      if (insertResult.getAffectedCount() != 1) {
         LOG.error("Exception when inserting chart, probably duplicate key: " + chartData.toString());
         throw new DatabaseException("Exception when inserting chart, probably duplicate key.");
      }
      nitriteDb.commit();
   }

   public void update(@NonNull final ChartData chartData) throws DatabaseException {
      WriteResult updateResult;
      try {
         openCollectionAndDatabase();
         updateResult = collection.update(mapper.object2Document(chartData));
      } catch (Exception e) {
         LOG.error("Exception when updating chart: " + chartData.toString() + ORG_MSG + e.getMessage());
         throw new DatabaseException("Exception when updating chart.");
      } finally {
         closeCollectionAndDatabase();
      }
      if ((updateResult.getAffectedCount() == 0)) {
         LOG.error("Exception when updating chart, original does not exist: " + chartData.toString());
         throw new DatabaseException("Exception when inserting chart, original does not exist.");
      }
   }

   public void delete(@NonNull final ChartData chartData) throws DatabaseException {
      openCollectionAndDatabase();
      try {
         Document chartDoc = mapper.object2Document(chartData);
         collection.remove(chartDoc);
      } catch (Exception e) {
         LOG.error("Exception when deleting chart: " + chartData.toString() + ORG_MSG + e.getMessage());
         throw new DatabaseException("Exception when deleting chart.");
      } finally {
         closeCollectionAndDatabase();
      }
   }

   public List<ChartData> read(final long chartId) throws DatabaseException {
      List<ChartData> chartDataList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         Document chartDoc = collection.find(eq("_id", chartId)).firstOrDefault();
         if (chartDoc != null) chartDataList.add(mapper.document2Object(chartDoc));
      } catch (Exception e) {
         LOG.error("Exception when reading chart using chartId :" + chartId + ORG_MSG + e.getMessage());
         throw new DatabaseException("Exception when reading chart.");
      } finally {
         closeCollectionAndDatabase();
      }
      return chartDataList;
   }

   public List<ChartData> readAll() throws DatabaseException {
      List<ChartData> chartDataList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor allChartData = collection.find();
         for (Document foundChartData : allChartData)
            chartDataList.add(mapper.document2Object(foundChartData));
      } catch (Exception e) {
         LOG.error("Exception when reading all charts. " + e.getMessage());
         throw new DatabaseException("Exception when reading all charts.");
      } finally {
         closeCollectionAndDatabase();
      }
      return chartDataList;
   }

   public List<ChartData> search(@NonNull final String searchName) throws DatabaseException {
      List<ChartData> chartDataList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         Document chartDoc = collection.find(eq("name", searchName)).firstOrDefault();
         if (chartDoc != null) chartDataList.add(mapper.document2Object(chartDoc));
      } catch (Exception e) {
         LOG.error("Exception when searching chart, using arg: " + searchName + ORG_MSG + e.getMessage());
         throw new DatabaseException("Exception when searching chart.");
      } finally {
         closeCollectionAndDatabase();
      }
      return chartDataList;
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
         LOG.error("Exception when reading max id for charts. " + e.getMessage());
         throw new DatabaseException("Exception when reading max id for charts." + ORG_MSG + e.getMessage());
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
