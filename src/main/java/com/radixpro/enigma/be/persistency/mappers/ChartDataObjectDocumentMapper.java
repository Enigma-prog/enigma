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
      final SimpleDate date = chartData.getFullDateTime().getFullDateTime().getDate();
      final SimpleTime time = chartData.getFullDateTime().getFullDateTime().getTime();
      final GeographicCoordinate longInput = chartData.getLocation().getLongInput();
      final GeographicCoordinate latInput = chartData.getLocation().getLatInput();
      return Document.createDocument("_id", chartData.getId())   // todo add timezone, dst, offsetForLmt + additional metadata
            .put("year", date.getYear())
            .put("month", date.getMonth())
            .put("day", date.getDay())
            .put("hour", time.getHour())
            .put("minute", time.getMinute())
            .put("second", time.getSecond())
            .put("gregorian", date.isGregorian())
            .put("timezone", chartData.getFullDateTime().getTimeZone().getId())
            .put("dst", chartData.getFullDateTime().isDst())
            .put("offsetforlmt", chartData.getFullDateTime().getOffsetForLmt())
            .put("jdut", chartData.getJulianDayForUt())
            .put("locationname", chartData.getLocation().getName())
            .put("geolat", chartData.getLocation().getGeoLat())
            .put("geolong", chartData.getLocation().getGeoLong())
            .put("geolongdeg", longInput.getDegrees())
            .put("geolongmin", longInput.getMinutes())
            .put("geolongsec", longInput.getSeconds())
            .put("geolongdir", longInput.getDirection())
            .put("geolatdeg", latInput.getDegrees())
            .put("geolatmin", latInput.getMinutes())
            .put("geolatsec", latInput.getSeconds())
            .put("geolatdir", latInput.getDirection())
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

      int zoneId = (int) doc.get("timezone");
      TimeZones timeZone = TimeZones.UT.timeZoneForId(zoneId);
      var fullDateTime = new FullDateTime(dateTime, timeZone,
            (boolean) doc.get("dst"), (double) doc.get("offsetforlmt"));
      var longInput = new GeographicCoordinate((int) doc.get("geolongdeg"), (int) doc.get("geolongmin"),
            (int) doc.get("geolongsec"), (String) doc.get("geolongdir"), (double) doc.get("geolong"));
      var latInput = new GeographicCoordinate((int) doc.get("geolatdeg"), (int) doc.get("geolatmin"),
            (int) doc.get("geolatsec"), (String) doc.get("geolatdir"), (double) doc.get("geolat"));
      var location = new Location(longInput, latInput, (String) doc.get("locationname"));
      var chartMetaData = new ChartMetaData((String) doc.get("name"), (String) doc.get("description"),
            (String) doc.get("source"), ChartTypes.UNKNOWN.chartTypeForId((int) doc.get("charttype")),
            Ratings.ZZ.getRatingForId((int) doc.get("rating")));
      return new ChartData(id, fullDateTime, location, chartMetaData);
   }


}
