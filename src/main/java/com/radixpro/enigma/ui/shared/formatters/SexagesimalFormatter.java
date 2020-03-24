/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.formatters;

import com.radixpro.enigma.shared.Range;
import lombok.val;

import static com.radixpro.enigma.shared.EnigmaDictionary.*;

/**
 * Formatter for sexagesimal results.
 */
public class SexagesimalFormatter {

   private final int lengthOfIntegerPart;

   /**
    * Constructor, defines the length of the hours/degrees in the formatted result.
    *
    * @param lengthOfIntegerPart Expects 2 or 3, any other value is considered to be 3.
    */
   public SexagesimalFormatter(final int lengthOfIntegerPart) {
      this.lengthOfIntegerPart = lengthOfIntegerPart;
   }

   /**
    * Format a double into a string with degrees, minutes and seconds.
    *
    * @param value2Format the value to format.
    * @return the formatted string.
    */
   public String formatDms(final double value2Format) {
      return performFormatting(value2Format);
   }

   public String formatDm(final double value2Format) {
      return performFormatting(value2Format).substring(0, lengthOfIntegerPart + 4);
   }


   private String performFormatting(final double value2Format) {
      val tempValue = new Range(0.0, 360.0).checkValue(value2Format);
      val degHour = (int) tempValue;
      val fraction = tempValue - degHour;
      val fractionalMinute = fraction * 60.0;
      val minute = (int) fractionalMinute;
      val second = (int) ((fractionalMinute - minute) * 60.0);
      val degHourFormat = lengthOfIntegerPart == 2 ? "%02d" : "%03d";
      String content = degHourFormat + DEGREESIGN + "%02d" + MINUTESIGN + "%02d" + SECONDSIGN;
      return String.format(content, degHour, minute, second);
   }

}
