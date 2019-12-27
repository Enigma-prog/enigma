/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import java.util.List;

public class CalculationSettings {

   private final List<CelBodiesToCalculate> celBodies;
   private final HouseSystemsToCalculate houseSystem;
   private final AyanamshasToCalculate ayanamsha;
   private final boolean sidereal;
   private final boolean topocentric;
   private final boolean heliocentric;

   public CalculationSettings(final List<CelBodiesToCalculate> celBodies, final HouseSystemsToCalculate houseSystem,
                              final AyanamshasToCalculate ayanamsha, final boolean sidereal, final boolean topocentric,
                              final boolean heliocentric) {
      this.celBodies = celBodies;
      this.houseSystem = houseSystem;
      this.ayanamsha = ayanamsha;
      this.sidereal = sidereal;
      this.topocentric = topocentric;
      this.heliocentric = heliocentric;
   }

   public List<CelBodiesToCalculate> getCelBodies() {
      return celBodies;
   }

   public HouseSystemsToCalculate getHouseSystem() {
      return houseSystem;
   }

   public AyanamshasToCalculate getAyanamsha() {
      return ayanamsha;
   }

   public boolean isSidereal() {
      return sidereal;
   }

   public boolean isTopocentric() {
      return topocentric;
   }

   public boolean isHeliocentric() {
      return heliocentric;
   }
}
