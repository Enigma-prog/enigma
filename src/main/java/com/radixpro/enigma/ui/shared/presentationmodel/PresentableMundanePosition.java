/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.be.astron.assist.HousePosition;
import com.radixpro.enigma.ui.shared.glyphs.Sign2GlyphMapper;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.LongAndGlyphValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlainDmsValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlusMinusValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Wrapper around HousePosition; enables the use in a tableview.
 */
@Getter
public class PresentableMundanePosition {

   private final String name;
   private String formattedLongitude;
   private String formattedRa;
   private String formattedDeclination;
   private String formattedAzimuth;
   private String formattedAltitude;
   private String signGlyph;

   /**
    * The constructor populates all properties.
    *
    * @param name     Name for the mundane position. Possibly a number for the cusp, an acronym for vertex etc.
    * @param position An instance of HousePosition that contains the data that must be presented.
    */
   public PresentableMundanePosition(@NonNull final String name, @NonNull final HousePosition position) {
      this.name = name;
      createMundanePosition(name, position);
   }

   private void createMundanePosition(@NonNull final String name, @NonNull final HousePosition position) {
      val longWithGlyph = new LongAndGlyphValue(position.getLongitude()).getLongWithGlyph();
      formattedLongitude = longWithGlyph.getPosition();
      signGlyph = new Sign2GlyphMapper().getGlyph(longWithGlyph.getSignIndex());
      formattedRa = new PlainDmsValue(position.getEquatorialPosition().getRightAscension()).getFormattedPosition();
      formattedDeclination = new PlusMinusValue(position.getEquatorialPosition().getDeclination()).getFormattedPosition();
      formattedAzimuth = new PlainDmsValue(position.getHorizontalPosition().getAzimuth()).getFormattedPosition();
      formattedAltitude = new PlusMinusValue(position.getHorizontalPosition().getAltitude()).getFormattedPosition();
   }

}
