/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.xchg.domain.CelObjectSinglePosition;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PresentableDistancePositionTest {

   @Mock
   private CelObjectSinglePosition celObjectSinglePositionMock;
   private CelestialObjects celObject;
   private PresentableDistancePosition presDistPos;

   @Before
   public void setUp() throws Exception {
      when(celObjectSinglePositionMock.getDistancePosition()).thenReturn(8.532897658210534);
      when(celObjectSinglePositionMock.getDistanceSpeed()).thenReturn(0.5);
      celObject = CelestialObjects.SATURN;
      presDistPos = new PresentableDistancePosition(celObject, celObjectSinglePositionMock);
   }

   @Test
   public void getFormattedDistance() {
      assertEquals("  8.53289765", presDistPos.getFormattedDistance());
   }

   @Test
   public void getFormattedDistSpeed() {
      assertEquals("  0.50000000", presDistPos.getFormattedDistSpeed());
   }

   @Test
   public void getFormattedDistSpeedNegative() {
      when(celObjectSinglePositionMock.getDistanceSpeed()).thenReturn(-0.5);
      presDistPos = new PresentableDistancePosition(celObject, celObjectSinglePositionMock);
      assertEquals(" -0.50000000", presDistPos.getFormattedDistSpeed());
   }

   @Test
   public void getCelBodyGlyph() {
      assertEquals("h", presDistPos.getCelBodyGlyph());
   }
}