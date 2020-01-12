/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

import com.radixpro.enigma.xchg.domain.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

   private final double geoLat = 52.0;
   private final double geoLong = 7.0;
   private final double delta = 0.00000001;
   private Location location;

   @Before
   public void setUp() {
      location = new Location(geoLat, geoLong);
   }

   @Test
   public void getGeoLat() {
      assertEquals(geoLat, location.getGeoLat(), delta);
   }

   @Test
   public void getGeoLong() {
      assertEquals(geoLong, location.getGeoLong(), delta);
   }
}