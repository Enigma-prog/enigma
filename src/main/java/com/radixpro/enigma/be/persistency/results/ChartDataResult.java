/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.ChartData;

import java.util.List;

/**
 * Validated result from querying database for ChartData.
 */
public class ChartDataResult extends AbstractResult {

   final List<ChartData> chartDataList;

   public ChartDataResult(final List<ChartData> chartDataList, final DatabaseResults result) {
      super(result);
      this.chartDataList = chartDataList;
   }

   public List<ChartData> getChartDataList() {
      return chartDataList;
   }

}
