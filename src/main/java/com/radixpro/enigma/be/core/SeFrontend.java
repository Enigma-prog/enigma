/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.core;

import com.radixpro.enigma.be.model.CelBodySingleCoordinates;
import com.radixpro.enigma.be.model.SePositionResultCelBodies;
import com.radixpro.enigma.be.model.SePositionResultHouses;
import swisseph.SweDate;
import swisseph.SwissEph;

/**
 * Simple wrapper to access the Java port to the SE by Thomas Mack.
 * Implemented as a singleton to prevent multiple instantations.
 * Always use this wrapper to access the Java port.
 */
public class SeFrontend {

   private static SeFrontend instance = null;
   private static final String PATH = "se";
   private final SwissEph swissEph = new SwissEph(PATH);

   private SeFrontend() {
      // prevent direct instantiation.
   }

   /**
    * Retrieve instance of singleton for SeFrontend.
    *
    * @return unique instance
    */
   public static SeFrontend getFrontend() {
      if (instance == null) {
         instance = new SeFrontend();
      }
      return instance;
   }

   /**
    * Calculate Julian day, both for ephemeris time and for universal time
    *
    * @param year  year, use astronomical years
    * @param month month, 1..12
    * @param day day of month
    * @param hour hour 0..23
    * @param min minute
    * @param sec sec
    * @param gregFlag true for Gregorian calendar, false for Julian calendar
    * @return Julian Day for ET [0], and Julian Day for UT [1]
    */
   public double[] getJulianDay(final int year, final int month, final int day, final int hour, final int min,
                                final int sec, final boolean gregFlag) {
      final var sweDate = new SweDate();
      final var checkValidInput = false;
      return sweDate.getJDfromUTC(year, month, day, hour, min, sec, gregFlag, checkValidInput);
   }


   /**
    * Calculate ecliptical or equatorial positions for a body
    *
    * @param jdEt  Julian Day based on EPhemeris Time
    * @param id    indicates the body
    * @param flags combined settings for the SE
    * @return calculated positions
    */
   public SePositionResultCelBodies getPositionsForCelBody(final double jdEt, final int id, final int flags) {
      double[] allPositions = new double[6];
      var errorMsg = new StringBuffer();
      swissEph.swe_calc_ut(jdEt, id, flags, allPositions, errorMsg);
      return new SePositionResultCelBodies(allPositions, errorMsg.toString());
   }

   /**
    * Calculate horizontal positions for a body
    *
    * @param jdEt     Julian day based on ephemeris time
    * @param eclCoord ecliptical coördinates
    * @param geoLat   geographic latitude
    * @param geoLong  geographic longitude
    * @param flags    combined settings for the SE
    * @return calculated positions
    */
   public double[] getHorizontalPositionForCelBody(final double jdEt, final CelBodySingleCoordinates eclCoord,
                                                   final double geoLat, final double geoLong, final int flags) {
      double[] geoPos = {geoLong, geoLat, 0.0};
      double[] eclPos = {eclCoord.getMainPosition(), eclCoord.getDeviationPosition(), eclCoord.getDistancePosition()};
      double atPress = 0.0;
      double atTemp = 0.0;
      var azAlt = new double[3];
      swissEph.swe_azalt(jdEt, flags, geoPos, atPress, atTemp, eclPos, azAlt);
      return azAlt;
   }

   /**
    * Calculate positions for houses
    *
    * @param jdUt      Julian Day based on Universal Time
    * @param flags     combined settings for the SE
    * @param geoLat    geographic latitude
    * @param geoLong   geographic longitude
    * @param system    the housesystem to use
    * @param nrOfCusps number of cusps for the current housesystem
    * @return calculated positions
    */
   public SePositionResultHouses getPositionsForHouses(final double jdUt, final int flags, final double geoLat,
                                                       final double geoLong, final int system, final int nrOfCusps) {
      var cusps = new double[nrOfCusps + 1];
      var ascMc = new double[10];
      swissEph.swe_houses(jdUt, flags, geoLat, geoLong, system, cusps, ascMc);
      return new SePositionResultHouses(ascMc, cusps);
   }



}

