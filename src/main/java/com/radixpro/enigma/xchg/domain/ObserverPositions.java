/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum ObserverPositions {
   UNKNOWN(0, "observerpositions.unknown"),
   GEOCENTRIRC(1, "observerpositions.geocentric"),
   TOPOCENTRIC(2, "observerpositions.topocentric"),
   HELIOCENTRIC(3, "observerpositions.heliocentric");

   private final int id;
   private final String nameForRB;

   ObserverPositions(final int id, final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return nameForRB;
   }

   public ObserverPositions getObserverPositionForId(final int id) {
      for (ObserverPositions observerPos : ObserverPositions.values()) {
         if (observerPos.getId() == id) {
            return observerPos;
         }
      }
      return ObserverPositions.UNKNOWN;
   }

}
