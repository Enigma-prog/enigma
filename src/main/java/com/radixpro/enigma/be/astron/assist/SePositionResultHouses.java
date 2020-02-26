/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import lombok.Getter;
import lombok.NonNull;

/**
 * DTO for the result of a SE calculation for houses, holds array with positions and a possible error Message.
 */
@Getter
public class SePositionResultHouses {

   private final double[] ascMc;
   private final double[] cusps;

   public SePositionResultHouses(@NonNull final double[] ascMc, @NonNull final double[] cusps) {
      this.ascMc = ascMc;
      this.cusps = cusps;
   }

}
