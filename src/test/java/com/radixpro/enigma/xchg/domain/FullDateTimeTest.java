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

@RunWith(MockitoJUnitRunner.class)
public class FullDateTimeTest {

   private static final double DELTA = 0.00000001;
   @Mock
   private SimpleDateTime dateTimeMock;
   private TimeZones timeZone = TimeZones.LMT;
   private boolean dst = false;
   private double offsetForLmt = 0.115;
   private FullDateTime dateTime;

   @Before
   public void setUp() {
      dateTime = new FullDateTime(dateTimeMock, timeZone, dst, offsetForLmt);
   }

   @Test
   public void getDateTime() {
      assertEquals(dateTimeMock, dateTime.getDateTime());
   }

   @Test
   public void getTimeZone() {
      assertEquals(timeZone, dateTime.getTimeZone());
   }

   @Test
   public void isDst() {
      assertEquals(dst, dateTime.isDst());
   }

   @Test
   public void getOffsetForLmt() {
      assertEquals(offsetForLmt, dateTime.getOffsetForLmt(), DELTA);
   }
}