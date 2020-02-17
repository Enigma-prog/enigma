/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.be.astron.main.JulianDay;
import org.dizitart.no2.objects.Id;

public class ChartData {

   @Id
   private final long id;
   private final SimpleDateTime simpleDateTime;
   private final Location location;
   private final ChartMetaData chartMetaData;
   private final JulianDay julianDay;


   public ChartData(final long id, final SimpleDateTime simpleDateTime,
                    final Location location, final ChartMetaData chartMetaData) {
      this.id = id;
      this.simpleDateTime = simpleDateTime;
      this.location = location;
      this.chartMetaData = chartMetaData;
      this.julianDay = new JulianDay(simpleDateTime);
   }

   public SimpleDateTime getSimpleDateTime() {
      return simpleDateTime;
   }

   public Location getLocation() {
      return location;
   }

   public long getId() {
      return id;
   }

   public ChartMetaData getChartMetaData() {
      return chartMetaData;
   }

   public double getJulianDayForUt() {
      return julianDay.getJdNrUt();
   }

}
