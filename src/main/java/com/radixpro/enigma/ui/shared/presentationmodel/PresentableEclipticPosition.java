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
import lombok.val;

import static com.google.common.base.Preconditions.checkNotNull;

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


   public PresentableEclipticPosition(final CelestialObjects celestialObject,
                                      final CelObjectSinglePosition celObjectSinglePosition) {
      checkNotNull(celestialObject);
      checkNotNull(celObjectSinglePosition);
      createPresentablePosition(celestialObject, celObjectSinglePosition);
   }

   private void createPresentablePosition(final CelestialObjects celestialObject,
                                          final CelObjectSinglePosition celObjectSinglePosition) {
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
