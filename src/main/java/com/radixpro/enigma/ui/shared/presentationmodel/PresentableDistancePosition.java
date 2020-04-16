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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Wrapper around CelObjectSinglePosition for the distance values; enables the use in a tableview.
 */
public class PresentableDistancePosition {

   private String formattedDistance;
   private String formattedDistSpeed;
   private String celBodyGlyph;

   public PresentableDistancePosition(final CelestialObjects celestialObject,
                                      final CelObjectSinglePosition celObjectSinglePosition) {
      createPresentablePosition(checkNotNull(celestialObject), checkNotNull(celObjectSinglePosition));
   }

   private void createPresentablePosition(final CelestialObjects celestialObject,
                                          final CelObjectSinglePosition celObjectSinglePosition) {
      checkNotNull(celestialObject);
      checkNotNull(celObjectSinglePosition);
      formattedDistance = new DecimalValue(celObjectSinglePosition.getDistancePosition()).getFormattedPosition();
      formattedDistSpeed = new DecimalValue(celObjectSinglePosition.getDistanceSpeed()).getFormattedPosition();
      celBodyGlyph = new CelObject2GlyphMapper().getGlyph(celestialObject);
   }

   public String getFormattedDistance() {
      return formattedDistance;
   }

   public String getFormattedDistSpeed() {
      return formattedDistSpeed;
   }

   public String getCelBodyGlyph() {
      return celBodyGlyph;
   }
}
