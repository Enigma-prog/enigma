/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron;

import com.radixpro.enigma.appmodel.HouseSystemsToCalculate;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultCelBodies;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultHouses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatedHousesTest {

   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultHouses sePositionResultMock;
   @InjectMocks
   private CalculatedHouses calculatedHouses;
   private final double[] cusps = {0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.10, 11.11, 12.12};
   private final double[] ascMc = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 0.0, 0.0};
   private final double jdUt =  1234567.8912;
   private final int flags = 3;
   private final double geoLat = 52.0;
   private final double geoLong = 7.0;
   private final HouseSystemsToCalculate system = HouseSystemsToCalculate.PLACIDUS;
   private final int nrOfCusps = 12;

   @Before
   public void setUp() {
      when(sePositionResultMock.getCusps()).thenReturn(cusps);
      when(sePositionResultMock.getAscMc()).thenReturn(ascMc);
      when(seFrontendMock.getPositionsForHouses(anyDouble(), anyInt(), anyDouble(), anyDouble(), anyInt(),
            anyInt())). thenReturn(sePositionResultMock);
   }

   @Test
   public void getCuspPositions() {
      assertEquals(cusps, calculatedHouses.getCuspPositions(jdUt, flags, geoLat, geoLong, system, nrOfCusps));
   }

   @Test
   public void getAdditionalPositions() {
      assertEquals(ascMc, calculatedHouses.getAdditionalPositions(jdUt, flags, geoLat, geoLong, system, nrOfCusps));
   }
}