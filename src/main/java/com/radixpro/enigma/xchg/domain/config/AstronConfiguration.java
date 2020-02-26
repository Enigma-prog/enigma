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
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Astronomical parts of the configuration.
 */
@Getter
public class AstronConfiguration implements Serializable {

   private final HouseSystems houseSystem;
   private final Ayanamshas ayanamsha;
   private final EclipticProjections eclipticProjection;
   private final ObserverPositions observerPosition;
   private final List<ConfiguredCelObject> celObjects;

   public AstronConfiguration(@NonNull final HouseSystems houseSystem,
                              @NonNull final Ayanamshas ayanamsha,
                              @NonNull final EclipticProjections eclipticProjection,
                              @NonNull final ObserverPositions observerPosition,
                              @NonNull final List<ConfiguredCelObject> celObjects) {
      this.houseSystem = houseSystem;
      this.ayanamsha = ayanamsha;
      this.eclipticProjection = eclipticProjection;
      this.observerPosition = observerPosition;
      this.celObjects = celObjects;
   }

}
