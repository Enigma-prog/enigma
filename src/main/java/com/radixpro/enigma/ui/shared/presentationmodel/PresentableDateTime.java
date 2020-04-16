/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.xchg.domain.FullDateTime;
import com.radixpro.enigma.xchg.domain.SimpleDate;
import lombok.Getter;
import lombok.val;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Presents date and time as Strings.
 */
@Getter
public class PresentableDateTime {

   private final String date;
   private final String time;

   public PresentableDateTime(final FullDateTime dateTime) {
      checkNotNull(dateTime);
      date = constructDateText(dateTime.getSimpleDateTime().getDate());
      time = constructTimeText(dateTime);
   }

   private String constructDateText(final SimpleDate date) {
      val year = date.getYear();
      val month = date.getMonth();
      val day = date.getDay();
      val cal = date.isGregorian() ? "G" : "J";
      return String.format("%04d/%02d/%02d %s", year, month, day, cal);
   }

   private String constructTimeText(final FullDateTime fullDateTime) {
      val hour = fullDateTime.getSimpleDateTime().getTime().getHour();
      val minute = fullDateTime.getSimpleDateTime().getTime().getMinute();
      val second = fullDateTime.getSimpleDateTime().getTime().getSecond();
      val zoneTxt = Rosetta.getRosetta().getText(fullDateTime.getTimeZone().getNameForRB());
      val dstKey = fullDateTime.isDst() ? "ui.shared.dst" : "ui.shared.nodst";
      val dstTxt = Rosetta.getRosetta().getText(dstKey);
      return String.format("%02d:%02d:%02d %s %s", hour, minute, second, dstTxt, zoneTxt);
   }

}
