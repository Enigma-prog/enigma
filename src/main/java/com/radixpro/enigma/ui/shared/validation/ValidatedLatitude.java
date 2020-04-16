/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

/**
 * Validation for latitude. Latitude should be in the format h:m:s (degrees -89..+89, minutes 0..59, seconds 0..59).
 * Seconds are optional. Negative degrees indicate south latitude, positive degrees indicate north latitude.
 */
public class ValidatedLatitude extends ValidatedInput {

   private double value;
   private int degrees;
   private int minutes;
   private int seconds;

   /**
    * The constructor performs the validation.
    *
    * @param input The latitude to validate.
    */
   public ValidatedLatitude(final String input) {
      super(input);
      validate();
   }

   @Override
   protected void validate() {
      String[] values = input.split(SEXAG_SEPARATOR);
      if (values.length == 2 || values.length == 3) {
         try {
            degrees = Integer.parseInt(values[0]);
            minutes = Integer.parseInt(values[1]);
            seconds = values.length == 3 ? Integer.parseInt(values[2]) : 0;
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

   public double getValue() {
      return this.value;
   }

   public int getDegrees() {
      return this.degrees;
   }

   public int getMinutes() {
      return this.minutes;
   }

   public int getSeconds() {
      return this.seconds;
   }
}
