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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FullChartTest {

   @Mock
   private SimpleDateTime simpleDateTimeMock;
   @Mock
   private SimpleDate simpleDateMock;
   @Mock
   private SimpleTime simpleTimeMock;
   @Mock
   private Location locationMock;
   @Mock
   private CalculationSettings settingsMock;

   private FullChart fullChart;

   @Before
   public void setUp() {
      List<CelestialObjects> celBodies = new ArrayList<>();
      celBodies.add(CelestialObjects.SUN);
      celBodies.add(CelestialObjects.MOON);
      when(settingsMock.isTopocentric()).thenReturn(false);
      when(settingsMock.isHeliocentric()).thenReturn(false);
      when(settingsMock.isSidereal()).thenReturn(true);
      when(settingsMock.getHouseSystem()).thenReturn(HouseSystems.AXIAL);
      when(settingsMock.getCelBodies()).thenReturn(celBodies);
      when(simpleDateMock.getYear()).thenReturn(2020);
      when(simpleDateMock.getMonth()).thenReturn(2);
      when(simpleDateMock.getDay()).thenReturn(19);
      when(simpleDateMock.isGregorian()).thenReturn(true);
      when(simpleTimeMock.getHour()).thenReturn(19);
      when(simpleTimeMock.getMinute()).thenReturn(16);
      when(simpleTimeMock.getSecond()).thenReturn(20);
      when(simpleDateTimeMock.getDate()).thenReturn(simpleDateMock);
      when(simpleDateTimeMock.getTime()).thenReturn(simpleTimeMock);

      fullChart = new FullChart(simpleDateTimeMock, locationMock, settingsMock);
   }

   @Test
   public void getHouseValues() {
      assertNotNull(fullChart.getMundaneValues());
   }

   @Test
   public void getBodies() {
      assertEquals(2, fullChart.getBodies().size());
   }

   @Test
   public void getDateTime() {
      assertEquals(simpleDateTimeMock, fullChart.getSimpleDateTime());
   }

   @Test
   public void getLocation() {
      assertEquals(locationMock, fullChart.getLocation());
   }

   @Test
   public void getSettings() {
      assertEquals(settingsMock, fullChart.getSettings());
   }

   @Test
   public void getObliquity() {
      assertTrue(fullChart.getObliquity() > 0.0);
   }
}