/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.persistency.results.ChartDataListResult;
import com.radixpro.enigma.be.persistency.results.ChartDataResult;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.xchg.domain.*;
import org.dizitart.no2.*;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.filters.Filters.eq;


public class ChartDataDao {

   private static final String COLLECTION_NAME = "chartdata";
   private Nitrite nitriteDb;
   private NitriteCollection collection;

   public DatabaseResults insert(final ChartData chartData) {
      var databaseResult = DatabaseResults.OK;
      try {
         openCollectionAndDatabase();
         final WriteResult insertResult = collection.insert(chartData2Document(chartData));
         if (insertResult.getAffectedCount() != 1) {
            databaseResult = DatabaseResults.NOT_UNIQUE;
         }
      } catch (Exception e) {
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
         final WriteResult updateResult = collection.update(chartData2Document(chartData));
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

   public DatabaseResults delete(final ChartData chartData) {
      openCollectionAndDatabase();
      Document chartDoc = chartData2Document(chartData);
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
         chartData = document2ChartData(chartDoc);
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
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
            chartDataList.add(document2ChartData(foundChartData));
      } catch (Exception e) {
         databaseResult = DatabaseResults.UNKNOWN_ERROR;   // TODO extend error handling
      } finally {
         closeCollectionAndDatabase();
      }
      return new ChartDataListResult(chartDataList, databaseResult);
   }

//   private Document chartData2Document(final ChartData chartData) {
//      return Document.createDocument("_id", chartData.getId())
//            .put("datetime", chartData.getSimpleDateTime())
//            .put("location", chartData.getLocation())
//            .put("meta", chartData.getChartMetaData());
//   }

   private Document chartData2Document(final ChartData chartData) {
      return Document.createDocument("_id", chartData.getId())
            .put("year", chartData.getSimpleDateTime().getYear())
            .put("month", chartData.getSimpleDateTime().getMonth())
            .put("day", chartData.getSimpleDateTime().getDay())
            .put("hour", chartData.getSimpleDateTime().getHour())
            .put("minute", chartData.getSimpleDateTime().getMinute())
            .put("second", chartData.getSimpleDateTime().getSecond())
            .put("gregorian", chartData.getSimpleDateTime().isGregorian())
            .put("jdut", chartData.getSimpleDateTime().getJdUt())
            .put("geolat", chartData.getLocation().getGeoLat())
            .put("geolong", chartData.getLocation().getGeoLong())
            .put("name", chartData.getChartMetaData().getName())
            .put("description", chartData.getChartMetaData().getDescription())
            .put("source", chartData.getChartMetaData().getSource())
            .put("sex", chartData.getChartMetaData().getSex())
            .put("charttype", chartData.getChartMetaData().getChartType().getId())
            .put("rating", chartData.getChartMetaData().getRating().getId())
            .put("categories", chartData.getChartMetaData().getCategories());
   }


   private ChartData document2ChartData(final Document doc) {
      long id = (long) doc.get("_id");
      var date = new SimpleDate((int) doc.get("day"), (int) doc.get("month"), (int) doc.get("month"), (boolean) doc.get("gregorian"));
      var time = new SimpleTime((int) doc.get("hour"), (int) doc.get("minute"), (int) doc.get("second"));
      var dateTime = new SimpleDateTime(SeFrontend.getFrontend(), date, time);
      var location = new Location((double) doc.get("geolat"), (double) doc.get("geolong"));
      var chartMetaData = new ChartMetaData((String) doc.get("name"), (String) doc.get("description"),
            (String) doc.get("source"), (String) doc.get("sex"), (List<Integer>) doc.get("categories"),
            ChartTypes.UNKNOWN.chartTypeForId((int) doc.get("charttype")),
            Ratings.ZZ.ratingForId((int) doc.get("rating")));
      return new ChartData(id, dateTime, location, chartMetaData);
   }

/*
   public ChartMetaData(final String name, final String description, final String source, final String sex,
                        final List<Integer> categories, final ChartTypes chartType, final Ratings rating) {
 */


//   private ChartData document2ChartData(final Document document) {
//      long id = (long) document.get("_id");
//      var simpleDateTime = (SimpleDateTime) document.get("datetime");
//      var location = (Location) document.get("location");
//      var chartMetaData = (ChartMetaData) document.get("meta");
//      return new ChartData(id, simpleDateTime, location, chartMetaData);
//   }


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
