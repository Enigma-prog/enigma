/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.xchg.domain.Ayanamshas;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import com.radixpro.enigma.xchg.domain.HouseSystems;

import java.util.List;

public class CalculationSettings {

   private final List<CelestialObjects> celBodies;
   private final HouseSystems houseSystem;
   private final Ayanamshas ayanamsha;
   private final boolean sidereal;
   private final boolean topocentric;
   private final boolean heliocentric;

   public CalculationSettings(final List<CelestialObjects> celBodies, final HouseSystems houseSystem,
                              final Ayanamshas ayanamsha, final boolean sidereal, final boolean topocentric,
                              final boolean heliocentric) {
      this.celBodies = celBodies;
      this.houseSystem = houseSystem;
      this.ayanamsha = ayanamsha;
      this.sidereal = sidereal;
      this.topocentric = topocentric;
      this.heliocentric = heliocentric;
   }

   public List<CelestialObjects> getCelBodies() {
      return celBodies;
   }

   public HouseSystems getHouseSystem() {
      return houseSystem;
   }

   public Ayanamshas getAyanamsha() {
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
