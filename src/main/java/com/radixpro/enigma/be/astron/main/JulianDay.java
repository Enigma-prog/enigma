/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.SimpleDateTime;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Julian Day numbers. HAndles JD nrs for Ephermeris Time (jdnrEt) and Universal Time (jdnrUt).
 */
public class JulianDay {

   private final SeFrontend seFrontend;
   @Getter
   private double jdNrEt;
   @Getter
   private double jdNrUt;

   /**
    * Constructor calculates the julian day numbers,
    *
    * @param dateTime Instance of datetime in UT.
    */
   public JulianDay(@NonNull final SimpleDateTime dateTime) {
      seFrontend = SeFrontend.getFrontend();
      calculateJdNr(dateTime);
   }

   private void calculateJdNr(@NonNull final SimpleDateTime dateTime) {
      // Julian Day for ET [0], and Julian Day for UT [1]
      val date = dateTime.getDate();
      val time = dateTime.getTime();
      double[] jdNrs = seFrontend.getJulianDay(date.getYear(), date.getMonth(), date.getDay(),
            time.getHour(), time.getMinute(), time.getSecond(), date.isGregorian());
      jdNrEt = jdNrs[0];
      jdNrUt = jdNrs[1];
   }

}
