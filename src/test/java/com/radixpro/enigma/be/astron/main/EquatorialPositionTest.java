/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.EquatorialPosition;
import com.radixpro.enigma.be.astron.assist.SePositionResultCelBodies;
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
public class EquatorialPositionTest {

   private static final double DELTA = 0.00000001;
   private final double longitude = 100.0;
   private final double jdUt = 123456.789;
   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultCelBodies sePosResultMock;
   private EquatorialPosition equatorialPosition;

   @Before
   public void setUp() {
      when(sePosResultMock.getAllPositions()).thenReturn(new double[]{1.1, 2.2, 3.3});
      when(seFrontendMock.convertToEquatorial(any(), anyDouble())).thenReturn(new double[]{101.0, -1.0, 1.0});
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(sePosResultMock);
   }

   @Test
   public void getRightAscension() {
      equatorialPosition = new EquatorialPosition(seFrontendMock, longitude, jdUt);
      assertEquals(101.0, equatorialPosition.getRightAscension(), DELTA);
   }

   @Test
   public void getRightAscensionValuesInConstructor() {
      equatorialPosition = new EquatorialPosition(200.2, 12.3);
      assertEquals(200.2, equatorialPosition.getRightAscension(), DELTA);
   }

   @Test
   public void getDeclination() {
      equatorialPosition = new EquatorialPosition(seFrontendMock, longitude, jdUt);
      assertEquals(-1.0, equatorialPosition.getDeclination(), DELTA);
   }

   @Test
   public void getDeclinationValuesInConstructor() {
      equatorialPosition = new EquatorialPosition(278.9, -18.8);
      assertEquals(-18.8, equatorialPosition.getDeclination(), DELTA);
   }

}