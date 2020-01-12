/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.ChartData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChartDataListResultTest {

   @Mock
   private ChartData chartDataMock1;
   @Mock
   private ChartData getChartDataMock2;
   private List<ChartData> chartDataList;
   private DatabaseResults databaseResult;
   private ChartDataListResult chartDataListResult;

   @Before
   public void setUp() {
      databaseResult = DatabaseResults.OK;
      chartDataList = new ArrayList<>();
      chartDataListResult = new ChartDataListResult(chartDataList, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, chartDataListResult.getDatabaseResult());
   }

   @Test
   public void getChartDataList() {
      assertEquals(chartDataList, chartDataListResult.getChartDataList());
   }

}