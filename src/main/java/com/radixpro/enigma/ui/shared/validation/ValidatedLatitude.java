/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

import lombok.Getter;
import lombok.NonNull;
import lombok.val;

public class ValidatedLatitude extends ValidatedInput {

   private static final int LAT_DEGREE_MIN = -89;
   private static final int LAT_DEGREE_MAX = 89;
   @Getter
   private double value;
   @Getter
   private int degrees;
   @Getter
   private int minutes;
   @Getter
   private int seconds;

   public ValidatedLatitude(@NonNull final String input) {
      super(input);
      validate();
   }

   @Override
   protected void validate() {
      val values = input.split(SEXAG_SEPARATOR);
      if (values.length == 2 || values.length == 3) {
         try {
            degrees = Integer.parseInt(values[0]);
            minutes = Integer.parseInt(values[1]);
            if (values.length == 3) seconds = Integer.parseInt(values[2]);
            else seconds = 0;
            validated = (degrees >= LAT_DEGREE_MIN && degrees <= LAT_DEGREE_MAX &&
                  minutes >= MINUTE_MIN && minutes <= MINUTE_MAX &&
                  seconds >= SECOND_MIN && seconds <= SECOND_MAX);
            if (validated) value = degrees + (double) minutes / MINUTES_PER_HOUR + (double) seconds / SECONDS_PER_HOUR;
         } catch (NumberFormatException nfe) {
            validated = false;
         }
         if (!validated) value = 0.0;
      }
   }

}
