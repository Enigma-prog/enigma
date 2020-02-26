/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Getter
public class SimpleDateTime implements Serializable {
   private final SimpleDate date;
   private final SimpleTime time;

   public SimpleDateTime(@NonNull final SimpleDate simpleDate, @NonNull final SimpleTime simpleTime) {
      this.date = simpleDate;
      this.time = simpleTime;
   }

}
