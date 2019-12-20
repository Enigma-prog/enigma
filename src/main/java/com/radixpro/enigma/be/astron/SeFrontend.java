/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron;

import com.radixpro.enigma.be.astron.calcmodel.SePositionResultCelBodies;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultHouses;
import swisseph.SwissEph;

/**
 * Simple wrapper to access the Java port by Thomas Mack.
 * Implemented as a singleton to prevent multiple instantations.
 * You should always use this wrapper to access the Java port.
 */
public class SeFrontend {

   private static SeFrontend instance = null;
   private final static String path = "se";
   private final SwissEph swissEph = new SwissEph(path);

   private SeFrontend() {
      // prevent direct instantiation.
   }

   public static SeFrontend getFrontend() {
      if (instance == null) {
         instance = new SeFrontend();
      }
      return instance;
   }

   public SePositionResultCelBodies getPositionsForCelBody(double jdUt, int id, int flags) {
      double[] allPositions = new double[6];
      StringBuffer errorMsg = new StringBuffer();
      swissEph.swe_calc_ut(jdUt, id, flags, allPositions, errorMsg);
      return new SePositionResultCelBodies(allPositions, errorMsg.toString());
   }

   public SePositionResultHouses getPositionsForHouses(double jdUt, int flags, double geoLat, double geoLong, int system, int nrOfCusps) {
      double[] cusps = new double[nrOfCusps + 1];
      double[] ascMc = new double[10];
      swissEph.swe_houses(jdUt, flags, geoLat, geoLong, system, cusps, ascMc);
      return new SePositionResultHouses(ascMc, cusps);
   }


}
