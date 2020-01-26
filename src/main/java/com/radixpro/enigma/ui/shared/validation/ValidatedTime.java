/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

public class ValidatedTime extends ValidatedInput {

   private double value;

   public ValidatedTime(final String input) {
      super(input);
      validate();
   }

   private void validate() {
      int hour, minute, second;
      String[] values = input.split(TIME_SEPARATOR);
      if (values.length == 2 || values.length == 3) {
         try {
            hour = Integer.parseInt(values[0]);
            minute = Integer.parseInt(values[1]);
            if (values.length == 3) second = Integer.parseInt(values[2]);
            else second = 0;
            validated = (hour >= HOUR_MIN && hour <= HOUR_MAX &&
                  minute >= MINUTE_MIN && minute <= MINUTE_MAX &&
                  second >= SECOND_MIN && second <= SECOND_MAX);
            if (validated) value = hour + (double) minute / MINUTES_PER_HOUR + (double) second / SECONDS_PER_HOUR;
            else value = 0.0;
         } catch (ArithmeticException ae) {
            validated = false;
            value = 0.0;
         }
      }
   }

   public double getValue() {
      return value;
   }

}
