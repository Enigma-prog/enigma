/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CelBodyHorizontalCoordinatesTest {

   private final double jdUt = 123456.789;
   private final double geoLat = 50.0;
   private final double geoLon = -100.0;
   private final int flags = 0;
   private final double[] horPositions = {3.3, 4.4};
   private final double delta = 0.00000001;
   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private Location locationMock;
   @Mock
   private CelBodySingleCoordinates celBodySingleCoordinatesMock;
   private CelBodyHorizontalCoordinates celBodyHorizontalCoordinates;

   @Before
   public void setUp() {
      when(seFrontendMock.getHorizontalPositionForCelBody(anyDouble(), any(), any(),
            anyInt())).thenReturn(horPositions);
      celBodyHorizontalCoordinates = new CelBodyHorizontalCoordinates(seFrontendMock, jdUt, celBodySingleCoordinatesMock,
            locationMock, flags);
   }

   @Test
   public void getAzimuth() {
      assertEquals(3.3, celBodyHorizontalCoordinates.getAzimuth(), delta);
   }

   @Test
   public void getAltitude() {
      assertEquals(4.4, celBodyHorizontalCoordinates.getAltitude(), delta);
   }
}