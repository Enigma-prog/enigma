/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public class FullDateTime {

   private final SimpleDateTime dateTime;
   private final TimeZones timeZone;
   private final boolean dst;
   private final double offsetForLmt;

   public FullDateTime(final SimpleDateTime dateTime, final TimeZones timeZone, final boolean dst,
                       final double offsetForLmt) {
      this.dateTime = dateTime;
      this.timeZone = timeZone;
      this.dst = dst;
      this.offsetForLmt = offsetForLmt;
   }

   public SimpleDateTime getDateTime() {
      return dateTime;
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
}
