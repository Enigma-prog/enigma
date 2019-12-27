/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;

public class DateTime {
   private final int year;
   private final int month;
   private final int day;
   private final int hour;
   private final int minute;
   private final int second;
   private final boolean gregorian;
   private double jdUt;
   private double jdEt;

   public DateTime(final SeFrontend seFrontend, final int year, final int month, final int day, final int hour,
                   final int minute, final int second, final boolean gregorian) {
      this.year = year;
      this.month = month;
      this.day = day;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
      this.gregorian = gregorian;
      calculateJd(seFrontend);
   }

   private void calculateJd(final SeFrontend seFrontend) {
      double[] jdValues = seFrontend.getJulianDay(year, month, day, hour, minute, second, gregorian);
      jdEt = jdValues[0];
      jdUt = jdValues[1];
   }

   public int getYear() {
      return year;
   }

   public int getMonth() {
      return month;
   }

   public int getDay() {
      return day;
   }

   public int getHour() {
      return hour;
   }

   public int getMinute() {
      return minute;
   }

   public int getSecond() {
      return second;
   }

   public boolean isGregorian() {
      return gregorian;
   }

   public double getJdUt() {
      return jdUt;
   }

   public double getJdEt() {
      return jdEt;
   }
}
