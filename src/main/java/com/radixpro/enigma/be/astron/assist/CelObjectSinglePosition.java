/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import lombok.Getter;
import lombok.NonNull;

/**
 * Calculated positions for a specific coordinateset for a celestial body.
 */
@Getter
public class CelObjectSinglePosition {

   private String errorMsg = "";
   private double mainPosition;
   private double deviationPosition;
   private double distancePosition;
   private double mainSpeed;
   private double deviationSpeed;
   private double distanceSpeed;

   public CelObjectSinglePosition(@NonNull final SeFrontend seFrontend, final double jdUt,
                                  @NonNull final CelestialObjects celBody, final int flags) {
      calculate(seFrontend, jdUt, celBody, flags);
   }

   private void calculate(@NonNull final SeFrontend seFrontend, final double jdUt,
                          @NonNull final CelestialObjects celBody, final int flags) {
      SePositionResultCelObjects sePositionResult = seFrontend.getPositionsForCelBody(jdUt, (int) celBody.getSeId(), flags);
      errorMsg = sePositionResult.getErrorMsg();
      mainPosition = sePositionResult.getAllPositions()[0];
      deviationPosition = sePositionResult.getAllPositions()[1];
      distancePosition = sePositionResult.getAllPositions()[2];
      mainSpeed = sePositionResult.getAllPositions()[3];
      deviationSpeed = sePositionResult.getAllPositions()[4];
      distanceSpeed = sePositionResult.getAllPositions()[5];
   }

}
