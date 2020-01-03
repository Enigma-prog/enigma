/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.core;

import com.radixpro.enigma.be.astron.assist.CelObjectSinglePosition;
import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SePositionResultCelObjects;
import com.radixpro.enigma.be.astron.assist.SePositionResultHouses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeFrontendTest {

   @Mock
   private CelObjectSinglePosition celObjectSinglePositionMock;
   @Mock
   private Location locationMock;
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
      when(locationMock.getGeoLat()).thenReturn(geoLat);
      when(locationMock.getGeoLong()).thenReturn(geoLong);
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
      SePositionResultCelObjects result = SeFrontend.getFrontend().getPositionsForCelBody(jdUt, bodyId, flags);
      assertEquals(148.08135699695939, result.getAllPositions()[0], delta);
   }

   @Test
   public void getPositionsForHouses() {
      SePositionResultHouses result = SeFrontend.getFrontend().getPositionsForHouses(jdUt, flags, locationMock,
            system, nrOfCusps);
      assertEquals(59.97963584631173, result.getCusps()[3], delta);
      assertEquals(258.18944437108246, result.getAscMc()[2], delta);
   }

   @Test
   public void getHorizontalPosition() {
      int horFlags = 0;    // ecliptical
      double[] eclipticalCoordinates = new double[]{22.2, 2.2, 5.2};
      double[] result = SeFrontend.getFrontend().getHorizontalPosition(jdUt, eclipticalCoordinates,
            locationMock, horFlags);
      assertEquals(238.22139830075912, result[0], delta);
      assertEquals(-9.634283826590398, result[1], delta);

   }

   @Test
   public void convertToEquatorial() {
      double[] eclipticPositions = {122.22, 1.1, 1.0};
      double obliquity = 23.447;
      double[] equatorialPositions = SeFrontend.getFrontend().convertToEquatorial(eclipticPositions, obliquity);
      assertEquals(124.751841376, equatorialPositions[0], delta);
      assertEquals(20.743011595, equatorialPositions[1], delta);

   }
}