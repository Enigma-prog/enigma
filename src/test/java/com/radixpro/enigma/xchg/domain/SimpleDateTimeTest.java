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
public class SimpleDateTimeTest {

   private final int year = 2000;
   private final int month = 2;
   private final int day = 12;
   private final boolean gregorian = true;
   private final int hour = 16;
   private final int minute = 15;
   private final int second = 48;
   @Mock
   private SimpleDate dateMock;
   @Mock
   private SimpleTime timeMock;
   private SimpleDateTime dateTime;

   @Before
   public void setUp() throws Exception {
      when(dateMock.getYear()).thenReturn(year);
      when(dateMock.getMonth()).thenReturn(month);
      when(dateMock.getDay()).thenReturn(day);
      when(dateMock.isGregorian()).thenReturn(gregorian);
      when(timeMock.getHour()).thenReturn(hour);
      when(timeMock.getMinute()).thenReturn(minute);
      when(timeMock.getSecond()).thenReturn(second);
      dateTime = new SimpleDateTime(dateMock, timeMock);
   }

   @Test
   public void getYear() {
      assertEquals(year, dateTime.getYear());
   }

   @Test
   public void getMonth() {
      assertEquals(month, dateTime.getMonth());
   }

   @Test
   public void getDay() {
      assertEquals(day, dateTime.getDay());
   }

   @Test
   public void getHour() {
      assertEquals(hour, dateTime.getHour());
   }

   @Test
   public void getMinute() {
      assertEquals(minute, dateTime.getMinute());
   }

   @Test
   public void getSecond() {
      assertEquals(second, dateTime.getSecond());
   }

   @Test
   public void isGregorian() {
      assertEquals(gregorian, dateTime.isGregorian());
   }
}