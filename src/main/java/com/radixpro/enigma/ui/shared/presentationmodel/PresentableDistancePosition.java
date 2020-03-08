/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.ui.shared.glyphs.CelObject2GlyphMapper;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.DecimalValue;
import com.radixpro.enigma.xchg.domain.CelObjectSinglePosition;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import lombok.Getter;
import lombok.NonNull;

/**
 * Wrapper around CelObjectSinglePosition for the distance values; enables the use in a tableview.
 */
@Getter
public class PresentableDistancePosition {

   private String formattedDistance;
   private String formattedDistSpeed;
   private String celBodyGlyph;

   public PresentableDistancePosition(@NonNull final CelestialObjects celestialObject,
                                      @NonNull final CelObjectSinglePosition celObjectSinglePosition) {
      createPresentablePosition(celestialObject, celObjectSinglePosition);
   }

   private void createPresentablePosition(@NonNull final CelestialObjects celestialObject,
                                          @NonNull final CelObjectSinglePosition celObjectSinglePosition) {
      formattedDistance = new DecimalValue(celObjectSinglePosition.getDistancePosition()).getFormattedPosition();
      formattedDistSpeed = new DecimalValue(celObjectSinglePosition.getDistanceSpeed()).getFormattedPosition();
      celBodyGlyph = new CelObject2GlyphMapper().getGlyph(celestialObject);
   }
}
