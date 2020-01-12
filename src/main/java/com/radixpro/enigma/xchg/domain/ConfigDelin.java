/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.util.List;

public class ConfigDelin {

   private final double aspectBaseOrb;
   private final List<AspectOrb> supportedAspects;

   public ConfigDelin(final double aspectBaseOrb, final List<AspectOrb> supportedAspects) {
      this.aspectBaseOrb = aspectBaseOrb;
      this.supportedAspects = supportedAspects;
   }

   public double getAspectBaseOrb() {
      return aspectBaseOrb;
   }

   public List<AspectOrb> getSupportedAspects() {
      return supportedAspects;
   }
}
