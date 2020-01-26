/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatedTimeTest {

   private static final double MARGIN = 0.00000001;
   private ValidatedTime time;
   private final String timeTextOk = "11:45:48";
   private final String timeTextSingleDigits = "1:2:3";
   private final String timeTextHour2Large = "25:45:38";
   private final String timeTextHour2Small = "-1:45:38";
   private final String timeTextMinute2Large = "11:65:48";
   private final String timeTextMinute2Small = "11:-1:48";
   private final String timeTextSecond2Large = "11:45:60";
   private final String timeTextSecond2Small = "11:45:-3";
   private final String timeTextNoSecond = "11:45";
   private final String timeTextNotNumeric = "Enigma";


   @Test
   public void happyFlow() {
      time = new ValidatedTime(timeTextOk);
      assertTrue(time.isValidated());
      assertEquals(11.763333333333333, time.getValue(), MARGIN);
   }

   @Test
   public void singleDigits() {
      time = new ValidatedTime(timeTextSingleDigits);
      assertTrue(time.isValidated());
      assertEquals(1.03416666666666667, time.getValue(), MARGIN);
   }

   @Test
   public void happyFlowNoSecond() {
      time = new ValidatedTime(timeTextNoSecond);
      assertTrue(time.isValidated());
      assertEquals(11.75, time.getValue(), MARGIN);
   }

   @Test
   public void hour2Large() {
      time = new ValidatedTime(timeTextHour2Large);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }

   @Test
   public void hour2Small() {
      time = new ValidatedTime(timeTextHour2Small);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }

   @Test
   public void minute2Large() {
      time = new ValidatedTime(timeTextMinute2Large);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }

   @Test
   public void minute2Small() {
      time = new ValidatedTime(timeTextMinute2Small);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }

   @Test
   public void second2Large() {
      time = new ValidatedTime(timeTextSecond2Large);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }

   @Test
   public void second2Small() {
      time = new ValidatedTime(timeTextSecond2Small);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }

   @Test
   public void notNumeric() {
      time = new ValidatedTime(timeTextNotNumeric);
      assertFalse(time.isValidated());
      assertEquals(0.0, time.getValue(), MARGIN);
   }


}
