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
public class GeographicCoordinate implements Serializable {

   private final int degrees;
   private final int minutes;
   private final int seconds;
   private final String direction;
   private final double value;

   public GeographicCoordinate(final int degrees, final int minutes, final int seconds, @NonNull final String direction,
                               final double value) {
      this.degrees = degrees;
      this.minutes = minutes;
      this.seconds = seconds;
      this.direction = direction;
      this.value = value;
   }

}
