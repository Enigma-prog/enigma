/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron;

import com.radixpro.enigma.appmodel.HouseSystemsToCalculate;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultHouses;

/**
 * Calculated positions for houses and other mundane points.
 */
public class CalculatedHouses {

   private final SeFrontend seFrontend;

   public CalculatedHouses(final SeFrontend seFrontend) {
      this.seFrontend = seFrontend;
   }

   /**
    * Calculate positions for cusps
    * @param jdUt NJulian Day number for UT
    * @param flags Flags for SE
    * @param geoLat Geographic latitude
    * @param geoLong Geographic longitude
    * @param system Housesystem
    * @param nrOfCusps Number of cusps in housesystem
    * @return Array with doubles, ignore postion 0, positions 1..[nrOfCusps] contain longitude for cusps 1..[nrOfCusps]
    */
   public double[] getCuspPositions(final double jdUt, final int flags, final double geoLat, final double geoLong,
                                    final HouseSystemsToCalculate system, final int nrOfCusps) {
      return performCalculation(jdUt, flags, geoLat, geoLong, system, nrOfCusps).getCusps();
   }

   /**
    * Calculate additional mundane positions
    * @param jdUt NJulian Day number for UT
    * @param flags Flags for SE
    * @param geoLat Geographic latitude
    * @param geoLong Geographic longitude
    * @param system Housesystem
    * @param nrOfCusps Number of cusps in housesystem
    * @return Array with doubles: 0 = Ascendant, 1 = MC, 2 = ARMC, 3 = Vertex, 4 = equatorial ascendant,
    *         5 = co-ascendant (Walter Koch), 6 = co-ascendant (Michael Munkasey), 7 = polar ascendant (M. Munkasey)
    */
   public double[] getAdditionalPositions(final double jdUt, final int flags, final double geoLat, final double geoLong,
                                          final HouseSystemsToCalculate system, final int nrOfCusps) {
      return performCalculation(jdUt, flags, geoLat, geoLong, system, nrOfCusps).getAscMc();
   }

   private SePositionResultHouses performCalculation(final double jdUt, final int flags, final double geoLat,
                                                     final double geoLong, final HouseSystemsToCalculate system,
                                                     final int nrOfCusps) {
      final char seIdAsInt = system.getSeId().charAt(0);
      return seFrontend.getPositionsForHouses(jdUt, flags, geoLat, geoLong, seIdAsInt,  nrOfCusps);
   }

}
