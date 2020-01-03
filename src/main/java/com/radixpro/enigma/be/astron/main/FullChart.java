/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.CalculationSettings;
import com.radixpro.enigma.be.astron.assist.CombinedFlags;
import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.xchg.domain.SeFlags;

import java.util.ArrayList;
import java.util.List;

/**
 * A 'full' chart with information on all positions
 */
public class FullChart {

   private final SimpleDateTime simpleDateTime;
   private final Location location;
   private final CalculationSettings settings;
   private final SeFrontend seFrontend;
   private long flagsValue;
   private List<SeFlags> allFlags;
   private MundaneValues mundaneValues;
   private List<CelObjectPosition> bodies;
   private double obliquity;

   public FullChart(final SimpleDateTime simpleDateTime, final Location location, final CalculationSettings settings) {
      this.simpleDateTime = simpleDateTime;
      this.location = location;
      this.settings = settings;
      seFrontend = SeFrontend.getFrontend();
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
      mundaneValues = new MundaneValues(seFrontend, simpleDateTime.getJdUt(), (int) flagsValue, location, settings.getHouseSystem());
   }

   private void calculateBodies() {
      bodies = new ArrayList<>();
      for (int i = 0; i < settings.getCelBodies().size(); i++) {
         bodies.add(new CelObjectPosition(seFrontend, simpleDateTime.getJdUt(), settings.getCelBodies().get(i), location, allFlags));
      }
   }

   private void calculateObliquity() {
      obliquity = new Obliquity(seFrontend, simpleDateTime.getJdUt()).getTrueObliquity();
   }

   public MundaneValues getMundaneValues() {
      return mundaneValues;
   }

   public List<CelObjectPosition> getBodies() {
      return bodies;
   }

   public SimpleDateTime getSimpleDateTime() {
      return simpleDateTime;
   }

   public Location getLocation() {
      return location;
   }

   public CalculationSettings getSettings() {
      return settings;
   }

   public double getObliquity() {
      return obliquity;
   }
}
