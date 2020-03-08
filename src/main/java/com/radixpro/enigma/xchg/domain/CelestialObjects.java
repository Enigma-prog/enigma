/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;

/**
 * Celestial bodies and id's to access the SE.
 */
@Getter
public enum CelestialObjects {
   UNKNOWN(0, -1, CelObjectCategory.UNKNOWN, "celobject.unknown"),
   SUN(1, 0, CelObjectCategory.CLASSICS, "celobject.sun"),
   MOON(2, 1, CelObjectCategory.CLASSICS, "celobject.moon"),
   MERCURY(3, 2, CelObjectCategory.CLASSICS, "celobject.mercury"),
   VENUS(4, 3, CelObjectCategory.CLASSICS, "celobject.venus"),
   EARTH(5, 14, CelObjectCategory.CLASSICS, "celobject.earth"),
   MARS(6, 4, CelObjectCategory.CLASSICS, "celobject.mars"),
   JUPITER(7, 5, CelObjectCategory.CLASSICS, "celobject.jupiter"),
   SATURN(8, 6, CelObjectCategory.CLASSICS, "celobject.saturn"),
   URANUS(9, 7, CelObjectCategory.MODERN, "celobject.uranus"),
   NEPTUNE(10, 8, CelObjectCategory.MODERN, "celobject.neptune"),
   PLUTO(11, 9, CelObjectCategory.MODERN, "celobject.pluto"),
   CHEIRON(12, 15, CelObjectCategory.CENTAURS, "celobject.cheiron"),
   MEAN_NODE(13, 10, CelObjectCategory.INTERSECTIONS, "celobject.meannode"),
   TRUE_NODE(14, 11, CelObjectCategory.INTERSECTIONS, "celobject.truenode"),
   PHOLUS(15, 16, CelObjectCategory.CENTAURS, "celobject.pholus"),
   CERES(16, 17, CelObjectCategory.ASTEROIDS, "celobject.ceres"),
   PALLAS(17, 18, CelObjectCategory.ASTEROIDS, "celobject.pallas"),
   JUNO(18, 19, CelObjectCategory.ASTEROIDS, "celobject.juno"),
   VESTA(19, 20, CelObjectCategory.ASTEROIDS, "celobject.vesta"),
   NESSUS(20, 17066, CelObjectCategory.CENTAURS, "celobject.nessus"),
   HUYA(21, 48628, CelObjectCategory.EXTRA_PLUT, "celobject.huya"),
   MAKEMAKE(22, 146472, CelObjectCategory.EXTRA_PLUT, "celobject.makemake"),
   HAUMEA(23, 146108, CelObjectCategory.EXTRA_PLUT, "celobject.haumea"),
   ERIS(24, 146199, CelObjectCategory.EXTRA_PLUT, "celobject.eris"),
   IXION(25, 38978, CelObjectCategory.EXTRA_PLUT, "celobject.ixion"),
   ORCUS(26, 100482, CelObjectCategory.EXTRA_PLUT, "celobject.orcus"),
   QUAOAR(27, 60000, CelObjectCategory.EXTRA_PLUT, "celobject.quaoar"),
   SEDNA(28, 100377, CelObjectCategory.EXTRA_PLUT, "celobject.sedna"),
   VARUNA(29, 30000, CelObjectCategory.EXTRA_PLUT, "celobject.varuna");

   private final int id;
   private final long seId;
   private final String nameForRB;
   private final CelObjectCategory category;

   CelestialObjects(final int id, final long seId, @NonNull final CelObjectCategory category,
                    @NonNull final String nameForRB) {
      this.id = id;
      this.seId = seId;
      this.category = category;
      this.nameForRB = nameForRB;
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


