/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.core;

import com.radixpro.enigma.be.astron.assist.SePositionResultCelObjects;
import com.radixpro.enigma.be.astron.assist.SePositionResultHouses;
import com.radixpro.enigma.xchg.domain.Location;
import lombok.NonNull;
import lombok.val;
import org.apache.log4j.Logger;
import swisseph.SweDate;
import swisseph.SwissEph;
import swisseph.SwissLib;

/**
 * Simple wrapper to access the Java port to the SE by Thomas Mack.
 * Implemented as a singleton to prevent multiple instantations.
 * Always use this wrapper to access the Java port.
 */
public class SeFrontend {

   private static final Logger LOG = Logger.getLogger(SeFrontend.class);
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
         LOG.info("Created singleton instance for SeFrontend.");
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
      val sweDate = new SweDate();
      return sweDate.getJDfromUTC(year, month, day, hour, min, sec, gregFlag, false);
   }

   /**
    * Calculate ecliptical or equatorial positions for a body
    *
    * @param jdUt  Julian Day based on EPhemeris Time
    * @param id    indicates the body
    * @param flags combined settings for the SE
    * @return calculated positions
    */
   public SePositionResultCelObjects getPositionsForCelBody(final double jdUt, final int id, final int flags) {
      double[] allPositions = new double[6];
      var errorMsg = new StringBuffer();  // StringBuilder not possible because Java Port to the SE uses a StringBuffer.
      swissEph.swe_calc_ut(jdUt, id, flags, allPositions, errorMsg);
      return new SePositionResultCelObjects(allPositions, errorMsg.toString());
   }

   /**
    * Calculate horizontal positions for a body
    *
    * @param jdUt     Julian day based on ephemeris time
    * @param eclCoord ecliptical coördinates: index 0 = longitude, 1 = latitude, 2 = distance
    * @param location geographic latitude and longitude
    * @param flags    combined settings for the SE
    * @return calculated positions
    */
   public double[] getHorizontalPosition(final double jdUt, @NonNull final double[] eclCoord,
                                         @NonNull final Location location, final int flags) {
      double[] geoPos = {location.getGeoLong(), location.getGeoLat(), 0.0};
      double[] eclPos = {eclCoord[0], eclCoord[1], eclCoord[2]};
      val atPress = 0.0;
      val atTemp = 0.0;
      var azAlt = new double[3];
      swissEph.swe_azalt(jdUt, flags, geoPos, atPress, atTemp, eclPos, azAlt);
      return azAlt;
   }

   /**
    * Calculate positions for houses
    *
    * @param jdUt      Julian Day based on Universal Time
    * @param flags     combined settings for the SE
    * @param location  geographic latitude and longitude
    * @param system    the housesystem to use
    * @param nrOfCusps number of cusps for the current housesystem
    * @return calculated positions
    */
   public SePositionResultHouses getPositionsForHouses(final double jdUt, final int flags, @NonNull final Location location,
                                                       final int system, final int nrOfCusps) {
      var cusps = new double[nrOfCusps + 1];
      var ascMc = new double[10];
      swissEph.swe_houses(jdUt, flags, location.getGeoLat(), location.getGeoLong(), system, cusps, ascMc);
      return new SePositionResultHouses(ascMc, cusps);
   }

   public double[] convertToEquatorial(@NonNull final double[] eclipticValues, final double obliquity) {
      SwissLib swissLib = new SwissLib();
      var equatorialValues = new double[3];
      swissLib.swe_cotrans(eclipticValues, equatorialValues, -obliquity);  // obliquity must be negative !
      return equatorialValues;
   }

   public boolean isValidDate(final int year, final int month, final int day, final boolean gregorian) {
      val sweDate1 = new SweDate(year, month, day, 0.0, gregorian);
      val calculatedJulDay = sweDate1.getJulDay();
      val sweDate2 = new SweDate(calculatedJulDay, gregorian);
      return (sweDate1.getYear() == sweDate2.getYear() && sweDate1.getMonth() == sweDate2.getMonth() &&
            sweDate1.getDay() == sweDate2.getDay());
   }

}

