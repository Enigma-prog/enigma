/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel.valuetypes;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * DTO that holds a formatted String with degrees, minutes and seconds, and the index of the zodiacal sign.
 */
@Getter
public class LongWithGlyph {

   private final String position;
   private final int signIndex;

   public LongWithGlyph(final String position, final int signIndex) {
      this.position = checkNotNull(position);
      this.signIndex = signIndex;
   }

}
