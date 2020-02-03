/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum CelObjectCategory {
   UNKNOWN(0, "celobjectcat.unknown.name", "celobjectcat.unknown.descr"),
   CLASSICS(1, "celobjectcat.classic.name", "celobjectcat.classic.descr"),
   MODERN(2, "celobjectcat.modern.name", "celobjectcat.modern.descr"),
   EXTRA_PLUT(3, "celobjectcat.extraplut.name", "celobjectcat.extraplut.descr"),
   ASTEROIDS(4, "celobjectcat.asteroids.name", "celobjectcat.asteroids.descr"),
   CENTAURS(5, "celobjectcat.centaurs.name", "celobjectcat.centaurs.descr"),
   INTERSECTIONS(6, "celobjectcat.intersections.name", "celobjectcat.intersections.descr"),
   HYPOTHETS(7, "celobjectcat.hypothets.name", "celobjectcat.hypothets.descr");

   private final int id;
   private final String nameForRB;
   private final String descrForRB;

   CelObjectCategory(final int id, final String nameForRB, final String descrforRB) {
      this.id = id;
      this.nameForRB = nameForRB;
      this.descrForRB = descrforRB;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return nameForRB;
   }

   public String getRbKeyForDescription() {
      return descrForRB;
   }

}
