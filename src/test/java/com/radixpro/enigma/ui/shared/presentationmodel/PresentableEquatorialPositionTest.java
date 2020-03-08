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

import static com.radixpro.enigma.shared.EnigmaDictionary.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PresentableEquatorialPositionTest {

   @Mock
   private CelObjectSinglePosition positionMock;
   private CelestialObjects celestialObject;
   private PresentableEquatorialPosition eqPosition;

   @Before
   public void setUp() throws Exception {
      when(positionMock.getMainPosition()).thenReturn(100.5);
      when(positionMock.getDeviationPosition()).thenReturn(-0.25);
      when(positionMock.getMainSpeed()).thenReturn(-1.3333334);
      when(positionMock.getDeviationSpeed()).thenReturn(0.0528);
      celestialObject = CelestialObjects.VENUS;
      eqPosition = new PresentableEquatorialPosition(celestialObject, positionMock);
   }

   @Test
   public void getFormattedRightAscension() {
      assertEquals("100" + DEGREESIGN + "30" + MINUTESIGN + "00" + SECONDSIGN,
            eqPosition.getFormattedRightAscension());
   }

   @Test
   public void getFormattedRaSpeed() {
      assertEquals(" -1" + DEGREESIGN + "20" + MINUTESIGN + "00" + SECONDSIGN,
            eqPosition.getFormattedRaSpeed());
   }

   @Test
   public void getFormattedDeclination() {
      assertEquals(" -0" + DEGREESIGN + "15" + MINUTESIGN + "00" + SECONDSIGN,
            eqPosition.getFormattedDeclination());
   }

   @Test
   public void getFormattedDeclSpeed() {
      assertEquals(" +0" + DEGREESIGN + "03" + MINUTESIGN + "10" + SECONDSIGN,
            eqPosition.getFormattedDeclSpeed());
   }

   @Test
   public void getCelBodyGlyph() {
      assertEquals("d", eqPosition.getCelBodyGlyph());
   }

}