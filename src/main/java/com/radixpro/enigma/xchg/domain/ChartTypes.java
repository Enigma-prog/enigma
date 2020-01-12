/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum ChartTypes {
   UNKNOWN(0, "unknown"),
   NATAL(1, "natal"),
   EVENT(2, "event"),
   HORARY(3, "horary"),
   ELECTION(4, "election");

   private static final String RB_PREFIX = "gen.lookup.charttype.";
   private static final String RB_NAME_POSTFIX = ".name";
   private final int id;
   private final String nameForRB;

   ChartTypes(final int id, final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

   public ChartTypes chartTypeForId(final int id) {
      for (ChartTypes chartType : ChartTypes.values()) {
         if (chartType.getId() == id) {
            return chartType;
         }
      }
      return ChartTypes.UNKNOWN;
   }

}
