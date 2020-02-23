/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public class FullDateTime {

   private final SimpleDateTime fullDateTime;
   private final TimeZones timeZone;
   private final boolean dst;
   private final double offsetForLmt;
   private final double ut;

   public FullDateTime(final SimpleDateTime fullDateTime, final TimeZones timeZone, final boolean dst,
                       final double offsetForLmt) {
      this.fullDateTime = fullDateTime;
      this.timeZone = timeZone;
      this.dst = dst;
      this.offsetForLmt = offsetForLmt;
      this.ut = calculateUt();
   }

   public SimpleDateTime getFullDateTime() {
      return fullDateTime;
   }

   public TimeZones getTimeZone() {
      return timeZone;
   }

   public boolean isDst() {
      return dst;
   }

   public double getOffsetForLmt() {
      return offsetForLmt;
   }

   /**
    * UT can be negatieve in case of a date-shift.
    *
    * @return calcualted Universal Time.
    */
   public double getUt() {
      return ut;
   }

   private double calculateUt() {
      var calcUt = fullDateTime.getTime().getHour() + fullDateTime.getTime().getMinute() / 60.0
            + fullDateTime.getTime().getSecond() / 3600.0 + offsetForLmt + timeZone.getOffset();
      if (dst) calcUt++;
      return calcUt;
   }
}
