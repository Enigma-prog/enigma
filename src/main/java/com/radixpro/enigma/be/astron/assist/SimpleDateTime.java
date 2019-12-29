/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;

public class SimpleDateTime {
   private SimpleDate date;
   private SimpleTime time;
   private double jdUt;

   public SimpleDateTime(final SeFrontend seFrontend, final SimpleDate simpleDate, final SimpleTime simpleTime) {
      this.date = simpleDate;
      this.time = simpleTime;
      calculateJd(seFrontend);
   }

   private void calculateJd(final SeFrontend seFrontend) {
      double[] jdValues = seFrontend.getJulianDay(date.getYear(), date.getMonth(), date.getDay(), time.getHour(),
            time.getMinute(), time.getSecond(), date.isGregorian());
      // jdValues[0] is for JD for Ephemris time/ Dynamical time and can be ignored as the SE already handles this
      jdUt = jdValues[1];
   }

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

   public double getJdUt() {
      return jdUt;
   }
}
