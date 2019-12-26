/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.core.SeFrontend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateTimeTest {

   @Mock
   private SeFrontend seFrontendMock;
   private DateTime dateTime;
   private final int year = 2019;
   private final int month = 12;
   private final int day = 26;
   private final int hour = 12;
   private final int minute = 3;
   private final int second = 45;
   private final boolean gregorian = true;
   private final double jdEt = 12.34;
   private final double jdUt = 12.35;
   private final double delta = 0.00000001;

   @Before
   public void setUp() throws Exception {
      when(seFrontendMock.getJulianDay(anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(),
            anyBoolean())).thenReturn(new double[]{jdEt, jdUt});
      dateTime = new DateTime(seFrontendMock, year, month, day, hour, minute, second, gregorian);
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

   @Test
   public void getJdUt() {
      assertEquals(jdUt, dateTime.getJdUt(), delta);
   }

   @Test
   public void getJdEt() {
      assertEquals(jdEt, dateTime.getJdEt(), delta);
   }
}