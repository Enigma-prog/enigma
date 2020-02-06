/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import com.radixpro.enigma.xchg.domain.CelestialObjects;

import java.io.Serializable;

/**
 * Configuration for a specific celestial object.
 */
public class ConfiguredCelObject implements Serializable {
   private final CelestialObjects celObject;
   private final String glyph;
   private final double orbPercentage;
   private final boolean showInDrawing;

   public ConfiguredCelObject(final CelestialObjects celObject, final String glyph, final double orbPercentage,
                              final boolean showInDrawing) {
      this.celObject = celObject;
      this.glyph = glyph;
      this.orbPercentage = orbPercentage;
      this.showInDrawing = showInDrawing;
   }

   public CelestialObjects getCelObject() {
      return celObject;
   }

   public String getGlyph() {
      return glyph;
   }

   public double getOrbPercentage() {
      return orbPercentage;
   }

   public boolean isShowInDrawing() {
      return showInDrawing;
   }
}
