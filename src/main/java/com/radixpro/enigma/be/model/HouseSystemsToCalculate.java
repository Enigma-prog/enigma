/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

/**
 * Housesystems for calculation, internal id and id for SE.
 */
public enum HouseSystemsToCalculate {
   NONE(1, ""),
   WHOLESIGN(2, "W"),
   EQUAL(3, "A"),           // SE also supports "E".
   EQUAL_MINUS_5(4, ""),
   VEHLOW(5, "V"),
   PLACIDUS(6, "P"),
   KOCH(7, "K"),
   PORPHYRI(8, "O"),
   REGIOMONTANUS(9, "R"),
   CAMPANUS(10, "C"),
   ALCABITIUS(11, "B"),
   TOPOCENTRIC(12, "T"),
   KRUSINSKI(13, "U"),
   APC(14, "Y"),
   MORIN(15, "M"),
   AXIAL(16, "X"),
   HORIZON(17, "H");

   private final String seId;
   private final int id;

   HouseSystemsToCalculate(int id, String seId) {
      this.id = id;
      this.seId = seId;
   }

   public String getSeId() {
      return seId;
   }

   public int getId() {
      return id;
   }
}
