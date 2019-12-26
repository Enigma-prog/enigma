/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.model.CombinedFlags;
import com.radixpro.enigma.be.model.SeFlags;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CombinedFlagsTest {

   private CombinedFlags combinedFlags;
   private ArrayList<SeFlags> allSeFlags;

   @Before
   public void setUp()  {
      allSeFlags = new ArrayList<>();
      allSeFlags.add(SeFlags.SWISSEPH);            // 2L
      allSeFlags.add(SeFlags.HERLIOCENTRIC);       // 8L
      allSeFlags.add(SeFlags.TOPOCENTRIC);         // 32 * 1024L
      combinedFlags = new CombinedFlags(allSeFlags);
   }

   @Test
   public void getCombinedValue() {
      assertEquals(32778L, combinedFlags.getCombinedValue());
   }
}
