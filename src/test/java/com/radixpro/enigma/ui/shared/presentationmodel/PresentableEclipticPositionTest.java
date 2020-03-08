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
public class PresentableEclipticPositionTest {

   @Mock
   private CelObjectSinglePosition positionMock;
   private CelestialObjects celestialObject;
   private PresentableEclipticPosition eclPosition;

   @Before
   public void setUp() throws Exception {
      when(positionMock.getMainPosition()).thenReturn(220.5);
      when(positionMock.getDeviationPosition()).thenReturn(2.25);
      when(positionMock.getMainSpeed()).thenReturn(0.3333334);
      when(positionMock.getDeviationSpeed()).thenReturn(-0.0528);
      celestialObject = CelestialObjects.MARS;
      eclPosition = new PresentableEclipticPosition(celestialObject, positionMock);
   }

   @Test
   public void getFormattedLongitude() {
      assertEquals("10" + DEGREESIGN + "30" + MINUTESIGN + "00" + SECONDSIGN,
            eclPosition.getFormattedLongitude());
   }

   @Test
   public void getFormattedLongSpeed() {
      assertEquals(" +0" + DEGREESIGN + "20" + MINUTESIGN + "00" + SECONDSIGN,
            eclPosition.getFormattedLongSpeed());
   }

   @Test
   public void getFormattedLatitude() {
      assertEquals(" +2" + DEGREESIGN + "15" + MINUTESIGN + "00" + SECONDSIGN,
            eclPosition.getFormattedLatitude());

   }

   @Test
   public void getFormattedLatSpeed() {
      assertEquals(" -0" + DEGREESIGN + "03" + MINUTESIGN + "10" + SECONDSIGN,
            eclPosition.getFormattedLatSpeed());
   }

   @Test
   public void getSignGlyph() {
      assertEquals("8", eclPosition.getSignGlyph());
   }

   @Test
   public void getCelBodyGlyph() {
      assertEquals("f", eclPosition.getCelBodyGlyph());
   }
}