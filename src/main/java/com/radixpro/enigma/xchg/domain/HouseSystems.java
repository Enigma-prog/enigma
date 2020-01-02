/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

/**
 * Housesystems for calculation, internal id and id for SE.
 */
public enum HouseSystems {
   NONE(-1, "", "none", 0, false, false, false),
   WHOLESIGN(2, "W", "wholesign", 12, true, false, true),
   EQUAL(3, "A", "equalasc", 12, true, false, true),
   EQUAL_MC(4, "D", "equalmc", 12, true, false, true),
   VEHLOW(5, "V", "vehlow", 12, true, false, false),
   PLACIDUS(6, "P", "placidus", 12, true, true, true),
   KOCH(7, "K", "koch", 12, true, true, true),
   PORPHYRI(8, "O", "porphyri", 12, true, true, true),
   REGIOMONTANUS(9, "R", "regiomontanus", 12, true, true, true),
   CAMPANUS(10, "C", "campanus", 12, true, true, true),
   ALCABITIUS(11, "B", "alcabitius", 12, true, true, true),
   TOPOCENTRIC(12, "T", "topocentric", 12, true, true, true),
   KRUSINSKI(13, "U", "krusinsky", 12, true, true, true),
   APC(14, "Y", "apc", 12, true, true, true),
   MORIN(15, "M", "morin", 12, true, false, true),
   AXIAL(16, "X", "axial", 12, true, false, true),
   HORIZON(17, "H", "azimuth", 12, true, false, true),
   CARTER(18, "F", "carter", 12, true, false, true),
   EQUAL_ARIES(19, "N", "equalaries", 12, true, false, true),
   GAUQUELIN(20, "G", "gauquelin", 36, false, false, true),
   SUNSHINE(21, "i", "sunshine", 12, true, false, false),
   SUNSHINE_TREINDL(22, "I", "sunshinetreindl", 12, true, false, true);

   private static final String RB_PREFIX = "gen.lookup.houses.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
   private final String seId;
   private final int id;
   private final int nrOfCusps;
   private final String nameForRB;
   private final boolean counterClockwise;
   private final boolean quadrantSystem;
   private final boolean cuspIsStart;


   HouseSystems(final int id, final String seId, final String nameForRB, final int nrOfCusps,
                final boolean counterClockwise, final boolean quadrantSystem, final boolean cuspIsStart) {
      this.id = id;
      this.seId = seId;
      this.nameForRB = nameForRB;
      this.nrOfCusps = nrOfCusps;
      this.counterClockwise = counterClockwise;
      this.quadrantSystem = quadrantSystem;
      this.cuspIsStart = cuspIsStart;
   }

   public String getSeId() {
      return seId;
   }

   public int getId() {
      return id;
   }

   public int getNrOfCusps() {
      return nrOfCusps;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

   public String getRbKeyForDescription() {
      return RB_PREFIX + nameForRB + RB_DESCRIPTION_POSTFIX;
   }

   public boolean isCounterClockwise() {
      return counterClockwise;
   }

   public boolean isQuadrantSystem() {
      return quadrantSystem;
   }

   public boolean isCuspIsStart() {
      return cuspIsStart;
   }

}
