/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

import com.radixpro.enigma.xchg.domain.SimpleTime;

public class ValidatedTime extends ValidatedInput {

   private SimpleTime simpleTime;

   public ValidatedTime(final String input) {
      super(input);
      validate();
   }

   @Override
   protected void validate() {
      int hour;
      int minute;
      int second;
      String[] values = input.split(SEXAG_SEPARATOR);
      if (values.length == 2 || values.length == 3) {
         try {
            hour = Integer.parseInt(values[0]);
            minute = Integer.parseInt(values[1]);
            if (values.length == 3) second = Integer.parseInt(values[2]);
            else second = 0;
            validated = (hour >= HOUR_MIN && hour <= HOUR_MAX &&
                  minute >= MINUTE_MIN && minute <= MINUTE_MAX &&
                  second >= SECOND_MIN && second <= SECOND_MAX);
            if (validated) simpleTime = new SimpleTime(hour, minute, second); // dummy values for timezone and dst
         } catch (NumberFormatException nfe) {
            validated = false;
         }
      }
      if (!validated) simpleTime = new SimpleTime(0, 0, 0);
   }

   public SimpleTime getSimpleTime() {
      return simpleTime;
   }

}
