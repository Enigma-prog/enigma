/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.util.List;

public class ConfigAstron {

   private final HouseSystems houseSystem;
   private final Ayanamshas ayanamsha;
   private final EclipticProjections eclipticProjection;
   private final ObserverPositions observerPosition;
   private final List<CelestialObjects> celestialObjects;

   public ConfigAstron(final HouseSystems houseSystem, final Ayanamshas ayanamsha,
                       final EclipticProjections eclipticProjection, final ObserverPositions observerPosition,
                       final List<CelestialObjects> celestialObjects) {
      this.houseSystem = houseSystem;
      this.ayanamsha = ayanamsha;
      this.eclipticProjection = eclipticProjection;
      this.observerPosition = observerPosition;
      this.celestialObjects = celestialObjects;
   }

   public HouseSystems getHouseSystem() {
      return houseSystem;
   }

   public Ayanamshas getAyanamsha() {
      return ayanamsha;
   }

   public EclipticProjections getEclipticProjection() {
      return eclipticProjection;
   }

   public ObserverPositions getObserverPosition() {
      return observerPosition;
   }

   public List<CelestialObjects> getCelestialObjects() {
      return celestialObjects;
   }
}
