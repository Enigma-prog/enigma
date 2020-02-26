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
public class Location implements Serializable {

   private final GeographicCoordinate longInput;
   private final GeographicCoordinate latInput;
   private final String name;


   public Location(@NonNull final GeographicCoordinate longInput, @NonNull final GeographicCoordinate latInput,
                   @NonNull final String name) {
      this.longInput = longInput;
      this.latInput = latInput;
      this.name = name;
   }

   public double getGeoLat() {
      return latInput.getValue();
   }

   public double getGeoLong() {
      return longInput.getValue();
   }

}
