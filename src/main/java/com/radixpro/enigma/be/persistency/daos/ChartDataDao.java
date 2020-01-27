/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.mappers.ChartDataObjectDocumentMapper;
import com.radixpro.enigma.be.persistency.results.ChartDataListResult;
import com.radixpro.enigma.be.persistency.results.ChartDataResult;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.xchg.domain.ChartData;
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

   public ChartDataDao() {
      mapper = new ChartDataObjectDocumentMapper();
   }

   public DatabaseResults insert(final ChartData chartData) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult insertResult = collection.insert(mapper.object2Document(chartData));
         if (insertResult.getAffectedCount() != 1) {
            databaseResult = DatabaseResults.NOT_UNIQUE;
         }
      } catch (Exception e) {
         LOG.error("Exception when inserting chart. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      nitriteDb.commit();
      return databaseResult;
   }

   public DatabaseResults update(final ChartData chartData) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult updateResult = collection.update(mapper.object2Document(chartData));
         if ((updateResult.getAffectedCount() == 0)) {
            databaseResult = DatabaseResults.NOT_FOUND;
         }
      } catch (Exception e) {
         LOG.error("Exception when updating chart. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return databaseResult;
   }

   public DatabaseResults delete(final ChartData chartData) {
      openCollectionAndDatabase();
      Document chartDoc = mapper.object2Document(chartData);
      collection.remove(chartDoc);
      closeCollectionAndDatabase();
      return DatabaseResults.OK;
   }

   public ChartDataResult read(final long chartId) {
      var databaseResult = DatabaseResults.OK;
      ChartData chartData = null;
      try {
         openCollectionAndDatabase();
         Document chartDoc = collection.find(eq("_id", chartId)).firstOrDefault();
         if (chartDoc != null) chartData = mapper.document2Object(chartDoc);
         else databaseResult = DatabaseResults.NOT_FOUND;
      } catch (Exception e) {
         LOG.error("Exception when reading chart. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return new ChartDataResult(chartData, databaseResult);
   }

   public ChartDataListResult readAll() {
      var databaseResult = DatabaseResults.OK;
      List<ChartData> chartDataList = new ArrayList<>();
      try {
         openCollectionAndDatabase();
         final Cursor allChartData = collection.find();
         for (Document foundChartData : allChartData)
            chartDataList.add(mapper.document2Object(foundChartData));
      } catch (Exception e) {
         LOG.error("Exception when reading all charts. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return new ChartDataListResult(chartDataList, databaseResult);
   }

   public ChartDataResult search(final String searchName) {
      var databaseResult = DatabaseResults.OK;
      ChartData chartData = null;
      try {
         openCollectionAndDatabase();
         Document chartDoc = collection.find(eq("name", searchName)).firstOrDefault();
         if (chartDoc != null) chartData = mapper.document2Object(chartDoc);
         else databaseResult = DatabaseResults.NOT_FOUND;
      } catch (Exception e) {
         LOG.error("Exception when searching chart. " + e.getMessage());
         databaseResult = DatabaseResults.UNKNOWN_ERROR;
      } finally {
         closeCollectionAndDatabase();
      }
      return new ChartDataResult(chartData, databaseResult);
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
         LOG.error("Exception when reading max id for charts. " + e.getMessage());
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
