/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import static swisseph.SweConst.SE_ECL_NUT;

/**
 * Obliquity of the earth-axis.
 */
@Getter
public class Obliquity {

   private double trueObliquity;
   private double meanObliquity;

   public Obliquity(@NonNull final SeFrontend seFrontend, final double jdUt) {
      performCalculation(seFrontend, jdUt);
   }

   private void performCalculation(@NonNull final SeFrontend seFrontend, final double jdUt) {
      val flags = 0;
      val calculatedPos = seFrontend.getPositionsForCelBody(jdUt, SE_ECL_NUT, flags);
      trueObliquity = calculatedPos.getAllPositions()[0];
      meanObliquity = calculatedPos.getAllPositions()[1];
   }
}
