/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.main.EquatorialPosition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FullPositionResultHousesTest {

   private static final double DELTA = 0.00000001;
   private final double longitude = 123.456;
   @Mock
   private EquatorialPosition equatorialPositionMock;
   private FullPositionResultHouses fullPositionResultHouses;

   @Before
   public void setUp() {
      fullPositionResultHouses = new FullPositionResultHouses(longitude, equatorialPositionMock);
   }

   @Test
   public void getEquatorialPosition() {
      assertEquals(equatorialPositionMock, fullPositionResultHouses.getEquatorialPosition());
   }

   @Test
   public void getLongitude() {
      assertEquals(longitude, fullPositionResultHouses.getLongitude(), DELTA);
   }
}