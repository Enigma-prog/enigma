/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.ChartData;

/**
 * Validated result from querying database for ChartData.
 */
public class ChartDataResult extends AbstractResult {

   private final ChartData chartData;

   public ChartDataResult(final ChartData chartData, final DatabaseResults result) {
      super(result);
      this.chartData = chartData;
   }

   public ChartData getChartData() {
      return chartData;
   }
}
