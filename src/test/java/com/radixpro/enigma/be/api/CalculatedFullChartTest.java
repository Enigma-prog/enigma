/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.be.astron.assist.CalculationSettings;
import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;
import com.radixpro.enigma.be.astron.main.FullChart;
import com.radixpro.enigma.xchg.domain.CelestialBodies;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatedFullChartTest {

   @Mock
   private Location locationMock;
   @Mock
   private SimpleDateTime simpleDateTimeMock;
   @Mock
   private CalculationSettings settingsMock;
   private FullChart fullChart;

   @Before
   public void setUp() {
      final List<CelestialBodies> bodies = new ArrayList<>();
      bodies.add(CelestialBodies.SUN);
      bodies.add(CelestialBodies.MOON);
      when(locationMock.getGeoLat()).thenReturn(44.4);
      when(locationMock.getGeoLong()).thenReturn(22.2);
      when(settingsMock.isHeliocentric()).thenReturn(false);
      when(settingsMock.isTopocentric()).thenReturn(true);
      when(settingsMock.isHeliocentric()).thenReturn(false);
      when(settingsMock.getHouseSystem()).thenReturn(HouseSystems.PLACIDUS);
      when(settingsMock.getCelBodies()).thenReturn(bodies);
      fullChart = new FullChart(simpleDateTimeMock, locationMock, settingsMock);
   }

   @Test
   public void getBodies() {
      assertEquals(2, fullChart.getBodies().size());
   }

   @Test
   public void getHouseValues() {
      assertNotNull(fullChart.getMundaneValues());
   }

   @Test
   public void getSettings() {
      assertEquals(settingsMock, fullChart.getSettings());
   }

   @Test
   public void getDateTime() {
      assertEquals(simpleDateTimeMock, fullChart.getSimpleDateTime());
   }

   @Test
   public void getLocation() {
      assertEquals(locationMock, fullChart.getLocation());
   }
}