/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.SimpleDateTime;

public class JulianDay {

   private final SeFrontend seFrontend;
   private double jdNrEt;
   private double jdNrUt;

   public JulianDay(final SimpleDateTime dateTime) {
      seFrontend = SeFrontend.getFrontend();
      calculateJdNr(dateTime);
   }

   private void calculateJdNr(final SimpleDateTime dateTime) {
      // Julian Day for ET [0], and Julian Day for UT [1]
      double[] jdNrs = seFrontend.getJulianDay(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(),
            dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(), dateTime.isGregorian());
      jdNrEt = jdNrs[0];
      jdNrUt = jdNrs[1];
   }

   /**
    * Julian Day number for Ephemeris time/ Dynamical time. This can be ignored for calculations by7 the SE as the
    * SE already handles this.
    *
    * @return calculated Julian day number for ET.
    */
   public double getJdNrEt() {
      return jdNrEt;
   }

   /**
    * Julian Day number for Universal Time
    *
    * @return calculated JUlian day number for UT.
    */
   public double getJdNrUt() {
      return jdNrUt;
   }
}
