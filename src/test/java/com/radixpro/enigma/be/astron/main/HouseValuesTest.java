/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.*;
import com.radixpro.enigma.be.astron.core.SeFrontend;
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
public class HouseValuesTest {

   private final double[] cusps = {0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.10, 11.11, 12.12};
   private final double[] ascMc = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 0.0, 0.0};  // Asc,MC,ARMC,Vertex,Eastpoint...
   private final double jdUt = 1234567.8912;
   private final int flags = 3;
   private final double delta = 0.00000001;
   private final HouseSystemsToCalculate system = HouseSystemsToCalculate.PLACIDUS;
   private final int nrOfCusps = 12;
   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultHouses sePositionResultHousesMock;
   @Mock
   private SePositionResultCelBodies sePositionResultCelBodiesMock;
   @Mock
   private Location locationMock;
   private HouseValues houseValues;

   @Before
   public void setUp() {
      when(sePositionResultHousesMock.getCusps()).thenReturn(cusps);
      when(sePositionResultHousesMock.getAscMc()).thenReturn(ascMc);
      when(sePositionResultCelBodiesMock.getAllPositions()).thenReturn(new double[]{23.447, 23.448});
      when(seFrontendMock.convertToEquatorial(any(), anyDouble())).thenReturn(new double[]{2.3, 3.3});
      when(seFrontendMock.getPositionsForHouses(anyDouble(), anyInt(), any(), anyInt(),
            anyInt())).thenReturn(sePositionResultHousesMock);
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(sePositionResultCelBodiesMock);
      houseValues = new HouseValues(seFrontendMock, jdUt, flags, locationMock, system);
   }

   @Test
   public void getCusps() {
      List<FullPositionResultHouses> result = houseValues.getCusps();
      assertEquals(cusps.length, result.size());
      assertEquals(cusps[3], result.get(3).getLongitude(), delta);
   }

   @Test
   public void getMc() {
      assertEquals(2.2, houseValues.getMc().getLongitude(), delta);
   }

   @Test
   public void getAscendant() {
      assertEquals(1.1, houseValues.getAscendant().getLongitude(), delta);
   }

   @Test
   public void getEastpoint() {
      assertEquals(5.5, houseValues.getEastpoint(), delta);
   }

   @Test
   public void getVertex() {
      assertEquals(4.4, houseValues.getVertex(), delta);
   }

   @Test
   public void getArmc() {
      assertEquals(3.3, houseValues.getArmc(), delta);
   }

}