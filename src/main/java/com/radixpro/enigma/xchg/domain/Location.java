/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Geographic location. Is part of the persisted data for a chart.
 */
public class Location implements Serializable {

   private final GeographicCoordinate longInput;
   private final GeographicCoordinate latInput;
   private final String name;

   /**
    * Constructor defines all fields.
    *
    * @param longInput geographic longitude.
    * @param latInput  geographic latitude.
    * @param name      Name of location.
    */
   public Location(final GeographicCoordinate longInput, final GeographicCoordinate latInput, final String name) {
      this.longInput = checkNotNull(longInput);
      this.latInput = checkNotNull(latInput);
      this.name = checkNotNull(name);
   }

   public double getGeoLat() {
      return latInput.getValue();
   }

   public double getGeoLong() {
      return longInput.getValue();
   }


   public GeographicCoordinate getLongInput() {
      return longInput;
   }

   public GeographicCoordinate getLatInput() {
      return latInput;
   }

   public String getName() {
      return name;
   }
}
