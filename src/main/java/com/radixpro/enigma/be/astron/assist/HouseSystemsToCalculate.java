/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

/**
 * Housesystems for calculation, internal id and id for SE.
 */
public enum HouseSystemsToCalculate {
   NONE(1, "", 0),
   WHOLESIGN(2, "W", 12),
   EQUAL(3, "A", 12),           // SE also supports "E".
   EQUAL_MINUS_5(4, "", 12),
   VEHLOW(5, "V", 12),
   PLACIDUS(6, "P", 12),
   KOCH(7, "K", 12),
   PORPHYRI(8, "O", 12),
   REGIOMONTANUS(9, "R", 12),
   CAMPANUS(10, "C", 12),
   ALCABITIUS(11, "B", 12),
   TOPOCENTRIC(12, "T", 12),
   KRUSINSKI(13, "U", 12),
   APC(14, "Y", 12),
   MORIN(15, "M", 12),
   AXIAL(16, "X", 12),
   HORIZON(17, "H", 12);

   private final String seId;
   private final int id;
   private final int nrOfCusps;

   HouseSystemsToCalculate(final int id, final String seId, final int nrOfCusps) {
      this.id = id;
      this.seId = seId;
      this.nrOfCusps = nrOfCusps;
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
}
