/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum EclipticProjections {
   UNKNOWN(0, "eclipticprojections.unknown"),
   TROPICAL(1, "eclipticprojections.tropical"),
   SIDEREAL(2, "eclipticprojections.sidereal");

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
      return rbId;
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
