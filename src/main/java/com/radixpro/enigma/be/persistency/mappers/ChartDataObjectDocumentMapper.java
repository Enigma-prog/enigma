/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.*;
import org.dizitart.no2.Document;

public class ChartDataObjectDocumentMapper {

   /**
    * Convert object for chart-data to a Nitrite Document. Disabled check for ConstantConditions by IntelliJ Analyzer.
    * SonarLint does not see any problem here.
    *
    * @param chartData The object to save in Nitrite.
    * @return Document for Nitrite.
    */
   public Document object2Document(final ChartData chartData) {
      //noinspection ConstantConditions
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
            .put("charttype", chartData.getChartMetaData().getChartType().getId())
            .put("rating", chartData.getChartMetaData().getRating().getId());
   }

   public ChartData document2Object(final Document doc) {
      long id = (long) doc.get("_id");
      var date = new SimpleDate((int) doc.get("year"), (int) doc.get("month"), (int) doc.get("day"), (boolean) doc.get("gregorian"));
      var time = new SimpleTime((int) doc.get("hour"), (int) doc.get("minute"), (int) doc.get("second"));
      var dateTime = new SimpleDateTime(SeFrontend.getFrontend(), date, time);
      var location = new Location((double) doc.get("geolat"), (double) doc.get("geolong"));
      var chartMetaData = new ChartMetaData((String) doc.get("name"), (String) doc.get("description"),
            (String) doc.get("source"), ChartTypes.UNKNOWN.chartTypeForId((int) doc.get("charttype")),
            Ratings.ZZ.getRatingForId((int) doc.get("rating")));
      return new ChartData(id, dateTime, location, chartMetaData);
   }


}
