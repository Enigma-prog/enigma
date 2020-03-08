/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.be.astron.assist.HorizontalPosition;
import com.radixpro.enigma.ui.shared.glyphs.CelObject2GlyphMapper;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlainDmsValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlusMinusValue;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import lombok.Getter;
import lombok.NonNull;

/**
 * Wrapper around HorizontalPosition (azimuth and altitude); enables the use in a tableview.
 */
@Getter
public class PresentableHorizontalPosition {

   private String formattedAzimuth;
   private String formattedAltitude;
   private String celBodyGlyph;

   public PresentableHorizontalPosition(@NonNull final CelestialObjects celObject,
                                        @NonNull HorizontalPosition horizontalPosition) {
      createHorizontalPosition(celObject, horizontalPosition);
   }


   private void createHorizontalPosition(@NonNull final CelestialObjects celObject,
                                         @NonNull HorizontalPosition horizontalPosition) {
      formattedAzimuth = new PlainDmsValue(horizontalPosition.getAzimuth()).getFormattedPosition();
      formattedAltitude = new PlusMinusValue(horizontalPosition.getAltitude()).getFormattedPosition();
      celBodyGlyph = new CelObject2GlyphMapper().getGlyph(celObject);
   }
}
