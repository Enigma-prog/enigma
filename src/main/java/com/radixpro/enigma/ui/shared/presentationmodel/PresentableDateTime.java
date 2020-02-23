/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.xchg.domain.FullDateTime;
import com.radixpro.enigma.xchg.domain.SimpleDate;

/**
 * Presents date and time as Strings.
 */
public class PresentableDateTime {

   private final String date;
   private final String time;

   public PresentableDateTime(final FullDateTime dateTime) {
      date = constructDateText(dateTime.getFullDateTime().getDate());
      time = constructTimeText(dateTime);
   }

   private String constructDateText(final SimpleDate date) {
      final int year = date.getYear();
      final int month = date.getMonth();
      final int day = date.getDay();
      final String cal = date.isGregorian() ? "G" : "J";
      return String.format("%04d/%02d/%02d %s", year, month, day, cal);
   }

   private String constructTimeText(final FullDateTime fullDateTime) {
      final int hour = fullDateTime.getFullDateTime().getTime().getHour();
      final int minute = fullDateTime.getFullDateTime().getTime().getMinute();
      final int second = fullDateTime.getFullDateTime().getTime().getSecond();
      final String zoneTxt = Rosetta.getRosetta().getText(fullDateTime.getTimeZone().getRbKeyForName());
      String dstKey = fullDateTime.isDst() ? "ui.shared.dst" : "ui.shared.nodst";
      String dstTxt = Rosetta.getRosetta().getText(dstKey);
      return String.format("%02d:%02d:%02d %s %s", hour, minute, second, dstTxt, zoneTxt);
   }

   public String getDate() {
      return date;
   }

   public String getTime() {
      return time;
   }
}
