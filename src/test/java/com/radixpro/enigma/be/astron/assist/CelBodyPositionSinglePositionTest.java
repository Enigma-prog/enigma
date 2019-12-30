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
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CelBodyPositionSinglePositionTest {

   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultCelBodies sePositionResultMock;
   private CelBodySinglePosition celBodySinglePosition;
   private final double jdUt = 123.456;
   private final int flags = 2;
   private final CelBodiesToCalculate celBodyToCalculate = CelBodiesToCalculate.MARS;

   private final double delta = 0.00000001;
   private final double[] positions = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
   private final String errorMsg = "Text";

   @Before
   public void setUp() {
      when(sePositionResultMock.getAllPositions()).thenReturn(positions);
      when(sePositionResultMock.getErrorMsg()).thenReturn(errorMsg);
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(sePositionResultMock);
      celBodySinglePosition = new CelBodySinglePosition(seFrontendMock, jdUt, celBodyToCalculate, flags);
   }

   @Test
   public void getErrorMsg() {
      assertEquals(errorMsg, celBodySinglePosition.getErrorMsg());
   }

   @Test
   public void getMainPosition() {
      assertEquals(1.1, celBodySinglePosition.getMainPosition(), delta);
   }

   @Test
   public void getDeviationPosition() {
      assertEquals(2.2, celBodySinglePosition.getDeviationPosition(), delta);
   }

   @Test
   public void getDistancePosition() {
      assertEquals(3.3, celBodySinglePosition.getDistancePosition(), delta);
   }

   @Test
   public void getMainSpeed() {
      assertEquals(4.4, celBodySinglePosition.getMainSpeed(), delta);
   }

   @Test
   public void getDeviationSpeed() {
      assertEquals(5.5, celBodySinglePosition.getDeviationSpeed(), delta);
   }

   @Test
   public void getDistanceSpeed() {
      assertEquals(6.6, celBodySinglePosition.getDistanceSpeed(), delta);
   }
}