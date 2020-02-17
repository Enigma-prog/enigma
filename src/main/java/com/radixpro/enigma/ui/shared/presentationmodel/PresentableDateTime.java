/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.xchg.domain.SimpleDateTime;

/**
 * Presents date and time as Strings.
 */
public class PresentableDateTime {

   private final String date;
   private final String time;

   public PresentableDateTime(final SimpleDateTime dateTime) {
      date = constructDateText(dateTime);
      time = constructTimeText(dateTime);
   }

   private String constructDateText(final SimpleDateTime dateTime) {
      final int year = dateTime.getYear();
      final int month = dateTime.getMonth();
      final int day = dateTime.getDay();
      final String cal = dateTime.isGregorian() ? "G" : "J";
      return String.format("%04d/%02d/%02d %s", year, month, day, cal);
   }

   private String constructTimeText(final SimpleDateTime dateTime) {
      final int hour = dateTime.getHour();
      final int minute = dateTime.getMinute();
      final int second = dateTime.getSecond();
      return String.format("%02d:%02d:%02d", hour, minute, second);
   }

   public String getDate() {
      return date;
   }

   public String getTime() {
      return time;
   }
}
