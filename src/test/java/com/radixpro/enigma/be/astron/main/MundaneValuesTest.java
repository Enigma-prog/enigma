/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.HousePosition;
import com.radixpro.enigma.be.astron.assist.SePositionResultCelObjects;
import com.radixpro.enigma.be.astron.assist.SePositionResultHouses;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import com.radixpro.enigma.xchg.domain.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MundaneValuesTest {

   private final double[] cusps = {0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.10, 11.11, 12.12};
   private final double[] ascMc = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 0.0, 0.0};  // Asc,MC,ARMC,Vertex,Eastpoint...
   private final double delta = 0.00000001;
   private final HouseSystems system = HouseSystems.PLACIDUS;
   private final int nrOfCusps = 12;
   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultHouses sePositionResultHousesMock;
   @Mock
   private SePositionResultCelObjects sePositionResultCelObjectsMock;
   @Mock
   private Location locationMock;
   private MundaneValues mundaneValues;

   @Before
   public void setUp() {
      when(sePositionResultHousesMock.getCusps()).thenReturn(cusps);
      when(sePositionResultHousesMock.getAscMc()).thenReturn(ascMc);
      when(sePositionResultCelObjectsMock.getAllPositions()).thenReturn(new double[]{23.447, 23.448});
      when(seFrontendMock.convertToEquatorial(any(), anyDouble())).thenReturn(new double[]{2.3, 3.3});
      when(seFrontendMock.getPositionsForHouses(anyDouble(), anyInt(), any(), anyInt(),
            anyInt())).thenReturn(sePositionResultHousesMock);
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(sePositionResultCelObjectsMock);
      when(seFrontendMock.getHorizontalPosition(anyDouble(), any(), any(), anyInt())).thenReturn(new double[]{100.0, -30.0});
      int flags = 3;
      double jdUt = 1234567.8912;
      mundaneValues = new MundaneValues(seFrontendMock, jdUt, flags, locationMock, system);
   }

   @Test
   public void getCusps() {
      List<HousePosition> result = mundaneValues.getCusps();
      assertEquals(cusps.length, result.size());
      assertEquals(cusps[3], result.get(3).getLongitude(), delta);
   }

   @Test
   public void getMc() {
      assertEquals(2.2, mundaneValues.getMc().getLongitude(), delta);
   }

   @Test
   public void getAscendant() {
      assertEquals(1.1, mundaneValues.getAscendant().getLongitude(), delta);
   }

   @Test
   public void getEastpoint() {
      assertEquals(5.5, mundaneValues.getEastpoint().getLongitude(), delta);
   }

   @Test
   public void getVertex() {
      assertEquals(4.4, mundaneValues.getVertex().getLongitude(), delta);
   }

   @Test
   public void getArmc() {
      assertEquals(3.3, mundaneValues.getArmc(), delta);
   }

}