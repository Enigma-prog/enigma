/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import com.radixpro.enigma.xchg.domain.AspectOrbStructure;

import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Configuration for aspects.
 */
public class AspectConfiguration implements Serializable {

   private final double baseOrb;
   private final AspectOrbStructure orbStructure;
   private final List<ConfiguredAspect> aspects;
   private final boolean drawInOutGoing;

   /**
    * Constructor defines all members.
    *
    * @param aspects        Supported aspects including orb-percentage.
    * @param baseOrb        The base orb.
    * @param orbStructure   Structure to be used for orbs.
    * @param drawInOutGoing Indicates if separate glyphs will be used for in- and outgoing aspects.
    */
   public AspectConfiguration(final List<ConfiguredAspect> aspects, final double baseOrb,
                              final AspectOrbStructure orbStructure, final boolean drawInOutGoing) {
      this.aspects = checkNotNull(aspects);
      this.baseOrb = baseOrb;
      this.orbStructure = checkNotNull(orbStructure);
      this.drawInOutGoing = drawInOutGoing;
   }

   public double getBaseOrb() {
      return baseOrb;
   }

   public AspectOrbStructure getOrbStructure() {
      return orbStructure;
   }

   public List<ConfiguredAspect> getAspects() {
      return aspects;
   }

   public boolean isDrawInOutGoing() {
      return drawInOutGoing;
   }
}
