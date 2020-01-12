/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.Serializable;

public enum Ratings implements Serializable {
   ZZ(0, "zz"),
   AA(1, "aa"),
   A(2, "a"),
   B(3, "b"),
   C(4, "c"),
   DD(5, "dd"),
   X(6, "x"),
   XX(7, "xx");

   private static final String RB_PREFIX = "gen.lookup.ratings.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
   private final int id;
   private final String nameForRB;

   Ratings(final int id, final String nameForRB) {
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

   public Ratings getRatingForId(final int id) {
      for (Ratings rating : Ratings.values()) {
         if (rating.getId() == id) {
            return rating;
         }
      }
      return Ratings.ZZ;
   }

}