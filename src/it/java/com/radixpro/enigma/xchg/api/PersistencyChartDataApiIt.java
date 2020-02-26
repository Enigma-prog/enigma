/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.xchg.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test for persistency of ChartData.
 */
public class PersistencyChartDataApiIt {


   private PersistedChartDataApi api;
   private ChartData chartData1;
   private ChartData chartData2;

   @Before
   public void setUp() {
      api = new PersistedChartDataApi();
      chartData1 = createChartData(1, "one");
      chartData2 = createChartData(2, "two");
      List<ChartData> chartDataList = api.readAll();
      // clean up
      for (ChartData chartData : chartDataList) {
         api.delete(chartData);
      }

   }

   @Test
   public void insert() {
      try {
         api.insert(chartData1);
         assertTrue("No exception occurred.", true);
      } catch (Exception e) {
         fail("Excetion during insert of ChartData.");
      }
   }

   @Test
   public void update() {
      api.insert(chartData1);
      var updateChartData = createChartData(1, "ein");
      api.update(updateChartData);
      ChartData chartData = api.read(1).get(0);
      assertEquals("ein", chartData.getChartMetaData().getName());
   }

   @Test
   public void read() {
      api.insert(chartData1);
      api.insert(chartData2);
      ChartData chartData = api.read(1).get(0);
      assertEquals("one", chartData.getChartMetaData().getName());
   }

   @Test
   public void readAll() {
      api.insert(chartData1);
      api.insert(chartData2);
      List<ChartData> chartDataList = api.readAll();
      assertEquals(2, chartDataList.size());
   }

   @Test
   public void readNotFound() {
      api.insert(chartData1);
      api.insert(chartData2);
      assertEquals(0, api.read(5).size());
   }

   @Test
   public void search() {
      api.insert(chartData1);
      api.insert(chartData2);
      ChartData chartData = api.search("two").get(0);
      assertEquals("two", chartData.getChartMetaData().getName());
   }

   @Test
   public void searchNotFound() {
      api.insert(chartData1);
      api.insert(chartData2);
      assertEquals(0, api.search("three").size());
   }


   @Test
   public void getMaxId() {
      assertEquals(0, api.getMaxId());
      api.insert(chartData2);
      assertEquals(2, api.getMaxId());
   }


   private ChartData createChartData(final long id, final String name) {
      final var simpleDate = new SimpleDate(2020, 1, 10, true);
      final var simpleTime = new SimpleTime(22, 52, 0);
      final var simpleDateTime = new SimpleDateTime(simpleDate, simpleTime);
      final var fullDateTime = new FullDateTime(simpleDateTime, TimeZones.UT, false, 0.0);
      final var longCoord = new GeographicCoordinate(52, 0, 0, "N", 52.0);
      final var latCoord = new GeographicCoordinate(7, 0, 0, "E", 7.0);
      final var location = new Location(longCoord, latCoord, "Name of location");
      final List<Integer> categories = new ArrayList<>();
      categories.add(1);
      final var chartMetaData = new ChartMetaData(name, "description", "source", ChartTypes.NATAL,
            Ratings.AA);
      return new ChartData(id, fullDateTime, location, chartMetaData);
   }


}
