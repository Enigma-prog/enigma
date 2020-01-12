/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum ObserverPositions {
   UNKNOWN(0, "unknown"),
   GEOCENTRIRC(1, "geocentric"),
   TOPOCENTRIC(2, "topocentric"),
   HELIOCENTRIC(3, "heliocentric");

   private static final String RB_PREFIX = "gen.lookup.observerpositions.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
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
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

   public String getRbKeyForDescription() {
      return RB_PREFIX + nameForRB + RB_DESCRIPTION_POSTFIX;
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
