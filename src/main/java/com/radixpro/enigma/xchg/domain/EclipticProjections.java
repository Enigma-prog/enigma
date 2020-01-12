/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum EclipticProjections {
   UNKNOWN(0, "unknown"),
   TROPICAL(1, "tropical"),
   SIDEREAL(2, "sidereal");

   private static final String RB_PREFIX = "gen.lookup.eclipticprojections.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";

   private final int id;
   private final String rbId;

   EclipticProjections(final int id, final String rbId) {
      this.id = id;
      this.rbId = rbId;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + rbId + RB_NAME_POSTFIX;
   }

   public String getRbKeyForDescription() {
      return RB_PREFIX + rbId + RB_DESCRIPTION_POSTFIX;
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
