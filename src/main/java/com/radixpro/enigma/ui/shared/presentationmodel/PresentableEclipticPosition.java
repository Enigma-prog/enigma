/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.ui.shared.glyphs.CelObject2GlyphMapper;
import com.radixpro.enigma.ui.shared.glyphs.Sign2GlyphMapper;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.LongAndGlyphValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlusMinusValue;
import com.radixpro.enigma.xchg.domain.CelObjectSinglePosition;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Wrapper around CelObjectSinglePosition for the ecliptic values; enables the use in a tableview.
 */
@Getter
public class PresentableEclipticPosition {

   private String formattedLongitude;
   private String formattedLongSpeed;
   private String formattedLatitude;
   private String formattedLatSpeed;
   private String signGlyph;
   private String celBodyGlyph;


   public PresentableEclipticPosition(@NonNull final CelestialObjects celestialObject,
                                      @NonNull final CelObjectSinglePosition celObjectSinglePosition) {
      createPresentablePosition(celestialObject, celObjectSinglePosition);
   }

   private void createPresentablePosition(@NonNull final CelestialObjects celestialObject,
                                          @NonNull final CelObjectSinglePosition celObjectSinglePosition) {
      val mainPosition = celObjectSinglePosition.getMainPosition();
      val longWithGlyph = new LongAndGlyphValue(mainPosition).getLongWithGlyph();
      formattedLongitude = longWithGlyph.getPosition();
      signGlyph = new Sign2GlyphMapper().getGlyph(longWithGlyph.getSignIndex());
      formattedLongSpeed = new PlusMinusValue(celObjectSinglePosition.getMainSpeed()).getFormattedPosition();
      formattedLatitude = new PlusMinusValue(celObjectSinglePosition.getDeviationPosition()).getFormattedPosition();
      formattedLatSpeed = new PlusMinusValue(celObjectSinglePosition.getDeviationSpeed()).getFormattedPosition();
      celBodyGlyph = new CelObject2GlyphMapper().getGlyph(celestialObject);
   }


}
