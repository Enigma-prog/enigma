/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import java.util.List;

public class CombinedFlags {

   private final long combinedValue;

   public CombinedFlags(final List<SeFlags> flagList) {
      combinedValue = performCombination(flagList);
   }

   private long performCombination(final List<SeFlags> flagList) {
      long result = 0;
      for (int i = 0; i < flagList.size(); i++) {
         result = result | flagList.get(i).getSeValue();
      }
      return result;
   }

   public long getCombinedValue() {
      return combinedValue;
   }
}
