/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import com.radixpro.enigma.xchg.domain.CelObjectSinglePosition;
import lombok.Getter;
import lombok.NonNull;

/**
 * Wrapper around CelObjectSinglePosition; enables the use in a tableview.
 */
public class PresentableEclipticPosition {

   @Getter
   private double formattedLongitude;
   private double formattedLongSpeed;
   private double formattedLatitude;
   private double formattedLatSpeed;
   private String signGlyph;
   private String celBodyGlyph;


   public PresentableEclipticPosition(@NonNull final CelObjectSinglePosition celObjectSinglePosition) {
      celObjectSinglePosition.getMainPosition();
   }


}
