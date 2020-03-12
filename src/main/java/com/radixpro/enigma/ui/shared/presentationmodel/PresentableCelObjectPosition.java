/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.be.astron.assist.HorizontalPosition;
import com.radixpro.enigma.be.astron.main.CelObjectPosition;
import com.radixpro.enigma.ui.shared.glyphs.CelObject2GlyphMapper;
import com.radixpro.enigma.ui.shared.glyphs.Sign2GlyphMapper;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.DecimalValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.LongAndGlyphValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlainDmsValue;
import com.radixpro.enigma.ui.shared.presentationmodel.valuetypes.PlusMinusValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Wrapper around CelObjectSinglePosition; enables the use in a tableview.
 */
@Getter
public class PresentableCelObjectPosition {

   private String formattedLongitude;
   private String formattedLongSpeed;
   private String formattedLatitude;
   private String formattedLatSpeed;
   private String signGlyph;
   private String celBodyGlyph;
   private String formattedRightAscension;
   private String formattedRaSpeed;
   private String formattedDeclination;
   private String formattedDeclSpeed;
   private String formattedAzimuth;
   private String formattedAltitude;
   private String formattedDistance;
   private String formattedDistSpeed;

   /**
    * Constructor populates all properties.
    *
    * @param celObjectPosition  contains longitude, latitude, right ascension, declination, radv and daily speeds.
    *                           Also knows which celestial object we are showing.
    * @param horizontalPosition contains azimuth and altitude
    */
   public PresentableCelObjectPosition(@NonNull final CelObjectPosition celObjectPosition,
                                       @NonNull final HorizontalPosition horizontalPosition) {
      createPresentablePosition(celObjectPosition, horizontalPosition);
   }

   private void createPresentablePosition(@NonNull final CelObjectPosition celObjectPos,
                                          @NonNull final HorizontalPosition horPos) {
      val eclPos = celObjectPos.getEclipticalPosition();
      val equPos = celObjectPos.getEquatorialPosition();
      val mainEclPos = eclPos.getMainPosition();
      val longWithGlyph = new LongAndGlyphValue(mainEclPos).getLongWithGlyph();
      formattedLongitude = longWithGlyph.getPosition();
      signGlyph = new Sign2GlyphMapper().getGlyph(longWithGlyph.getSignIndex());
      celBodyGlyph = new CelObject2GlyphMapper().getGlyph(celObjectPos.getCelestialBody());
      formattedLongSpeed = new PlusMinusValue(eclPos.getMainSpeed()).getFormattedPosition();
      formattedLatitude = new PlusMinusValue(eclPos.getDeviationPosition()).getFormattedPosition();
      formattedLatSpeed = new PlusMinusValue(eclPos.getDeviationSpeed()).getFormattedPosition();
      formattedRightAscension = new PlainDmsValue(equPos.getMainPosition()).getFormattedPosition();
      formattedRaSpeed = new PlusMinusValue(equPos.getMainSpeed()).getFormattedPosition();
      formattedDeclination = new PlusMinusValue(equPos.getDeviationPosition()).getFormattedPosition();
      formattedDeclSpeed = new PlusMinusValue(equPos.getDeviationSpeed()).getFormattedPosition();
      formattedAzimuth = new PlainDmsValue(horPos.getAzimuth()).getFormattedPosition();
      formattedAltitude = new PlusMinusValue(horPos.getAltitude()).getFormattedPosition();
      formattedDistance = new DecimalValue(eclPos.getDistancePosition()).getFormattedPosition();
      formattedDistSpeed = new DecimalValue(eclPos.getDistanceSpeed()).getFormattedPosition();
   }

}
