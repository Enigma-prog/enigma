/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import com.radixpro.enigma.xchg.domain.Ayanamshas;
import com.radixpro.enigma.xchg.domain.EclipticProjections;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import com.radixpro.enigma.xchg.domain.ObserverPositions;

import java.io.Serializable;
import java.util.List;

/**
 * Astronomical parts of the configuration.
 */
public class AstronConfiguration implements Serializable {

   private final HouseSystems houseSystem;
   private final Ayanamshas ayanamsha;
   private final EclipticProjections eclipticProjection;
   private final ObserverPositions observerPosition;
   private final List<ConfiguredCelObject> celObjects;

   public AstronConfiguration(final HouseSystems houseSystem, final Ayanamshas ayanamsha,
                              final EclipticProjections eclipticProjection, final ObserverPositions observerPosition,
                              final List<ConfiguredCelObject> celObjects) {
      this.houseSystem = houseSystem;
      this.ayanamsha = ayanamsha;
      this.eclipticProjection = eclipticProjection;
      this.observerPosition = observerPosition;
      this.celObjects = celObjects;
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

   public List<ConfiguredCelObject> getCelObjects() {
      return celObjects;
   }
}
