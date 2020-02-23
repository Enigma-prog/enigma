/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FullDateTimeTest {

   private static final double DELTA = 0.00000001;
   @Mock
   private SimpleDateTime simpleDateTimeMock;
   @Mock
   private SimpleTime simpleTimeMock;
   private TimeZones timeZone = TimeZones.LMT;
   private boolean dst = false;
   private double offsetForLmt = 0.115;
   private FullDateTime fullDateTime;

   @Before
   public void setUp() {
      when(simpleTimeMock.getHour()).thenReturn(15);
      when(simpleTimeMock.getMinute()).thenReturn(0);
      when(simpleTimeMock.getSecond()).thenReturn(0);
      when(simpleDateTimeMock.getTime()).thenReturn(simpleTimeMock);
      fullDateTime = new FullDateTime(simpleDateTimeMock, timeZone, dst, offsetForLmt);
   }

   @Test
   public void getDateTime() {
      assertEquals(simpleDateTimeMock, fullDateTime.getFullDateTime());
   }

   @Test
   public void getTimeZone() {
      assertEquals(timeZone, fullDateTime.getTimeZone());
   }

   @Test
   public void isDst() {
      assertEquals(dst, fullDateTime.isDst());
   }

   @Test
   public void getOffsetForLmt() {
      assertEquals(offsetForLmt, fullDateTime.getOffsetForLmt(), DELTA);
   }

   @Test
   public void getUtHappyFlow() {
      fullDateTime = new FullDateTime(simpleDateTimeMock, TimeZones.UT, false, 0.0);
      assertEquals(15.0, fullDateTime.getUt(), DELTA);
   }

   @Test
   public void getUtForLmt() {
      fullDateTime = new FullDateTime(simpleDateTimeMock, TimeZones.LMT, false, -5.0);
      assertEquals(10.0, fullDateTime.getUt(), DELTA);
   }

   @Test
   public void getUtWithDst() {
      fullDateTime = new FullDateTime(simpleDateTimeMock, TimeZones.UT, true, 0.0);
      assertEquals(16.0, fullDateTime.getUt(), DELTA);
   }

   @Test
   public void getUtPreviousDay() {
      when(simpleTimeMock.getHour()).thenReturn(3);
      fullDateTime = new FullDateTime(simpleDateTimeMock, TimeZones.EST, false, 0.0);
      assertEquals(-2.0, fullDateTime.getUt(), DELTA);
   }

   @Test
   public void getUtNextDay() {
      when(simpleTimeMock.getHour()).thenReturn(23);
      fullDateTime = new FullDateTime(simpleDateTimeMock, TimeZones.ICT, false, 0.0);
      assertEquals(30.0, fullDateTime.getUt(), DELTA);
   }

}