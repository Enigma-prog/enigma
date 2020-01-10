/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChartTypesTest {

   private final ChartTypes chartType = ChartTypes.EVENT;

   @Test
   public void getRbKeyForName() {
      assertEquals("gen.lookup.charttype.event.name", chartType.getRbKeyForName());
   }
}