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
   private FullDateTime fullDateTimeMock;
   @Mock
   private SimpleDateTime simpleDateTimeMock;
   @Mock
   private SimpleDate simpleDateMock;
   @Mock
   private SimpleTime simpleTimeMock;
   @Mock
   private Location locationMock;
   @Mock
   private ChartMetaData chartMetaDataMock;
   private ChartData chartData;

   @Before
   public void setUp() {
      when(simpleDateMock.getYear()).thenReturn(2020);
      when(simpleDateMock.getMonth()).thenReturn(2);
      when(simpleDateMock.getDay()).thenReturn(19);
      when(simpleDateMock.isGregorian()).thenReturn(true);
      when(simpleTimeMock.getHour()).thenReturn(19);
      when(simpleTimeMock.getMinute()).thenReturn(16);
      when(simpleTimeMock.getSecond()).thenReturn(20);
      when(simpleDateTimeMock.getDate()).thenReturn(simpleDateMock);
      when(simpleDateTimeMock.getTime()).thenReturn(simpleTimeMock);
      when(fullDateTimeMock.getDateTime()).thenReturn(simpleDateTimeMock);
      chartData = new ChartData(id, fullDateTimeMock, locationMock, chartMetaDataMock);
   }


   @Test
   public void getSimpleDateTime() {
      assertEquals(fullDateTimeMock, chartData.getFullDateTime());
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