/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

/**
 * Makes sure a number is in a specific range.
 */
public class Range {

   private double min;
   private double max;
   private double range;

   /**
    * Constructor defines min and max values.
    *
    * @param minValue Minimal value, inclusive.
    * @param maxValue Maximum value, exclusive.
    */
   public Range(final double minValue, final double maxValue) {
      if (minValue >= maxValue) throw new RuntimeException("INput for Range has wrong sequence");
      this.min = minValue;
      this.max = maxValue;
      range = max - min;
   }

   public double checkValue(final double inputValue) {
      double workValue = inputValue;
      while (workValue >= max) workValue = workValue - range;
      while (workValue < min) workValue = workValue + range;
      return workValue;
   }

}
