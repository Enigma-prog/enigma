/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.xchg.domain.ChartData;

/**
 * Wrapper around ChartData; enables the use in a tableview.
 */
public class PresentableChartData {

   final long chartId;
   final String chartName;
   final String chartDescr;
   final String chartDataDescr;
   final ChartData originalData;

   public PresentableChartData(final ChartData chartData) {
      chartId = chartData.getId();
      chartName = chartData.getChartMetaData().getName();
      chartDescr = chartData.getChartMetaData().getDescription();
      chartDataDescr = createDataDescription(chartData);
      originalData = chartData;
   }

   public long getChartId() {
      return chartId;
   }

   public String getChartName() {
      return chartName;
   }

   public String getChartDescr() {
      return chartDescr;
   }

   public String getChartDataDescr() {
      return chartDataDescr;
   }

   public ChartData getOriginalData() {
      return originalData;
   }

   private String createDataDescription(final ChartData chartData) {
      StringBuilder descrSb = new StringBuilder();
      var dateTime4Text = new PresentableDateTime(chartData.getFullDateTime());
      descrSb.append(dateTime4Text.getDate());
      descrSb.append(" ");
      descrSb.append(dateTime4Text.getTime());
      return descrSb.toString();
   }
}
