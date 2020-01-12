/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.Serializable;

public class Location implements Serializable {
   private final double geoLat;
   private final double geoLong;

   /**
    * DTO for geographic latitude and geographic longitude
    *
    * @param geoLat  Geographic latitude
    * @param geoLong Geographic longitude
    */
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
