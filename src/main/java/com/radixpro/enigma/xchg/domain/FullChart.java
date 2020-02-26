/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.be.astron.assist.CombinedFlags;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.be.astron.main.CelObjectPosition;
import com.radixpro.enigma.be.astron.main.JulianDay;
import com.radixpro.enigma.be.astron.main.MundaneValues;
import com.radixpro.enigma.be.astron.main.Obliquity;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A 'full' chart with information on all positions.
 */
public class FullChart {

   @Getter
   private final SimpleDateTime simpleDateTime;
   @Getter
   private final Location location;
   @Getter
   private final CalculationSettings settings;
   @Getter
   private MundaneValues mundaneValues;
   @Getter
   private List<CelObjectPosition> bodies;
   @Getter
   private double obliquity;
   private final SeFrontend seFrontend;
   private final JulianDay julianDay;
   private long flagsValue;
   private List<SeFlags> allFlags;

   public FullChart(@NonNull final SimpleDateTime simpleDateTime, @NonNull final Location location,
                    @NonNull final CalculationSettings settings) {
      this.simpleDateTime = simpleDateTime;
      this.location = location;
      this.settings = settings;
      seFrontend = SeFrontend.getFrontend();
      julianDay = new JulianDay(simpleDateTime);
      calculateFlags();
      calculateHouses();
      calculateBodies();
      calculateObliquity();
   }

   private void calculateFlags() {
      allFlags = new ArrayList<>();
      allFlags.add(SeFlags.SWISSEPH);
      allFlags.add(SeFlags.SPEED);
      if (settings.isHeliocentric()) {
         allFlags.add(SeFlags.HELIOCENTRIC);
      }
      if (settings.isSidereal()) {
         allFlags.add(SeFlags.SIDEREAL);
      }
      if (settings.isTopocentric()) {
         allFlags.add(SeFlags.TOPOCENTRIC);
      }
      final CombinedFlags combinedFlags = new CombinedFlags(allFlags);
      flagsValue = combinedFlags.getCombinedValue();
   }

   private void calculateHouses() {
      mundaneValues = new MundaneValues(seFrontend, julianDay.getJdNrUt(), (int) flagsValue, location, settings.getHouseSystem());
   }

   private void calculateBodies() {
      bodies = new ArrayList<>();
      for (int i = 0; i < settings.getCelBodies().size(); i++) {
         bodies.add(new CelObjectPosition(seFrontend, julianDay.getJdNrUt(), settings.getCelBodies().get(i), location, allFlags));
      }
   }

   private void calculateObliquity() {
      obliquity = new Obliquity(seFrontend, julianDay.getJdNrUt()).getTrueObliquity();
   }

   public double getJulianDayForUt() {
      return julianDay.getJdNrUt();
   }
}
