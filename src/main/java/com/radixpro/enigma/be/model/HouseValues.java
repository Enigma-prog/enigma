/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.core.SeFrontend;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculated positions for houses and other mundane points.
 */
public class HouseValues {

   private List<Double> cusps;  // values start at position 1
   private double mc;
   private double ascendant;
   private double eastpoint;
   private double vertex;
   private double armc;

   public HouseValues(final SeFrontend seFrontend, final double jdUt, final int flags, final double geoLat,
                      final double geoLong, final HouseSystemsToCalculate system, final int nrOfCusps) {
      calculate(seFrontend, jdUt, flags, geoLat, geoLong, system, nrOfCusps);
   }

   private void calculate(final SeFrontend seFrontend, final double jdUt, final int flags, final double geoLat,
                          final double geoLong, final HouseSystemsToCalculate system, final int nrOfCusps) {
      final char seIdAsInt = system.getSeId().charAt(0);
      final SePositionResultHouses positions = seFrontend.getPositionsForHouses(jdUt, flags, geoLat, geoLong, seIdAsInt,
            nrOfCusps);
      cusps = new ArrayList<>();
      for (int i = 0; i < positions.getCusps().length; i++) {
         cusps.add(positions.getCusps()[i]);
      }
      ascendant = positions.getAscMc()[0];
      mc = positions.getAscMc()[1];
      armc = positions.getAscMc()[2];
      vertex = positions.getAscMc()[3];
      eastpoint = positions.getAscMc()[4];
   }

   public List<Double> getCusps() {
      return cusps;
   }

   public double getMc() {
      return mc;
   }

   public double getAscendant() {
      return ascendant;
   }

   public double getEastpoint() {
      return eastpoint;
   }

   public double getVertex() {
      return vertex;
   }

   public double getArmc() {
      return armc;
   }
}
