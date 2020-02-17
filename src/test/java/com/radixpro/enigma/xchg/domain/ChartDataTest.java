/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
      when(simpleDateTimeMock.getYear()).thenReturn(1953);
      when(simpleDateTimeMock.getMonth()).thenReturn(1);
      when(simpleDateTimeMock.getDay()).thenReturn(29);
      when(simpleDateTimeMock.getHour()).thenReturn(7);
      when(simpleDateTimeMock.getMinute()).thenReturn(37);
      when(simpleDateTimeMock.getSecond()).thenReturn(30);
      when(simpleDateTimeMock.isGregorian()).thenReturn(true);
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