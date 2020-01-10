/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum ChartTypes {
   NATAL("natal"),
   EVENT("event"),
   HORARY("horary"),
   ELECTION("election");

   private static final String RB_PREFIX = "gen.lookup.charttype.";
   private static final String RB_NAME_POSTFIX = ".name";
   private final String nameForRB;

   ChartTypes(final String nameForRB) {
      this.nameForRB = nameForRB;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

}
