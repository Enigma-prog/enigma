/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum EclipticProjections {
   UNKNOWN(0, "eclipticprojections.unknown"),
   TROPICAL(1, "eclipticprojections.tropical"),
   SIDEREAL(2, "eclipticprojections.sidereal");

   private final int id;
   private final String nameForRB;

   EclipticProjections(final int id, @NonNull final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public EclipticProjections getProjectionForId(final int id) {
      for (EclipticProjections eclipticProjection : EclipticProjections.values()) {
         if (eclipticProjection.getId() == id) {
            return eclipticProjection;
         }
      }
      return EclipticProjections.UNKNOWN;
   }

}
