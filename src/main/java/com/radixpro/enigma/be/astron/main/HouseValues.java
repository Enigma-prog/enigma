/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.FullPositionResultHouses;
import com.radixpro.enigma.be.astron.assist.HouseSystemsToCalculate;
import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SePositionResultHouses;
import com.radixpro.enigma.be.astron.core.SeFrontend;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculated positions for houses and other mundane points.
 */
public class HouseValues {

   private List<FullPositionResultHouses> cusps;  // values start at position 1
   private FullPositionResultHouses mc;
   private FullPositionResultHouses ascendant;
   private double eastpoint;
   private double vertex;
   private double armc;

   public HouseValues(final SeFrontend seFrontend, final double jdUt, final int flags, final Location location,
                      final HouseSystemsToCalculate system) {
      calculate(seFrontend, jdUt, flags, location, system);
   }

   private void calculate(final SeFrontend seFrontend, final double jdUt, final int flags, final Location location,
                          final HouseSystemsToCalculate system) {
      final char seIdAsInt = system.getSeId().charAt(0);
      final SePositionResultHouses positions = seFrontend.getPositionsForHouses(jdUt, flags, location, seIdAsInt,
            system.getNrOfCusps());
      cusps = new ArrayList<>();
      for (int i = 0; i < positions.getCusps().length; i++) {
         cusps.add(constructFullPosition(seFrontend, positions.getCusps()[i], jdUt));
      }
      ascendant = constructFullPosition(seFrontend, positions.getAscMc()[0], jdUt);
      mc = constructFullPosition(seFrontend, positions.getAscMc()[1], jdUt);
      armc = positions.getAscMc()[2];
      vertex = positions.getAscMc()[3];
      eastpoint = positions.getAscMc()[4];
   }

   private FullPositionResultHouses constructFullPosition(final SeFrontend seFrontend, final double longitude,
                                                          final double jdUt) {
      final EquatorialPosition equatorialPosition = new EquatorialPosition(seFrontend, longitude, jdUt);
      return new FullPositionResultHouses(longitude, equatorialPosition);
   }

   public List<FullPositionResultHouses> getCusps() {
      return cusps;
   }

   public FullPositionResultHouses getMc() {
      return mc;
   }

   public FullPositionResultHouses getAscendant() {
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
