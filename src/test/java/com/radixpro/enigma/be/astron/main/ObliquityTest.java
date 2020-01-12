/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.SePositionResultCelObjects;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ObliquityTest {

   public static final double DELTA = 0.00000001;
   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultCelObjects positionResultMock;
   private Obliquity obliquity;

   @Before
   public void setUp() {
      when(positionResultMock.getAllPositions()).thenReturn(new double[]{1.1, 1.2, 3.3, 4.4, 0.0, 0.0});
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(positionResultMock);
      double jdUt = 123456.789;
      obliquity = new Obliquity(seFrontendMock, jdUt);
   }

   @Test
   public void getTrueObliquity() {
      assertEquals(1.1, obliquity.getTrueObliquity(), DELTA);
   }

   @Test
   public void getMeanObliquity() {
      assertEquals(1.2, obliquity.getMeanObliquity(), DELTA);
   }
}