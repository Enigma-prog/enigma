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

   public SimpleDateTime(final SimpleDate simpleDate, final SimpleTime simpleTime) {
      this.date = simpleDate;
      this.time = simpleTime;
   }

   public SimpleDate getDate() {
      return date;
   }

   public SimpleTime getTime() {
      return time;
   }

   //   public int getYear() {
//      return date.getYear();
//   }
//
//   public int getMonth() {
//      return date.getMonth();
//   }
//
//   public int getDay() {
//      return date.getDay();
//   }
//
//   public int getHour() {
//      return time.getHour();
//   }
//
//   public int getMinute() {
//      return time.getMinute();
//   }
//
//   public int getSecond() {
//      return time.getSecond();
//   }
//
//   public TimeZones getTimeZone() { return time.getTimeZone(); }
//
//   public boolean isGregorian() {
//      return date.isGregorian();
//   }
//
//   public boolean isDst() { return time.isDst(); }

}
