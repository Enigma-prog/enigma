/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.core;

import com.radixpro.enigma.be.model.CelBodySingleCoordinates;
import com.radixpro.enigma.be.model.SePositionResultCelBodies;
import com.radixpro.enigma.be.model.SePositionResultHouses;
import com.radixpro.enigma.be.core.SeFrontend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeFrontendTest {

   @Mock
   private CelBodySingleCoordinates celBodySingleCoordinatesMock;
   private final double delta = 0.00000001;
   private final double jdUt = 1234567.89;
   private final int bodyId = 4;
   private final int flags = 1;
   private final double geoLat = 52.0;
   private final double geoLong = 7.0;
   private final char system = 'p';
   private final int nrOfCusps = 12;

   @Before
   public void setUp() {
      when(celBodySingleCoordinatesMock.getMainPosition()).thenReturn(100.0);
      when(celBodySingleCoordinatesMock.getDeviationPosition()).thenReturn(-1.0);
      when(celBodySingleCoordinatesMock.getDistancePosition()).thenReturn(3.3);
   }

   @Test
   public void getFrontend() {
      SeFrontend seFrontend = SeFrontend.getFrontend();
      assertNotNull(seFrontend);
   }

   @Test
   public void getJulianDay() {
      double[] result = SeFrontend.getFrontend().getJulianDay(1953,1,29,7,37,0,true);
      assertEquals(2434406.8177128294, result[0], delta);
      assertEquals(2434406.8173611113, result[1], delta);
   }


   @Test
   public void getPositionsForCelBody() {
      SePositionResultCelBodies result = SeFrontend.getFrontend().getPositionsForCelBody(jdUt, bodyId, flags);
      assertEquals(148.08135699695939, result.getAllPositions()[0], delta);
   }

   @Test
   public void getPositionsForHouses() {
      SePositionResultHouses result = SeFrontend.getFrontend().getPositionsForHouses(jdUt, flags, geoLat, geoLong,
            system, nrOfCusps);
      assertEquals(59.97963584631173, result.getCusps()[3], delta);
      assertEquals(258.18944437108246, result.getAscMc()[2], delta);
   }

   @Test
   public void getHorizontalPositionForCelBody() {
      int horFlags = 0;    // ecliptical
      double[] result = SeFrontend.getFrontend().getHorizontalPositionForCelBody(jdUt, celBodySingleCoordinatesMock,
            geoLat, geoLong, horFlags);
      assertEquals(158.59341318390926, result[0], delta);
      assertEquals(-12.93921011397498, result[1], delta);

   }


}