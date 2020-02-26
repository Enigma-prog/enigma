/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

import lombok.Getter;
import lombok.NonNull;

public abstract class ValidatedInput {

   protected static final String DATE_SEPARATOR = "/";
   protected static final int HOUR_MIN = 0;
   protected static final int HOUR_MAX = 23;
   protected static final int MINUTE_MIN = 0;
   protected static final int MINUTE_MAX = 59;
   protected static final int MINUTES_PER_HOUR = 60;
   protected static final int SECOND_MIN = 0;
   protected static final int SECOND_MAX = 59;
   protected static final int SECONDS_PER_HOUR = 3600;
   protected static final String SEXAG_SEPARATOR = ":";
   protected final String input;
   @Getter
   protected boolean validated = false;

   public ValidatedInput(@NonNull final String input) {
      this.input = input;
   }

   protected abstract void validate();

}
