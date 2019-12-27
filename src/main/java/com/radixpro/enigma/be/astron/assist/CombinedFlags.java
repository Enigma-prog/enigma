/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import java.util.List;

public class CombinedFlags {

   private final long combinedValue;

   public CombinedFlags(final List<SeFlags> flagList) {
      combinedValue = performCombination(flagList);
   }

   private long performCombination(final List<SeFlags> flagList) {
      long result = 0;
      for (SeFlags seFlags : flagList) {
         result = result | seFlags.getSeValue();
      }
      return result;
   }

   public long getCombinedValue() {
      return combinedValue;
   }
}
