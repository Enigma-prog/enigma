/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.Serializable;

public class SimpleDateTime implements Serializable {
   private final SimpleDate date;
   private final SimpleTime time;
//   private double jdUt;

   public SimpleDateTime(final SimpleDate simpleDate, final SimpleTime simpleTime) {
      this.date = simpleDate;
      this.time = simpleTime;
//      calculateJd(SeFrontend.getFrontend());
   }
//
//   private void calculateJd(final SeFrontend seFrontend) {
//      double[] jdValues = seFrontend.getJulianDay(date.getYear(), date.getMonth(), date.getDay(), time.getHour(),
//            time.getMinute(), time.getSecond(), date.isGregorian());
//      // jdValues[0] is for JD for Ephemeris time/ Dynamical time and can be ignored as the SE already handles this
//      jdUt = jdValues[1];
//   }

   public int getYear() {
      return date.getYear();
   }

   public int getMonth() {
      return date.getMonth();
   }

   public int getDay() {
      return date.getDay();
   }

   public int getHour() {
      return time.getHour();
   }

   public int getMinute() {
      return time.getMinute();
   }

   public int getSecond() {
      return time.getSecond();
   }

   public boolean isGregorian() {
      return date.isGregorian();
   }

//   public double getJdUt() {
//      return jdUt;
//   }
}
