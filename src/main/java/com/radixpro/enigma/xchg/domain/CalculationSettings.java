/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

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
      this.celBodies = checkNotNull(celBodies);
      this.houseSystem = checkNotNull(houseSystem);
      this.ayanamsha = checkNotNull(ayanamsha);
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
