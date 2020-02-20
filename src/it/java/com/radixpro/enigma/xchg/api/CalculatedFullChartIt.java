/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.astron.main.CelObjectPosition;
import com.radixpro.enigma.be.astron.main.MundaneValues;
import com.radixpro.enigma.xchg.domain.*;
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
   // positions in array
   private static final int SUN = 0;
   private static final int MOON = 1;
   private static final int MERCURY = 2;
   private static final int VENUS = 3;
   private static final int MARS = 4;
   private static final int JUPITER = 5;
   private static final int SATURN = 6;
   private static final int URANUS = 7;
   private static final int NEPTUNE = 8;
   private static final int PLUTO = 9;
   private static final int CHEIRON = 10;
   private static final int MEAN_NODE = 11;

   private static final boolean GREGORIAN = true;
   private static final boolean HELIOCENTRIC = false;
   private static final boolean TOPOCENTRIC = false;
   private static final boolean SIDEREAL = false;
   private static final Ayanamshas AYANAMSHA = Ayanamshas.HUBER;
   private static final HouseSystems HOUSE_SYSTEM = HouseSystems.PLACIDUS;
   private static final double DELTA = 0.0002;   // 0.72 second of arc
   private static final double GEO_LAT = 52.21666667;
   private static final double GEO_LONG = 6.9;
   private Location location;
   private SimpleDateTime dateTime;
   private CalculationSettings settings;
   private GeographicCoordinate longCoordinate;
   private GeographicCoordinate latCoordinate;


   @Before
   public void setUp() {
      latCoordinate = new GeographicCoordinate(52, 13, 0, "N", GEO_LAT);
      longCoordinate = new GeographicCoordinate(6, 54, 0, "E", GEO_LONG);
      location = new Location(longCoordinate, latCoordinate, "Enschede");
      final SimpleDate date = new SimpleDate(YEAR, MONTH, DAY, GREGORIAN);
      final SimpleTime time = new SimpleTime(HOUR, MINUTE, SECOND);
      dateTime = new SimpleDateTime(date, time);
      List<CelestialObjects> requestedBodies = new ArrayList<>();
      requestedBodies.add(CelestialObjects.SUN);
      requestedBodies.add(CelestialObjects.MOON);
      requestedBodies.add(CelestialObjects.MERCURY);
      requestedBodies.add(CelestialObjects.VENUS);
      requestedBodies.add(CelestialObjects.MARS);
      requestedBodies.add(CelestialObjects.JUPITER);
      requestedBodies.add(CelestialObjects.SATURN);
      requestedBodies.add(CelestialObjects.URANUS);
      requestedBodies.add(CelestialObjects.NEPTUNE);
      requestedBodies.add(CelestialObjects.PLUTO);
      requestedBodies.add(CelestialObjects.CHEIRON);
      requestedBodies.add(CelestialObjects.MEAN_NODE);
      settings = new CalculationSettings(requestedBodies, HOUSE_SYSTEM, AYANAMSHA, SIDEREAL, TOPOCENTRIC, HELIOCENTRIC);
   }

   @Test
   public void testDefaultApi() {
      CalculatedFullChart calculatedFullChart = new CalculatedFullChart(dateTime, location, settings);
      assertEquals(dateTime, calculatedFullChart.getDateTime());
      assertEquals(location, calculatedFullChart.getLocation());
      assertEquals(settings, calculatedFullChart.getSettings());

      // celestial bodies, all longitudes and samples for the other values
      List<CelObjectPosition> bodies = calculatedFullChart.getBodies();

      // obliquity
      assertEquals(23.447072308, calculatedFullChart.getObliquity(), DELTA);

      // longitudes
      assertEquals(2434406.81770833, calculatedFullChart.getJdNrForUt(), 0.00001);  // Jd for UT
      assertEquals(309.1185106790, bodies.get(SUN).getEclipticalPosition().getMainPosition(), DELTA);  // Sun
      assertEquals(121.764999, bodies.get(MOON).getEclipticalPosition().getMainPosition(), DELTA);   // Moon (test was 121.7647365976)
      assertEquals(305.8677131365, bodies.get(MERCURY).getEclipticalPosition().getMainPosition(), DELTA);  // Mercury
      assertEquals(356.0098349692, bodies.get(VENUS).getEclipticalPosition().getMainPosition(), DELTA);  // Venus
      assertEquals(352.5987342027, bodies.get(MARS).getEclipticalPosition().getMainPosition(), DELTA);  // Mars
      assertEquals(41.9520277954, bodies.get(JUPITER).getEclipticalPosition().getMainPosition(), DELTA);  // Jupiter
      assertEquals(207.2578072264, bodies.get(SATURN).getEclipticalPosition().getMainPosition(), DELTA);  // Saturn
      assertEquals(105.5629669894, bodies.get(URANUS).getEclipticalPosition().getMainPosition(), DELTA);  // Uranus
      assertEquals(203.8824860279, bodies.get(NEPTUNE).getEclipticalPosition().getMainPosition(), DELTA);  // Neptune
      assertEquals(142.3620870053, bodies.get(PLUTO).getEclipticalPosition().getMainPosition(), DELTA);  // Pluto
      assertEquals(286.3288471420, bodies.get(CHEIRON).getEclipticalPosition().getMainPosition(), DELTA);  // Cheiron
      assertEquals(312.5802072448, bodies.get(MEAN_NODE).getEclipticalPosition().getMainPosition(), DELTA);  // mean node
      // speed in longitude
      assertEquals(1.0152777778, bodies.get(SUN).getEclipticalPosition().getMainSpeed(), DELTA);  // Sun
      assertEquals(0.7630555556, bodies.get(MARS).getEclipticalPosition().getMainSpeed(), DELTA);  // Mars
      assertEquals(-0.0386111111, bodies.get(URANUS).getEclipticalPosition().getMainSpeed(), DELTA);  // Uranus
      assertEquals(0.0919444444, bodies.get(CHEIRON).getEclipticalPosition().getMainSpeed(), DELTA);  // Cheiron
      // latitude
      assertEquals(0.9758333333, bodies.get(MOON).getEclipticalPosition().getDeviationPosition(), DELTA); // Moon
      assertEquals(-0.62967, bodies.get(MARS).getEclipticalPosition().getDeviationPosition(), DELTA);  // Mars
      assertEquals(2.553578, bodies.get(SATURN).getEclipticalPosition().getDeviationPosition(), DELTA);  // Saturn
      assertEquals(9.620795, bodies.get(PLUTO).getEclipticalPosition().getDeviationPosition(), DELTA);  // Pluto
      // speed in latitude
      assertEquals(-1.09333333, bodies.get(MOON).getEclipticalPosition().getDeviationSpeed(), DELTA); // Moon
      assertEquals(-0.02305556, bodies.get(MERCURY).getEclipticalPosition().getDeviationSpeed(), DELTA); // Mercury
      assertEquals(0.0, bodies.get(URANUS).getEclipticalPosition().getDeviationSpeed(), DELTA); // Uranus
      assertEquals(0.00361111, bodies.get(CHEIRON).getEclipticalPosition().getDeviationSpeed(), DELTA); // Cheiron

      // Right ascension
      assertEquals(356.1744157, bodies.get(VENUS).getEquatorialPosition().getMainPosition(), DELTA); // Venus
      assertEquals(39.8219374, bodies.get(JUPITER).getEquatorialPosition().getMainPosition(), DELTA); // Jupiter
      assertEquals(206.2260786, bodies.get(SATURN).getEquatorialPosition().getMainPosition(), DELTA); // Saturn
      assertEquals(202.7365977, bodies.get(NEPTUNE).getEquatorialPosition().getMainPosition(), DELTA); // Neptune

      // speed in Right Ascension
      assertEquals(12.2475, bodies.get(MOON).getEquatorialPosition().getMainSpeed(), DELTA);  // Moon
      assertEquals(1.7525, bodies.get(MERCURY).getEquatorialPosition().getMainSpeed(), DELTA);  // Mercury
      assertEquals(0.90472222, bodies.get(VENUS).getEquatorialPosition().getMainSpeed(), DELTA);  // Venus
      assertEquals(-0.0416667, bodies.get(URANUS).getEquatorialPosition().getMainSpeed(), DELTA);  // Uranus

      // Declination
      assertEquals(-20.788611111, bodies.get(MERCURY).getEquatorialPosition().getDeviationPosition(), DELTA); // Mercury
      assertEquals(14.493608, bodies.get(JUPITER).getEquatorialPosition().getDeviationPosition(), DELTA); // Jupiter
      assertEquals(23.13472222, bodies.get(PLUTO).getEquatorialPosition().getDeviationPosition(), DELTA); // Pluto
      assertEquals(-17.036888, bodies.get(MEAN_NODE).getEquatorialPosition().getDeviationPosition(), DELTA); // Mean node

      // Speed in declination
      assertEquals(0.2677778, bodies.get(SUN).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Sun
      assertEquals(-3.7541667, bodies.get(MOON).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Moon
      assertEquals(0.0, bodies.get(SATURN).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Saturn
      assertEquals(0.0102118, bodies.get(PLUTO).getEquatorialPosition().getDeviationSpeed(), DELTA);  // Pluto

      // Distance
      assertEquals(0.98501628, bodies.get(SUN).getEclipticalPosition().getDistancePosition(), DELTA);  // Sun
      assertEquals(30.0333179, bodies.get(NEPTUNE).getEclipticalPosition().getDistancePosition(), DELTA);  // Neptune
      assertEquals(34.59775899, bodies.get(PLUTO).getEclipticalPosition().getDistancePosition(), DELTA);  // Pluto
      assertEquals(13.08780192, bodies.get(CHEIRON).getEclipticalPosition().getDistancePosition(), DELTA);  // Cheiron

      // Speed in distance
      assertEquals(0.00001306, bodies.get(MOON).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Moon
      assertEquals(-0.00742368, bodies.get(VENUS).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Venus
      assertEquals(0.01611306, bodies.get(JUPITER).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Jupiter
      assertEquals(-0.01626397, bodies.get(SATURN).getEclipticalPosition().getDistanceSpeed(), DELTA);  // Saturn

      // Azimuth
      assertEquals(302.82472222, bodies.get(SUN).getHorizontalPosition().getAzimuth(), DELTA); // Sun
      assertEquals(261.0738577, bodies.get(MARS).getHorizontalPosition().getAzimuth(), DELTA); // Mars
      assertEquals(49.44222222, bodies.get(NEPTUNE).getHorizontalPosition().getAzimuth(), DELTA); // Neptune
      assertEquals(299.5225, bodies.get(MEAN_NODE).getHorizontalPosition().getAzimuth(), DELTA); // Mean node

      // Altitude
      assertEquals(-2.942777778, bodies.get(MOON).getHorizontalPosition().getAltitude(), DELTA);
      assertEquals(-18.505, bodies.get(JUPITER).getHorizontalPosition().getAltitude(), DELTA);  // Jupiter
      assertEquals(19.25527778, bodies.get(SATURN).getHorizontalPosition().getAltitude(), DELTA);  // Saturn
      assertEquals(14.26694444, bodies.get(CHEIRON).getHorizontalPosition().getAltitude(), DELTA);  // Cheiron

      // houses longitude
      MundaneValues mundaneValues = calculatedFullChart.getHouseValues();
      assertEquals(249.5350953050, mundaneValues.getArmc(), DELTA);
      assertEquals(314.7210736953, mundaneValues.getAscendant().getLongitude(), DELTA);
      assertEquals(251.1002358654, mundaneValues.getMc().getLongitude(), DELTA);
      assertEquals(163.3350501542, mundaneValues.getVertex().getLongitude(), DELTA);
      assertEquals(337.8647433572, mundaneValues.getEastpoint().getLongitude(), DELTA);
      assertEquals(314.7210736953, mundaneValues.getCusps().get(1).getLongitude(), DELTA);
      assertEquals(16.4262924252, mundaneValues.getCusps().get(2).getLongitude(), DELTA);
      assertEquals(50.1257249109, mundaneValues.getCusps().get(3).getLongitude(), DELTA);
      assertEquals(71.1002358654, mundaneValues.getCusps().get(4).getLongitude(), DELTA);
      assertEquals(88.3499759259, mundaneValues.getCusps().get(5).getLongitude(), DELTA);
      assertEquals(106.7107115059, mundaneValues.getCusps().get(6).getLongitude(), DELTA);
      assertEquals(134.7210736953, mundaneValues.getCusps().get(7).getLongitude(), DELTA);
      assertEquals(196.4262924252, mundaneValues.getCusps().get(8).getLongitude(), DELTA);
      assertEquals(230.1257249109, mundaneValues.getCusps().get(9).getLongitude(), DELTA);
      assertEquals(251.1002358654, mundaneValues.getCusps().get(10).getLongitude(), DELTA);
      assertEquals(268.3499759259, mundaneValues.getCusps().get(11).getLongitude(), DELTA);
      assertEquals(286.7107115059, mundaneValues.getCusps().get(12).getLongitude(), DELTA);
      // Right ascension, only samples
      assertEquals(317.1877777778, mundaneValues.getAscendant().getEquatorialPosition().getRightAscension(), DELTA);
      assertEquals(88.20138888889, mundaneValues.getCusps().get(5).getEquatorialPosition().getRightAscension(), DELTA);
      assertEquals(227.6802777778, mundaneValues.getCusps().get(9).getEquatorialPosition().getRightAscension(), DELTA);
      assertEquals(288.12, mundaneValues.getCusps().get(12).getEquatorialPosition().getRightAscension(), DELTA);
      // Declination, only samples
      assertEquals(-16.4227777778, mundaneValues.getAscendant().getEquatorialPosition().getDeclination(), DELTA);
      assertEquals(17.7804811, mundaneValues.getCusps().get(3).getEquatorialPosition().getDeclination(), DELTA);
      assertEquals(-6.46055555556, mundaneValues.getCusps().get(8).getEquatorialPosition().getDeclination(), DELTA);
      assertEquals(-23.4366666667, mundaneValues.getCusps().get(11).getEquatorialPosition().getDeclination(), DELTA);
      // Azimuth, only samples
      assertEquals(236.93, mundaneValues.getCusps().get(2).getHorizontalPosition().getAzimuth(), DELTA);
      assertEquals(180.0, mundaneValues.getCusps().get(4).getHorizontalPosition().getAzimuth(), DELTA);
      assertEquals(21.814444444, mundaneValues.getCusps().get(9).getHorizontalPosition().getAzimuth(), DELTA);
      assertEquals(324.375, mundaneValues.getCusps().get(12).getHorizontalPosition().getAzimuth(), DELTA);
      // Altitude, only samples
      assertEquals(0.0, mundaneValues.getCusps().get(1).getHorizontalPosition().getAltitude(), DELTA);
      assertEquals(-17.46583333, mundaneValues.getCusps().get(3).getHorizontalPosition().getAltitude(), DELTA);
      assertEquals(15.39415215, mundaneValues.getCusps().get(8).getHorizontalPosition().getAltitude(), DELTA);
      assertEquals(12.604166667, mundaneValues.getCusps().get(11).getHorizontalPosition().getAltitude(), DELTA);
   }

}
