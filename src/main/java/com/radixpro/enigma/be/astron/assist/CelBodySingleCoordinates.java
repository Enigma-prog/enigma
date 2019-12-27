/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.be.astron.core.SeFrontend;

/**
 * Calculated positions for a specific coordinateset for a celestial body.
 */
public class CelBodySingleCoordinates {

   private String errorMsg = "";
   private double mainPosition;
   private double deviationPosition;
   private double distancePosition;
   private double mainSpeed;
   private double deviationSpeed;
   private double distanceSpeed;

   public CelBodySingleCoordinates(final SeFrontend seFrontend, final Double jdEt, final CelBodiesToCalculate celBody, final int flags) {
      calculate(seFrontend, jdEt, celBody, flags);
   }

   private void calculate(final SeFrontend seFrontend, final Double jdEt, final CelBodiesToCalculate celBody, final int flags) {
      SePositionResultCelBodies sePositionResult = seFrontend.getPositionsForCelBody(jdEt, celBody.getId(), flags);
      errorMsg = sePositionResult.getErrorMsg();
      mainPosition = sePositionResult.getAllPositions()[0];
      deviationPosition = sePositionResult.getAllPositions()[1];
      distancePosition = sePositionResult.getAllPositions()[2];
      mainSpeed = sePositionResult.getAllPositions()[3];
      deviationSpeed = sePositionResult.getAllPositions()[4];
      distanceSpeed = sePositionResult.getAllPositions()[5];
   }

   public String getErrorMsg() {
      return errorMsg;
   }

   public double getMainPosition() {
      return mainPosition;
   }

   public double getDeviationPosition() {
      return deviationPosition;
   }

   public double getDistancePosition() {
      return distancePosition;
   }

   public double getMainSpeed() {
      return mainSpeed;
   }

   public double getDeviationSpeed() {
      return deviationSpeed;
   }

   public double getDistanceSpeed() {
      return distanceSpeed;
   }
}
