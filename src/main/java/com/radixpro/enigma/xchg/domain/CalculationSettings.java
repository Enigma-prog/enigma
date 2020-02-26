/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class CalculationSettings {

   private final List<CelestialObjects> celBodies;
   private final HouseSystems houseSystem;
   private final Ayanamshas ayanamsha;
   private final boolean sidereal;
   private final boolean topocentric;
   private final boolean heliocentric;

   public CalculationSettings(@NonNull final List<CelestialObjects> celBodies, @NonNull final HouseSystems houseSystem,
                              @NonNull final Ayanamshas ayanamsha, final boolean sidereal, final boolean topocentric,
                              final boolean heliocentric) {
      this.celBodies = celBodies;
      this.houseSystem = houseSystem;
      this.ayanamsha = ayanamsha;
      this.sidereal = sidereal;
      this.topocentric = topocentric;
      this.heliocentric = heliocentric;
   }
}
