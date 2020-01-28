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

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChartDataResultTest {

   private final DatabaseResults databaseResult = DatabaseResults.OK;
   @Mock
   private ChartDataResult chartDataResult;
   private List<ChartData> chartDataList;

   @Before
   public void setUp() {
      chartDataResult = new ChartDataResult(chartDataList, databaseResult);
   }

   @Test
   public void getDatabaseResult() {
      assertEquals(databaseResult, chartDataResult.getDatabaseResult());
   }

   @Test
   public void getChartData() {
      assertEquals(chartDataList, chartDataResult.getChartDataList());
   }

}