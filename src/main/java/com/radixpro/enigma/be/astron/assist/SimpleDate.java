/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

public class SimpleDate {
   private final int year;
   private final int month;
   private final int day;
   private final boolean gregorian;

   /**
    * DTO for a date
    *
    * @param year      the astronomical year
    * @param month     month, 1..12
    * @param day       day of month
    * @param gregorian true if gregorian calendar, false if julian calendar
    */
   public SimpleDate(final int year, final int month, final int day, final boolean gregorian) {
      this.year = year;
      this.month = month;
      this.day = day;
      this.gregorian = gregorian;
   }

   public int getYear() {
      return year;
   }

   public int getMonth() {
      return month;
   }

   public int getDay() {
      return day;
   }

   public boolean isGregorian() {
      return gregorian;
   }
}
