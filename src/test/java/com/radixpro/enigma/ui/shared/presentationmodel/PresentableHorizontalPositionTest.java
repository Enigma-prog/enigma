/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.be.astron.assist.HorizontalPosition;
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
public class PresentableHorizontalPositionTest {

   @Mock
   private HorizontalPosition horizontalPositionMock;
   private CelestialObjects celObject;
   private PresentableHorizontalPosition presHorPosition;

   @Before
   public void setUp() throws Exception {
      when(horizontalPositionMock.getAzimuth()).thenReturn(150.123456);
      when(horizontalPositionMock.getAltitude()).thenReturn(-12.5);
      celObject = CelestialObjects.JUPITER;
      presHorPosition = new PresentableHorizontalPosition(celObject, horizontalPositionMock);
   }

   @Test
   public void getFormattedAzimuth() {
      assertEquals("150" + DEGREESIGN + "07" + MINUTESIGN + "24" + SECONDSIGN,
            presHorPosition.getFormattedAzimuth());
   }

   @Test
   public void getFormattedAltitude() {
      assertEquals("-12" + DEGREESIGN + "30" + MINUTESIGN + "00" + SECONDSIGN,
            presHorPosition.getFormattedAltitude());
   }

   @Test
   public void getCelBodyGlyph() {
      assertEquals("g", presHorPosition.getCelBodyGlyph());
   }
}