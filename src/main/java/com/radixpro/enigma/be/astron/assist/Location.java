/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

public class Location {
   private final double geoLat;
   private final double geoLong;

   public Location(final double geoLat, final double geoLong) {
      this.geoLat = geoLat;
      this.geoLong = geoLong;
   }

   public double getGeoLat() {
      return geoLat;
   }

   public double getGeoLong() {
      return geoLong;
   }
}
