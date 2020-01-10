/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;

import java.util.List;

public class PersistableChartData {

   private String id;
   private List<Integer> categories;
   private SimpleDateTime simpleDateTime;
   private Location location;
   private ChartMetaData chartMetaData;

   public PersistableChartData(final String id, final List<Integer> categories, final SimpleDateTime simpleDateTime,
                               final Location location, final ChartMetaData chartMetaData) {
      this.id = id;
      this.categories = categories;
      this.simpleDateTime = simpleDateTime;
      this.location = location;
      this.chartMetaData = chartMetaData;
   }

   public SimpleDateTime getSimpleDateTime() {
      return simpleDateTime;
   }

   public Location getLocation() {
      return location;
   }

   public List<Integer> getCategories() {
      return categories;
   }

   public String getId() {
      return id;
   }

   public ChartMetaData getChartMetaData() {
      return chartMetaData;
   }
}
