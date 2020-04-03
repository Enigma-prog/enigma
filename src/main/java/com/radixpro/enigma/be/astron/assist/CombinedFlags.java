/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.xchg.domain.SeFlags;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * Combined result of all flags.
 */
public class CombinedFlags {

   @Getter
   private final long combinedValue;

   /**
    * Constructor expects a listof all the flags that will be combined.
    *
    * @param flagList The flags.
    */
   public CombinedFlags(@NonNull final List<SeFlags> flagList) {
      combinedValue = performCombination(flagList);
   }

   private long performCombination(@NonNull final List<SeFlags> flagList) {
      long result = 0;
      for (SeFlags seFlags : flagList) {
         result = result | seFlags.getSeValue();
      }
      return result;
   }
}
