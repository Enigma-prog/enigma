/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.xchg.domain.ChartData;
import lombok.Getter;
import lombok.val;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Wrapper around ChartData; enables the use in a tableview.
 */
@Getter
public class PresentableChartData {

   final long chartId;
   final String chartName;
   final String chartDescr;
   final String chartDataDescr;
   final ChartData originalData;

   public PresentableChartData(final ChartData chartData) {
      checkNotNull(chartData);
      chartId = chartData.getId();
      chartName = chartData.getChartMetaData().getName();
      chartDescr = chartData.getChartMetaData().getDescription();
      chartDataDescr = createDataDescription(chartData);
      originalData = chartData;
   }

   private String createDataDescription(final ChartData chartData) {
      val descrSb = new StringBuilder();
      val dateTime4Text = new PresentableDateTime(chartData.getFullDateTime());
      descrSb.append(dateTime4Text.getDate());
      descrSb.append(" ");
      descrSb.append(dateTime4Text.getTime());
      return descrSb.toString();
   }
}
