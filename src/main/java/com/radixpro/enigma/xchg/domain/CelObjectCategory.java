/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum CelObjectCategory {
   CLASSICS(1, "classic"),
   MODERN(2, "modern"),
   EXTRA_PLUT(3, "extraplut"),
   ASTEROIDS(4, "asteroids"),
   CENTAURS(5, "centaurs"),
   INTERSECTIONS(6, "intersections"),
   HYPOTHETS(7, "hypothets");

   private static final String RB_PREFIX = "gen.lookup.celobjectcat.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
   private final int id;
   private final String nameForRB;

   CelObjectCategory(final int id, final String nameForRB) {
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

}
