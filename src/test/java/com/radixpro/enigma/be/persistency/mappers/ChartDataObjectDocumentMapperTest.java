/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.xchg.domain.*;
import org.dizitart.no2.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChartDataObjectDocumentMapperTest {

   private ChartDataObjectDocumentMapper mapper;

   @Before
   public void setUp() {
      mapper = new ChartDataObjectDocumentMapper();
   }

   @Test
   public void object2Document() {
      Document doc = mapper.object2Document(createObject());
      assertEquals(3L, (long) doc.get("_id"));
      assertEquals("Jan", doc.get("name"));
   }

   @Test
   public void document2Object() {
      ChartData chartData = mapper.document2Object(createDocument());
      assertEquals(3L, chartData.getId());
      assertEquals("Jan", chartData.getChartMetaData().getName());
   }

   private Document createDocument() {
      List<Integer> catList = new ArrayList<>();
      catList.add(13);
      catList.add(22);
      return Document.createDocument("_id", 3L)
            .put("year", 2020)
            .put("month", 1)
            .put("day", 12)
            .put("hour", 15)
            .put("minute", 38)
            .put("second", 15)
            .put("gregorian", true)
            .put("jdut", 1234.5678)
            .put("locationname", "Enschede")
            .put("geolat", 52.23)
            .put("geolong", 6.9)
            .put("geolongdeg", 6)
            .put("geolongmin", 54)
            .put("geolongsec", 0)
            .put("geolongdir", "E")
            .put("geolatdeg", 52)
            .put("geolatmin", 13)
            .put("geolatsec", 48)
            .put("geolatdir", "N")
            .put("name", "Jan")
            .put("description", "description")
            .put("source", "source")
            .put("charttype", 2)
            .put("rating", 3);

   }


   private ChartData createObject() {
      List<Integer> catList = new ArrayList<>();
      catList.add(1);
      catList.add(2);
      long id = 3L;
      var date = new SimpleDate(2020, 1, 12, true);
      var time = new SimpleTime(14, 42, 55);
      var dateTime = new SimpleDateTime(date, time);
      var fullDateTime = new FullDateTime(dateTime, TimeZones.UT, false, 0.0);  // todo replace dummy values with real values
      var longCoord = new GeographicCoordinate(52, 13, 48, "N", 52.23);
      var latCoord = new GeographicCoordinate(6, 54, 0, "E", 6.9);
      var location = new Location(longCoord, latCoord, "Location name");
      var chartMetaData = new ChartMetaData("Jan", "Description", "Source",
            ChartTypes.ELECTION, Ratings.C);
      return new ChartData(id, fullDateTime, location, chartMetaData);
   }

}