/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.be.astron.main.JulianDay;
import com.radixpro.enigma.xchg.domain.SimpleDateTime;

public class DateTimeApi {

   private final SeFrontend seFrontend;

   public DateTimeApi() {
      seFrontend = SeFrontend.getFrontend();
   }

   public boolean checkDate(final int year, final int month, final int day, final boolean gregorian) {
      return seFrontend.isValidDate(year, month, day, gregorian);
   }

   public double getJulianDateTimeForEt(final SimpleDateTime dateTime) {
      return new JulianDay(dateTime).getJdNrEt();
   }

   public double getJulianDateTimeForUt(final SimpleDateTime dateTime) {
      return new JulianDay(dateTime).getJdNrUt();
   }
}
