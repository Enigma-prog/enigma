/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron;

import com.radixpro.enigma.appmodel.CelBodiesToCalculate;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultCelBodies;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatedCelBodyTest {

   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SePositionResultCelBodies sePositionResultMock;
   @InjectMocks
   private CalculatedCelBody calculatedCelBody;
   private final double jdUt = 123.456;
   private final double delta = 0.00000001;
   private final CelBodiesToCalculate celBodyToCalculate = CelBodiesToCalculate.MARS;
   private final int flags = 2;
   private final double[] positions = {1.1, 2.2, 3.3};
   private final String errorMsg = "Text";

   @Before
   public void setUp() {
      when(sePositionResultMock.getAllPositions()).thenReturn(positions);
      when(sePositionResultMock.getErrorMsg()).thenReturn(errorMsg);
      when(seFrontendMock.getPositionsForCelBody(anyDouble(), anyInt(), anyInt())).thenReturn(sePositionResultMock);
   }

   @Test
   public void getPositions() {
      assertEquals(2.2, calculatedCelBody.getPositions(jdUt, celBodyToCalculate, flags)[1], delta);
   }

   @Test
   public void getErrorMsg() {
      double[] temp = calculatedCelBody.getPositions(jdUt, celBodyToCalculate, flags);
      assertEquals(errorMsg, calculatedCelBody.getErrorMsg());
   }
}