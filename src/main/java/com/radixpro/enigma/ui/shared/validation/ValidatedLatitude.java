/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

public class ValidatedLatitude extends ValidatedInput {

   private final static int LAT_DEGREE_MIN = -89;
   private final static int LAT_DEGREE_MAX = 89;
   private double value;

   public ValidatedLatitude(final String input) {
      super(input);
      validate();
   }

   @Override
   protected void validate() {
      int degree;
      int minute;
      int second;
      String[] values = input.split(SEXAG_SEPARATOR);
      if (values.length == 2 || values.length == 3) {
         try {
            degree = Integer.parseInt(values[0]);
            minute = Integer.parseInt(values[1]);
            if (values.length == 3) second = Integer.parseInt(values[2]);
            else second = 0;
            validated = (degree >= LAT_DEGREE_MIN && degree <= LAT_DEGREE_MAX &&
                  minute >= MINUTE_MIN && minute <= MINUTE_MAX &&
                  second >= SECOND_MIN && second <= SECOND_MAX);
            if (validated) value = degree + (double) minute / MINUTES_PER_HOUR + (double) second / SECONDS_PER_HOUR;
         } catch (NumberFormatException nfe) {
            validated = false;
         }
         if (!validated) value = 0.0;
      }
   }

   public double getValue() {
      return value;
   }
}
