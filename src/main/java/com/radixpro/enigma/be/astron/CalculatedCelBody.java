/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron;

import com.radixpro.enigma.appmodel.CelBodiesToCalculate;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultCelBodies;

/**
 * Calculated positions for a specific celestial body.
 */
public class CalculatedCelBody {

   private String errorMsg = "";
   private final SeFrontend seFrontend;

   public CalculatedCelBody(final SeFrontend seFrontend) {
     this.seFrontend = seFrontend;
   }

   public double[] getPositions(final Double jdUt, final CelBodiesToCalculate celBody, final int flags) {
      SePositionResultCelBodies sePositionResult = seFrontend.getPositionsForCelBody(jdUt, celBody.getId(), flags);
      errorMsg = sePositionResult.getErrorMsg();
      return  sePositionResult.getAllPositions();
   }

   public String getErrorMsg() {
      return errorMsg;
   }

}
