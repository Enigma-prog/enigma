/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class ChartDataTest {

   private final int id = 123;
   @Mock
   private SimpleDateTime simpleDateTimeMock;
   @Mock
   private Location locationMock;
   @Mock
   private ChartMetaData chartMetaDataMock;
   private ChartData chartData;

   @Before
   public void setUp() {
      chartData = new ChartData(id, simpleDateTimeMock, locationMock, chartMetaDataMock);
   }


   @Test
   public void getSimpleDateTime() {
      assertEquals(simpleDateTimeMock, chartData.getSimpleDateTime());
   }

   @Test
   public void getLocation() {
      assertEquals(locationMock, chartData.getLocation());
   }

   @Test
   public void getId() {
      assertEquals(id, chartData.getId());
   }

   @Test
   public void getChartMetaData() {
      assertEquals(chartMetaDataMock, chartData.getChartMetaData());
   }
}