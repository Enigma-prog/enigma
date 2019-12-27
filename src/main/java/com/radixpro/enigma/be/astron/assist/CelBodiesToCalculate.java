/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

/**
 * Celestial bodies and id's to access the SE.
 */
public enum CelBodiesToCalculate {
   SUN(0),
   MOON(1),
   MERCURY(2),
   VENUS(3),
   MARS(4),
   JUPITER(5),
   SATURN(6),
   URANUS(7),
   NEPTUNE(8),
   PLUTO(9),
   MEAN_NODE(10),
   TRUE_NODE(11),
   CHIRON(15);

   private final int id;

   CelBodiesToCalculate(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }
}
