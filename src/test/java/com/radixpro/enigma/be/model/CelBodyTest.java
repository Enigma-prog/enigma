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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CelBodyTest {

   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultCelBodies sePositionResultMock;
   private CelBody celBody;
   private final Double jdUt = 1234567.891;
   private final CelBodiesToCalculate celBodyToCalc = CelBodiesToCalculate.MERCURY;
   private final double geoLat = 52.0;
   private final double geoLong = 7.0;
   private ArrayList<SeFlags> flagList;

   @Before
   public void setUp() throws Exception {
      when(sePositionResultMock.getErrorMsg()).thenReturn("errormsg");
      when(sePositionResultMock.getAllPositions()).thenReturn(new double[] {1.1, 1.2, 1.3, 1.4, 1.5, 1.6});
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(sePositionResultMock);
      when(seFrontendMock.getHorizontalPositionForCelBody(anyDouble(), any(), anyDouble(), anyDouble(),
            anyInt())).thenReturn(new double[] {3.4, 5.6});
      flagList = new ArrayList<>();
      flagList.add(SeFlags.SWISSEPH);
      celBody = new CelBody(seFrontendMock, jdUt, celBodyToCalc, geoLat, geoLong, flagList);
   }

   @Test
   public void getEclipticalPosition() {
      assertTrue(celBody.getEclipticalPosition() instanceof CelBodySingleCoordinates);
   }

   @Test
   public void getEquatorialPosition() {
      assertTrue(celBody.getEquatorialPosition() instanceof CelBodySingleCoordinates);
   }

   @Test
   public void getHorizontalPosition() {
      assertTrue(celBody.getHorizontalPosition() instanceof CelBodyHorizontalCoordinates);
   }
}