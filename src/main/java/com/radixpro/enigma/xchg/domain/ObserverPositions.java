/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;


/**
 * Enum for the position of the observer, this results in a geocentric chart, a topocentric chart
 * (using parallax correction), or a heliocentric chart. Is persistable as part of a configuration.
 */
@Getter
public enum ObserverPositions implements Serializable {
   UNKNOWN(0, "observerpositions.unknown"),
   GEOCENTRIRC(1, "observerpositions.geocentric"),
   TOPOCENTRIC(2, "observerpositions.topocentric"),
   HELIOCENTRIC(3, "observerpositions.heliocentric");

   private final int id;
   private final String nameForRB;

   ObserverPositions(final int id, @NonNull final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   /**
    * Return an observer position for the specified index.
    *
    * @param id The index.
    * @return The resulting obserever position.
    */
   public ObserverPositions getObserverPositionForId(final int id) {
      for (ObserverPositions observerPos : ObserverPositions.values()) {
         if (observerPos.getId() == id) {
            return observerPos;
         }
      }
      return ObserverPositions.UNKNOWN;
   }

}
