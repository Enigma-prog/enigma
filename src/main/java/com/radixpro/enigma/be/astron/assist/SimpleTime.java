/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

public class SimpleTime {
   private final int hour;
   private final int minute;
   private final int second;

   public SimpleTime(final int hour, final int minute, final int second) {
      this.hour = hour;
      this.minute = minute;
      this.second = second;
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
}
