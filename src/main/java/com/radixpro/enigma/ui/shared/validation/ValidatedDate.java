/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

import com.radixpro.enigma.xchg.api.DateTimeApi;
import com.radixpro.enigma.xchg.domain.SimpleDate;

public class ValidatedDate extends ValidatedInput {

   private SimpleDate simpleDate;
   private DateTimeApi dateTimeApi;

   public ValidatedDate(final String input) {
      super(input);
      dateTimeApi = new DateTimeApi();
      validate();
   }

   @Override
   protected void validate() {
      int day, month, year;
      boolean gregorian;
      String[] values = input.split(DATE_SEPARATOR);
      if (values.length != 4) {
         validated = false;
      } else {
         try {
            year = Integer.parseInt(values[0]);
            month = Integer.parseInt(values[1]);
            day = Integer.parseInt(values[2]);
            gregorian = values[3].equalsIgnoreCase("g");
            validated = (dateTimeApi.checkDate(year, month, day, gregorian));
            if (validated) simpleDate = new SimpleDate(year, month, day, gregorian);
         } catch (NumberFormatException nfe) {
            validated = false;
         }
      }
      if (!validated) simpleDate = new SimpleDate(0, 1, 1, false);
   }

   public SimpleDate getSimpleDate() {
      return simpleDate;
   }
}
