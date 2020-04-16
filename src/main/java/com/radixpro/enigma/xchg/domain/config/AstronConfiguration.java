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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Astronomical parts of the configuration.
 */
public class AstronConfiguration implements Serializable {

   private final HouseSystems houseSystem;
   private final Ayanamshas ayanamsha;
   private final EclipticProjections eclipticProjection;
   private final ObserverPositions observerPosition;
   private final List<ConfiguredCelObject> celObjects;

   /**
    * Constructor defines all memebers.
    *
    * @param houseSystem        Selected house system.
    * @param ayanamsha          Selected ayanamasha, Ayanamshas.NONE for tropical zodiac.
    * @param eclipticProjection Tropical or sidereal zodiac.
    * @param observerPosition   Positionof the observer.
    * @param celObjects         The supported celestial objects.
    */
   public AstronConfiguration(final HouseSystems houseSystem,
                              final Ayanamshas ayanamsha,
                              final EclipticProjections eclipticProjection,
                              final ObserverPositions observerPosition,
                              final List<ConfiguredCelObject> celObjects) {
      this.houseSystem = checkNotNull(houseSystem);
      this.ayanamsha = checkNotNull(ayanamsha);
      this.eclipticProjection = checkNotNull(eclipticProjection);
      this.observerPosition = checkNotNull(observerPosition);
      this.celObjects = checkNotNull(celObjects);
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
