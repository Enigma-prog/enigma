/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.SePositionResultCelObjects;
import com.radixpro.enigma.be.astron.core.SeFrontend;

import static swisseph.SweConst.SE_ECL_NUT;

/**
 * Obliquity of the earth-axis.
 */
public class Obliquity {

   private double trueObliquity;
   private double meanObliquity;

   public Obliquity(final SeFrontend seFrontend, final double jdUt) {
      performCalculation(seFrontend, jdUt);
   }

   private void performCalculation(final SeFrontend seFrontend, final double jdUt) {
      final int flags = 0;
      SePositionResultCelObjects calculatedPos = seFrontend.getPositionsForCelBody(jdUt, SE_ECL_NUT, flags);
      trueObliquity = calculatedPos.getAllPositions()[0];
      meanObliquity = calculatedPos.getAllPositions()[1];
   }

   public double getTrueObliquity() {
      return trueObliquity;
   }

   public double getMeanObliquity() {
      return meanObliquity;
   }
}
