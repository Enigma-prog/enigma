/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import com.radixpro.enigma.xchg.domain.AspectOrbStructure;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Configuration for aspects.
 */
@Getter
public class AspectConfiguration implements Serializable {

   private final double baseOrb;
   private final AspectOrbStructure orbStructure;
   private final List<ConfiguredAspect> aspects;
   private final boolean drawInOutGoing;

   public AspectConfiguration(@NonNull final List<ConfiguredAspect> aspects, final double baseOrb,
                              @NonNull final AspectOrbStructure orbStructure, final boolean drawInOutGoing) {
      this.aspects = aspects;
      this.baseOrb = baseOrb;
      this.orbStructure = orbStructure;
      this.drawInOutGoing = drawInOutGoing;
   }
}
