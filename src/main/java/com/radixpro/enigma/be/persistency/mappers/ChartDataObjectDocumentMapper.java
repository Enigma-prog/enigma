/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.xchg.domain.*;
import org.dizitart.no2.Document;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Converter between ChartData and JSON document as persisted in Nitrite.
 */
public class ChartDataObjectDocumentMapper {

   /**
    * Converts instance of ChartData to a Nitrite Document.
    * Disabled some checks for ConstantConditions by IntelliJ Analyzer.
    * SonarLint does not see any problem here.
    *
    * @param chartData The object to save in Nitrite.
    * @return Document for Nitrite.
    */
   @SuppressWarnings({"unchecked", "ConstantConditions", "RedundantSuppression"})
   public Document object2Document(final ChartData chartData) {
      //noinspection ConstantConditions
      checkNotNull(chartData);
      SimpleDate date = chartData.getFullDateTime().getSimpleDateTime().getDate();
      SimpleTime time = chartData.getFullDateTime().getSimpleDateTime().getTime();
      GeographicCoordinate longInput = chartData.getLocation().getLongInput();
      GeographicCoordinate latInput = chartData.getLocation().getLatInput();
      return Document.createDocument("_id", chartData.getId())
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
            .put("jdut", chartData.getFullDateTime().getJdUt())
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

   /**
    * Convert Nitrite document to an instance of ChartData.
    *
    * @param doc The document with the JSON from Nitrite.
    * @return The resulting instance of ChartData.
    */
   public ChartData document2Object(final Document doc) {
      checkNotNull(doc);
      long id = (long) doc.get("_id");
      SimpleDate date = new SimpleDate((int) doc.get("year"), (int) doc.get("month"), (int) doc.get("day"), (boolean) doc.get("gregorian"));
      SimpleTime time = new SimpleTime((int) doc.get("hour"), (int) doc.get("minute"), (int) doc.get("second"));
      SimpleDateTime dateTime = new SimpleDateTime(date, time);

      int zoneId = (int) doc.get("timezone");
      TimeZones timeZone = TimeZones.UT.timeZoneForId(zoneId);
      FullDateTime fullDateTime = new FullDateTime(dateTime, timeZone, (boolean) doc.get("dst"), (double) doc.get("offsetforlmt"));
      GeographicCoordinate longInput = new GeographicCoordinate((int) doc.get("geolongdeg"), (int) doc.get("geolongmin"),
            (int) doc.get("geolongsec"), (String) doc.get("geolongdir"), (double) doc.get("geolong"));
      GeographicCoordinate latInput = new GeographicCoordinate((int) doc.get("geolatdeg"), (int) doc.get("geolatmin"),
            (int) doc.get("geolatsec"), (String) doc.get("geolatdir"), (double) doc.get("geolat"));
      Location location = new Location(longInput, latInput, (String) doc.get("locationname"));
      ChartMetaData chartMetaData = new ChartMetaData((String) doc.get("name"), (String) doc.get("description"),
            (String) doc.get("source"), ChartTypes.UNKNOWN.chartTypeForId((int) doc.get("charttype")),
            Ratings.ZZ.getRatingForId((int) doc.get("rating")));
      return new ChartData(id, fullDateTime, location, chartMetaData);
   }

}
