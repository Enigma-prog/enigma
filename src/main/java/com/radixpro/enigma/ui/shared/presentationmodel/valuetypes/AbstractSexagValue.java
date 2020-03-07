/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel.valuetypes;

import lombok.val;

import static com.radixpro.enigma.shared.EnigmaDictionary.*;

/**
 * Parent for classes that construct a formatted value in a sexagesimal format.
 */
public abstract class AbstractSexagValue extends AbstractValueType {

   protected int lengthOfIntegerPart = 3;

   public AbstractSexagValue(final double value) {
      super(value);
   }

   protected abstract void performFormatting();

   protected String performSexagFormatting(final double workValue) {
      double posValue = Math.abs(workValue);
      val degHour = (int) posValue;
      val fraction = posValue - degHour;
      val fractionalMinute = fraction * 60.0;
      val minute = (int) fractionalMinute;
      val second = (int) ((fractionalMinute - minute) * 60.0);
      String degHourFormat;
      degHourFormat = lengthOfIntegerPart == 2 ? "%2d" : "%3d";
      String content = degHourFormat + DEGREESIGN + "%02d" + MINUTESIGN + "%02d" + SECONDSIGN;
      return String.format(content, degHour, minute, second);
   }

}
