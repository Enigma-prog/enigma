/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.be.astron.assist.*;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.be.astron.main.CelBody;
import com.radixpro.enigma.be.astron.main.HouseValues;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for CalculatedFullChart.
 */
public class CalculatedFullChartIt {

   private static final int YEAR = 1953;
   private static final int MONTH = 1;
   private static final int DAY = 29;
   private static final int HOUR = 7;
   private static final int MINUTE = 37;
   private static final int SECOND = 30;
   private static final boolean GREGORIAN = true;
   private static final boolean HELIOCENTRIC = false;
   private static final boolean TOPOCENTRIC = false;
   private static final boolean SIDEREAL = false;
   private static final AyanamshasToCalculate AYANAMSHA = AyanamshasToCalculate.HUBER;
   private static final HouseSystemsToCalculate HOUSE_SYSTEM = HouseSystemsToCalculate.PLACIDUS;
   private static final double DELTA = 0.0002;   // 0.72 second of arc
   private static final double GEO_LAT = 52.21666667;
   private static final double GEO_LONG = 6.9;
   private Location location;
   private SimpleDateTime dateTime;
   private CalculationSettings settings;

   @Before
   public void setUp() {
      SeFrontend seFrontend = SeFrontend.getFrontend();
      location = new Location(GEO_LAT, GEO_LONG);
      final SimpleDate date = new SimpleDate(YEAR, MONTH, DAY, GREGORIAN);
      final SimpleTime time = new SimpleTime(HOUR, MINUTE, SECOND);
      dateTime = new SimpleDateTime(seFrontend, date, time);
      List<CelBodiesToCalculate> requestedBodies = new ArrayList<>();
      requestedBodies.add(CelBodiesToCalculate.SUN);
      requestedBodies.add(CelBodiesToCalculate.MOON);
      requestedBodies.add(CelBodiesToCalculate.MERCURY);
      requestedBodies.add(CelBodiesToCalculate.VENUS);
      requestedBodies.add(CelBodiesToCalculate.MARS);
      requestedBodies.add(CelBodiesToCalculate.JUPITER);
      requestedBodies.add(CelBodiesToCalculate.SATURN);
      requestedBodies.add(CelBodiesToCalculate.URANUS);
      requestedBodies.add(CelBodiesToCalculate.NEPTUNE);
      requestedBodies.add(CelBodiesToCalculate.PLUTO);
      requestedBodies.add(CelBodiesToCalculate.CHIRON);
      requestedBodies.add(CelBodiesToCalculate.MEAN_NODE);
      settings = new CalculationSettings(requestedBodies, HOUSE_SYSTEM, AYANAMSHA, SIDEREAL, TOPOCENTRIC, HELIOCENTRIC);
   }

