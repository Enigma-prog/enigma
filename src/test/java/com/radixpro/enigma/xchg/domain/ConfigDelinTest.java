/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConfigDelinTest {

   private static final double MARGIN = 0.00000001;
   private final double aspectBaseOrb = 8.5;
   private ConfigDelin configDelin;
   private List<AspectOrb> supportedAspects;

   @Before
   public void setUp() {
      supportedAspects = new ArrayList<>();
      supportedAspects.add(new AspectOrb(Aspects.OPPOSITION, 90));
      supportedAspects.add(new AspectOrb(Aspects.BINOVILE, 5));
      configDelin = new ConfigDelin(aspectBaseOrb, supportedAspects);
   }

   @Test
   public void getAspectBaseOrb() {
      assertEquals(aspectBaseOrb, configDelin.getAspectBaseOrb(), MARGIN);
   }

   @Test
   public void getSupportedAspects() {
      assertEquals(supportedAspects, configDelin.getSupportedAspects());
   }
}