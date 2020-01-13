/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

/**
 * Celestial bodies and id's to access the SE.
 */
public enum CelestialObjects {
   UNKNOWN(0, -1, CelObjectCategory.UNKNOWN, "unknown"),
   SUN(1, 0, CelObjectCategory.CLASSICS, "sun"),
   MOON(2, 1, CelObjectCategory.CLASSICS, "moon"),
   MERCURY(3, 2, CelObjectCategory.CLASSICS, "mercury"),
   VENUS(4, 3, CelObjectCategory.CLASSICS, "venus"),
   AARDE(5, 14, CelObjectCategory.CLASSICS, "earth"),
   MARS(6, 4, CelObjectCategory.CLASSICS, "mars"),
   JUPITER(7, 5, CelObjectCategory.CLASSICS, "jupiter"),
   SATURN(8, 6, CelObjectCategory.CLASSICS, "saturn"),
   URANUS(9, 7, CelObjectCategory.MODERN, "uranus"),
   NEPTUNE(10, 8, CelObjectCategory.MODERN, "neptune"),
   PLUTO(11, 9, CelObjectCategory.MODERN, "pluto"),
   CHEIRON(12, 15, CelObjectCategory.CENTAURS, "cheiron"),
   MEAN_NODE(13, 10, CelObjectCategory.INTERSECTIONS, "meannode"),
   TRUE_NODE(14, 11, CelObjectCategory.INTERSECTIONS, "truenode"),
   PHOLUS(15, 16, CelObjectCategory.CENTAURS, "pholus"),
   CERES(16, 17, CelObjectCategory.ASTEROIDS, "ceres"),
   PALLAS(17, 18, CelObjectCategory.ASTEROIDS, "pallas"),
   JUNO(18, 19, CelObjectCategory.ASTEROIDS, "juno"),
   VESTA(19, 20, CelObjectCategory.ASTEROIDS, "vesta"),
   NESSUS(20, 17066, CelObjectCategory.CENTAURS, "nessus"),
   HUYA(21, 48628, CelObjectCategory.EXTRA_PLUT, "huya"),
   MAKEMAKE(22, 146472, CelObjectCategory.EXTRA_PLUT, "makemake"),
   HAUMEA(23, 146108, CelObjectCategory.EXTRA_PLUT, "haumea"),
   ERIS(24, 146199, CelObjectCategory.EXTRA_PLUT, "eris"),
   IXION(25, 38978, CelObjectCategory.EXTRA_PLUT, "ixion"),
   ORCUS(26, 100482, CelObjectCategory.EXTRA_PLUT, "orcus"),
   QUAOAR(27, 60000, CelObjectCategory.EXTRA_PLUT, "quaoar"),
   SEDNA(28, 100377, CelObjectCategory.EXTRA_PLUT, "sedna"),
   VARUNA(29, 30000, CelObjectCategory.EXTRA_PLUT, "varuna");

   private static final String RB_PREFIX = "gen.lookup.celobject.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
   private final int id;
   private final long seId;
   private final String nameForRB;
   private final CelObjectCategory category;

   CelestialObjects(final int id, final long seId, final CelObjectCategory category, final String nameForRB) {
      this.id = id;
      this.seId = seId;
      this.category = category;
      this.nameForRB = nameForRB;
   }

   public int getId() {
      return id;
   }

   public long getSeId() {
      return seId;
   }

   public CelObjectCategory getCategory() {
      return category;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

   public String getRbKeyForDescription() {
      return RB_PREFIX + nameForRB + RB_DESCRIPTION_POSTFIX;
   }

   public CelestialObjects getCelObjectForId(int id) {
      for (CelestialObjects celObject : CelestialObjects.values()) {
         if (celObject.getId() == id) {
            return celObject;
         }
      }
      return CelestialObjects.UNKNOWN;
   }

}


