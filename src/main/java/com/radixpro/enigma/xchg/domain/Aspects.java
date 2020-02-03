/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum Aspects {
   UNKNOWN(0, -1, -1.0, false, "gen.lookup.aspects.unknown"),
   CONJUNCTION(1, 1, 0.0, true, "gen.lookup.aspects.conjunction"),
   OPPOSITION(2, 1, 180.0, true, "gen.lookup.aspects.opposition"),
   TRIANGLE(3, 1, 120.0, true, "gen.lookup.aspects.triangle"),
   SQUARE(4, 1, 90.0, true, "gen.lookup.aspects.square"),
   SEXTILE(5, 1, 60.0, true, "gen.lookup.aspects.sextile"),
   SEMISEXTILE(6, 2, 30.0, true, "gen.lookup.aspects.semisextile"),
   INCONJUNCT(7, 2, 150.0, true, "gen.lookup.aspects.inconjunct"),
   SEMISQUARE(8, 2, 45.0, true, "gen.lookup.aspects.semisquare"),
   SESQUIQUADRATE(9, 2, 135.0, true, "gen.lookup.aspects.sesquiquadrate"),
   QUINTILE(10, 2, 72.0, true, "gen.lookup.aspects.quintile"),
   BIQUINTILE(11, 2, 144.0, true, "gen.lookup.aspects.biquintile"),
   SEPTILE(12, 2, 51.42857143, true, "gen.lookup.aspects.septile"),
   PARALLEL(13, 1, 0.0, false, "gen.lookup.aspects.parallel"),
   CONTRAPARALLEL(14, 1, 0.0, false, "gen.lookup.aspects.contraparallel"),
   VIGINTILE(15, 3, 18.0, true, "gen.lookup.aspects.vigintile"),
   SEMIQUINTILE(16, 3, 36.0, true, "gen.lookup.aspects.semiquintile"),
   TRIDECILE(17, 3, 108.0, true, "gen.lookup.aspects.tridecile"),
   BISEPTILE(18, 3, 102.857142857, true, "gen.lookup.aspects.biseptile"),
   TRISEPTILE(19, 3, 154.2857142857, true, "gen.lookup.aspects.triseptile"),
   NOVILE(20, 3, 40.0, true, "gen.lookup.aspects.novile"),
   BINOVILE(21, 3, 80.0, true, "gen.lookup.aspects.binovile"),
   QUADRANOVILE(22, 3, 160.0, true, "gen.lookup.aspects.quadranovile"),
   UNDECILE(23, 3, 32.7272727272, true, "gen.lookup.aspects.undecile"),
   CENTILE(24, 3, 100.0, true, "gen.lookup.aspects.centile");


   //   private static final String rbPrefix = "gen.lookup.aspects.";
   private final int id;
   private final int importance;
   private final double angle;
   private final boolean ecliptical;
   private final String fullRbId;

   Aspects(final int id, final int importance, final double angle, final boolean ecliptical, final String rbId) {
      this.id = id;
      this.importance = importance;
      this.ecliptical = ecliptical;
      this.angle = angle;
//      this.fullRbId = rbPrefix + rbId;
      this.fullRbId = rbId;
   }

   public int getId() {
      return id;
   }

   public double getAngle() {
      return angle;
   }

   public int getImportance() {
      return importance;
   }

   public boolean isEcliptical() {
      return ecliptical;
   }

   public String getFullRbId() {
      return fullRbId;
   }

   public Aspects getAspectForId(int id) {
      for (Aspects aspect : Aspects.values()) {
         if (aspect.getId() == id) {
            return aspect;
         }
      }
      return Aspects.UNKNOWN;
   }
}
