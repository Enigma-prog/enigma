/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.Serializable;

public class Location implements Serializable {

   private final GeographicCoordinate longInput;
   private final GeographicCoordinate latInput;
   private final String name;
   private double geoLat;
   private double geoLong;


   public Location(final GeographicCoordinate longInput, final GeographicCoordinate latInput, final String name) {
      this.longInput = longInput;
      this.latInput = latInput;
      this.name = name;
   }

   public double getGeoLat() {
      return latInput.getValue();
   }

   public double getGeoLong() {
      return longInput.getValue();
   }

   public String getName() {
      return name;
   }

   public GeographicCoordinate getLongInput() {
      return longInput;
   }

   public GeographicCoordinate getLatInput() {
      return latInput;
   }


}
