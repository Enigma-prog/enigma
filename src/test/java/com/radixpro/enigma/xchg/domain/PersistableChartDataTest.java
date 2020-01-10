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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersistableChartDataTest {

   private final String id = "myId";
   @Mock
   private SimpleDateTime simpleDateTimeMock;
   @Mock
   private Location locationMock;
   @Mock
   private ChartMetaData chartMetaDataMock;
   private List<Integer> categories;
   private PersistableChartData persistableChartData;

   @Before
   public void setUp() {
      categories = new ArrayList<>();
      categories.add(12);
      categories.add(3);
      persistableChartData = new PersistableChartData(id, categories, simpleDateTimeMock, locationMock, chartMetaDataMock);
   }


   @Test
   public void getSimpleDateTime() {
      assertEquals(simpleDateTimeMock, persistableChartData.getSimpleDateTime());
   }

   @Test
   public void getLocation() {
      assertEquals(locationMock, persistableChartData.getLocation());
   }

   @Test
   public void getCategories() {
      assertEquals(categories, persistableChartData.getCategories());
   }

   @Test
   public void getId() {
      assertEquals(id, persistableChartData.getId());
   }

   @Test
   public void getChartMetaData() {
      assertEquals(chartMetaDataMock, persistableChartData.getChartMetaData());
   }
}