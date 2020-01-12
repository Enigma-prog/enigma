/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.nitritedomain;

import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;
import com.radixpro.enigma.xchg.domain.ChartData;
import com.radixpro.enigma.xchg.domain.ChartMetaData;

/**
 * Keeps persistable data for ChartData.
 */
public class NitriteChartData {

   private long id;
   private SimpleDateTime simpleDateTime;
   private Location location;
   private ChartMetaData chartMetaData;


   public NitriteChartData() {

   }

   public NitriteChartData(final ChartData chartData) {
      this.id = chartData.getId();
      this.simpleDateTime = chartData.getSimpleDateTime();
      this.location = chartData.getLocation();
      this.chartMetaData = chartData.getChartMetaData();
   }


   public long getId() {
      return id;
   }

   public SimpleDateTime getSimpleDateTime() {
      return simpleDateTime;
   }

   public Location getLocation() {
      return location;
   }

   public ChartMetaData getChartMetaData() {
      return chartMetaData;
   }
}


/*
   private ChartData createChartData(final int id, final String name) {
      final var simpleDate = new SimpleDate(2020,1,10, true);
      final var simpleTime = new SimpleTime(22,52,0);
      final var simpleDateTime = new SimpleDateTime(SeFrontend.getFrontend(), simpleDate, simpleTime);
      final var location = new Location(52.0, 7.0);
      final List<Integer> categories = new ArrayList<>();
      categories.add(1);
      final var chartMetaData = new ChartMetaData(name, "description", "source", "m", categories,
            ChartTypes.NATAL, Ratings.AA);
      return new ChartData(id, simpleDateTime, location, chartMetaData);
   }
 */