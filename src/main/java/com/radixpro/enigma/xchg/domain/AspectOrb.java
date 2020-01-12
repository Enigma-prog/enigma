/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public class AspectOrb {

   private final Aspects aspect;
   private final int orbPercentage;

   public AspectOrb(final Aspects aspect, final int orbPercentage) {
      this.aspect = aspect;
      this.orbPercentage = orbPercentage;
   }

   public Aspects getAspect() {
      return aspect;
   }

   public int getOrbPercentage() {
      return orbPercentage;
   }
}