   @Test
   public void testDefaultApi() {
      CalculatedFullChart calculatedFullChart = new CalculatedFullChart(dateTime, location, settings);
      assertEquals(dateTime, calculatedFullChart.getDateTime());
      assertEquals(location, calculatedFullChart.getLocation());
      assertEquals(settings, calculatedFullChart.getSettings());

      // celestial bodies, all longitudes and samples for the other values
      List<CelBody> bodies = calculatedFullChart.getBodies();
      // longitudes
      assertEquals(2434406.81806005, dateTime.getJdEt(), 0.00001);  // ET
      assertEquals(2434406.81770833, dateTime.getJdUt(), 0.00001);  // UT
      assertEquals(309.1185106790, bodies.get(0).getEclipticalPosition().getMainPosition(), DELTA);  // Sun
      assertEquals(121.764999, bodies.get(1).getEclipticalPosition().getMainPosition(), DELTA);   // Moon (test was 121.7647365976)
      assertEquals(305.8677131365, bodies.get(2).getEclipticalPosition().getMainPosition(), DELTA);  // Mercury
      assertEquals(356.0098349692, bodies.get(3).getEclipticalPosition().getMainPosition(), DELTA);  // Venus
      assertEquals(352.5987342027, bodies.get(4).getEclipticalPosition().getMainPosition(), DELTA);  // Mars
      assertEquals(41.9520277954, bodies.get(5).getEclipticalPosition().getMainPosition(), DELTA);  // Jupiter
      assertEquals(207.2578072264, bodies.get(6).getEclipticalPosition().getMainPosition(), DELTA);  // Saturn
      assertEquals(105.5629669894, bodies.get(7).getEclipticalPosition().getMainPosition(), DELTA);  // Uranus
      assertEquals(203.8824860279, bodies.get(8).getEclipticalPosition().getMainPosition(), DELTA);  // Neptune
      assertEquals(142.3620870053, bodies.get(9).getEclipticalPosition().getMainPosition(), DELTA);  // Pluto
      assertEquals(286.3288471420, bodies.get(10).getEclipticalPosition().getMainPosition(), DELTA);  // Cheiron
      assertEquals(312.5802072448, bodies.get(11).getEclipticalPosition().getMainPosition(), DELTA);  // mean node
      // speed in longitude
      assertEquals(1.0152777778, bodies.get(0).getEclipticalPosition().getMainSpeed(), DELTA);  // Sun
      assertEquals(0.7630555556, bodies.get(4).getEclipticalPosition().getMainSpeed(), DELTA);  // Mars
      assertEquals(-0.0386111111, bodies.get(7).getEclipticalPosition().getMainSpeed(), DELTA);  // Uranus
      assertEquals(0.0919444444, bodies.get(10).getEclipticalPosition().getMainSpeed(), DELTA);  // Cheiron
      // latitude
      assertEquals(0.9758333333, bodies.get(1).getEclipticalPosition().getDeviationPosition(), DELTA); // Moon
      assertEquals(-0.62967, bodies.get(4).getEclipticalPosition().getDeviationPosition(), DELTA);  // Mars
      assertEquals(2.553578, bodies.get(6).getEclipticalPosition().getDeviationPosition(), DELTA);  // Saturn
      assertEquals(9.620795, bodies.get(9).getEclipticalPosition().getDeviationPosition(), DELTA);  // Pluto
      // speed in latitude
      assertEquals(-1.09333333, bodies.get(1).getEclipticalPosition().getDeviationSpeed(), DELTA); // Moon
      assertEquals(-0.02305556, bodies.get(2).getEclipticalPosition().getDeviationSpeed(), DELTA); // Mercury
      assertEquals(0.0, bodies.get(7).getEclipticalPosition().getDeviationSpeed(), DELTA); // Uranus
      assertEquals(0.00361111, bodies.get(10).getEclipticalPosition().getDeviationSpeed(), DELTA); // Cheiron

      // Right ascension
      assertEquals(356.1744157, bodies.get(3).getEquatorialPosition().getMainPosition(), DELTA); // Venus
      assertEquals(39.8219374, bodies.get(5).getEquatorialPosition().getMainPosition(), DELTA); // Jupiter
      assertEquals(206.2260786, bodies.get(6).getEquatorialPosition().getMainPosition(), DELTA); // Saturn
      assertEquals(202.7365977, bodies.get(8).getEquatorialPosition().getMainPosition(), DELTA); // Neptune

      // speed in Right Ascension
      assertEquals(12.2475, bodies.get(1).getEquatorialPosition().getMainSpeed(), DELTA);  // Moon
      assertEquals(1.7525, bodies.get(2).getEquatorialPosition().getMainSpeed(), DELTA);  // Mercury
      assertEquals(0.90472222, bodies.get(3).getEquatorialPosition().getMainSpeed(), DELTA);  // Venus
      assertEquals(-0.0416667, bodies.get(7).getEquatorialPosition().getMainSpeed(), DELTA);  // Uranus

      // Declination
      assertEquals(-20.788611111, bodies.get(2).getEquatorialPosition().getDeviationPosition(), DELTA); // Mercury
      assertEquals(14.493608, bodies.get(5).getEquatorialPosition().getDeviationPosition(), DELTA); // Jupiter
      assertEquals(23.13472222, bodies.get(9).getEquatorialPosition().getDeviationPosition(), DELTA); // Pluto
      assertEquals(-17.036888, bodies.get(11).getEquatorialPosition().getDeviationPosition(), DELTA); // Mean node

      // Speed in declination
      assertEquals(0.2677778, bodies.get(0).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Sun
      assertEquals(-3.7541667, bodies.get(1).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Moon
      assertEquals(0.0, bodies.get(6).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Saturn
      assertEquals(0.0102118, bodies.get(9).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Pluto

      // Distance
      assertEquals(0.98501628, bodies.get(0).getEclipticalPosition().getDistancePosition(), DELTA);  // Sun
      assertEquals(30.0333179, bodies.get(8).getEclipticalPosition().getDistancePosition(), DELTA);  // Neptune
      assertEquals(34.59775899, bodies.get(9).getEclipticalPosition().getDistancePosition(), DELTA);  // Pluto
      assertEquals(13.08780192, bodies.get(10).getEclipticalPosition().getDistancePosition(), DELTA);  // Cheiron

      // Speed in distance
      assertEquals(0.00001306, bodies.get(1).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Moon
      assertEquals(-0.00742368, bodies.get(3).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Venus
      assertEquals(0.01611306, bodies.get(5).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Jupiter
      assertEquals(-0.01626397, bodies.get(6).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Saturn

      // Azimuth
      assertEquals(302.82472222, bodies.get(0).getHorizontalPosition().getAzimuth(), DELTA); // Sun
      assertEquals(261.0738577, bodies.get(4).getHorizontalPosition().getAzimuth(), DELTA); // Mars
      assertEquals(49.44222222, bodies.get(8).getHorizontalPosition().getAzimuth(), DELTA); // Neptune
      assertEquals(299.5225, bodies.get(11).getHorizontalPosition().getAzimuth(), DELTA); // Mean node

      // Altitude
      assertEquals(-2.942777778, bodies.get(1).getHorizontalPosition().getAltitude(), DELTA);  // Maan
      assertEquals(-18.505, bodies.get(5).getHorizontalPosition().getAltitude(), DELTA);  // Jupiter
      assertEquals(19.25527778, bodies.get(6).getHorizontalPosition().getAltitude(), DELTA);  // Saturn
      assertEquals(14.26694444, bodies.get(10).getHorizontalPosition().getAltitude(), DELTA);  // Cheiron

      // houses
      HouseValues houseValues = calculatedFullChart.getHouseValues();
      assertEquals(249.5350953050, houseValues.getArmc(), DELTA);
      assertEquals(314.7210736953, houseValues.getAscendant(), DELTA);
      assertEquals(251.1002358654, houseValues.getMc(), DELTA);
      assertEquals(163.3350501542, houseValues.getVertex(), DELTA);
      assertEquals(337.8647433572, houseValues.getEastpoint(), DELTA);      // TODO calculate or find reference data for eastpoint
      assertEquals(314.7210736953, houseValues.getCusps().get(1), DELTA);
      assertEquals(16.4262924252, houseValues.getCusps().get(2), DELTA);
      assertEquals(50.1257249109, houseValues.getCusps().get(3), DELTA);
      assertEquals(71.1002358654, houseValues.getCusps().get(4), DELTA);
      assertEquals(88.3499759259, houseValues.getCusps().get(5), DELTA);
      assertEquals(106.7107115059, houseValues.getCusps().get(6), DELTA);
      assertEquals(134.7210736953, houseValues.getCusps().get(7), DELTA);
      assertEquals(196.4262924252, houseValues.getCusps().get(8), DELTA);
      assertEquals(230.1257249109, houseValues.getCusps().get(9), DELTA);
      assertEquals(251.1002358654, houseValues.getCusps().get(10), DELTA);
      assertEquals(268.3499759259, houseValues.getCusps().get(11), DELTA);
      assertEquals(286.7107115059, houseValues.getCusps().get(12), DELTA);
   }

}
