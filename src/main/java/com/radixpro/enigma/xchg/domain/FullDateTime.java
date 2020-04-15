/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.be.astron.main.JulianDay;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * All data for date and time, both for calculation and presentation.
 */
@Getter
public class FullDateTime {

   private final SimpleDateTime simpleDateTime;
   private final TimeZones timeZone;
   private final boolean dst;
   private final double offsetForLmt;
   private final double jdUt;
   private final double jdEt;

   /**
    * Constructor defines all members.
    *
    * @param simpleDateTime Populated instance of SimpleDateTime.
    * @param timeZone       Instance from enum TimeZones which includes offset for UT in decimal hours.
    * @param dst            True if dst applies, otherwise false, assumed is dst is always one hour.
    * @param offsetForLmt   If timezone is LMT, this field should present the offset to UT in decimal hours.
    */
   public FullDateTime(final SimpleDateTime simpleDateTime, final TimeZones timeZone, final boolean dst,
                       final double offsetForLmt) {
      this.simpleDateTime = checkNotNull(simpleDateTime);
      this.timeZone = checkNotNull(timeZone);
      this.dst = dst;
      this.offsetForLmt = offsetForLmt;
      double utDelta = calculateUtDelta();
      JulianDay julianDay = new JulianDay(simpleDateTime);
      jdUt = julianDay.getJdNrUt() - utDelta / 24.0;
      jdEt = julianDay.getJdNrEt() - utDelta / 24.0;
   }

   private double calculateUtDelta() {
      double utDelta = (TimeZones.LMT == timeZone) ? offsetForLmt : timeZone.getOffset();
      if (dst) utDelta++;
      return utDelta;
   }

}
