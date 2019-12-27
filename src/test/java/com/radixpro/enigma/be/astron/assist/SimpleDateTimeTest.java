/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleDateTimeTest {

   @Mock
   private SeFrontend seFrontendMock;
   @Mock
   private SimpleDate simpleDateMock;
   @Mock
   private SimpleTime simpleTimeMock;
   private SimpleDateTime simpleDateTime;
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
   public void setUp() {
      when(simpleDateMock.getYear()).thenReturn(year);
      when(simpleDateMock.getMonth()).thenReturn(month);
      when(simpleDateMock.getDay()).thenReturn(day);
      when(simpleDateMock.isGregorian()).thenReturn(gregorian);
      when(simpleTimeMock.getHour()).thenReturn(hour);
      when(simpleTimeMock.getMinute()).thenReturn(minute);
      when(simpleTimeMock.getSecond()).thenReturn(second);
      when(seFrontendMock.getJulianDay(anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(),
            anyBoolean())).thenReturn(new double[]{jdEt, jdUt});
      simpleDateTime = new SimpleDateTime(seFrontendMock, simpleDateMock, simpleTimeMock);
   }

   @Test
   public void getYear() {
      assertEquals(year, simpleDateTime.getYear());
   }

   @Test
   public void getMonth() {
      assertEquals(month, simpleDateTime.getMonth());
   }

   @Test
   public void getDay() {
      assertEquals(day, simpleDateTime.getDay());
   }

   @Test
   public void getHour() {
      assertEquals(hour, simpleDateTime.getHour());
   }

   @Test
   public void getMinute() {
      assertEquals(minute, simpleDateTime.getMinute());
   }

   @Test
   public void getSecond() {
      assertEquals(second, simpleDateTime.getSecond());
   }

   @Test
   public void isGregorian() {
      assertEquals(gregorian, simpleDateTime.isGregorian());
   }

   @Test
   public void getJdUt() {
      assertEquals(jdUt, simpleDateTime.getJdUt(), delta);
   }

   @Test
   public void getJdEt() {
      assertEquals(jdEt, simpleDateTime.getJdEt(), delta);
   }
}