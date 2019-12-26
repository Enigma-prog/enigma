/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.core.SeFrontend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HouseValuesTest {

   private final double[] cusps = {0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.10, 11.11, 12.12};
   private final double[] ascMc = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 0.0, 0.0};  // Asc,MC,ARMC,Vertex,Eastpoint...
   private final double jdUt = 1234567.8912;
   private final int flags = 3;
   private final double geoLat = 52.0;
   private final double geoLong = 7.0;
   private final double delta = 0.00000001;
   private final HouseSystemsToCalculate system = HouseSystemsToCalculate.PLACIDUS;
   private final int nrOfCusps = 12;
   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultHouses sePositionResultMock;
   private HouseValues houseValues;

   @Before
   public void setUp() {
      when(sePositionResultMock.getCusps()).thenReturn(cusps);
      when(sePositionResultMock.getAscMc()).thenReturn(ascMc);
      when(seFrontendMock.getPositionsForHouses(anyDouble(), anyInt(), anyDouble(), anyDouble(), anyInt(),
            anyInt())).thenReturn(sePositionResultMock);
      houseValues = new HouseValues(seFrontendMock, jdUt, flags, geoLat, geoLong, system, nrOfCusps);
   }

   @Test
   public void getCusps() {
      List<Double> result = houseValues.getCusps();
      assertEquals(cusps.length, result.size());
      assertEquals(cusps[3], result.get(3), delta);
   }

   @Test
   public void getMc() {
      assertEquals(2.2, houseValues.getMc(), delta);
   }

   @Test
   public void getAscendant() {
      assertEquals(1.1, houseValues.getAscendant(), delta);
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