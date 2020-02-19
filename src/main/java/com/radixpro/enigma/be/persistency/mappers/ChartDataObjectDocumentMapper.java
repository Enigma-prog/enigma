/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

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
      final SimpleDate date = chartData.getFullDateTime().getDateTime().getDate();
      final SimpleTime time = chartData.getFullDateTime().getDateTime().getTime();
      return Document.createDocument("_id", chartData.getId())   // todo add timezone, dst, offsetForLmt + additional metadata
            .put("year", date.getYear())
            .put("month", date.getMonth())
            .put("day", date.getDay())
            .put("hour", time.getHour())
            .put("minute", time.getMinute())
            .put("second", time.getSecond())
            .put("gregorian", date.isGregorian())
            .put("jdut", chartData.getJulianDayForUt())
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
      var dateTime = new SimpleDateTime(date, time);
      var fullDateTime = new FullDateTime(dateTime, TimeZones.UT, false, 0.0); // TODO replace dummy values with real values
      var location = new Location((double) doc.get("geolat"), (double) doc.get("geolong"));
      var chartMetaData = new ChartMetaData((String) doc.get("name"), (String) doc.get("description"),
            (String) doc.get("source"), ChartTypes.UNKNOWN.chartTypeForId((int) doc.get("charttype")),
            Ratings.ZZ.getRatingForId((int) doc.get("rating")));
      return new ChartData(id, fullDateTime, location, chartMetaData);
   }


}
