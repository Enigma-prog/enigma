/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.main;

import com.radixpro.enigma.be.astron.assist.*;
import com.radixpro.enigma.be.astron.core.SeFrontend;

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
   private HouseValues houseValues;
   private List<CelBody> bodies;

   public FullChart(final SimpleDateTime simpleDateTime, final Location location, final CalculationSettings settings) {
      this.simpleDateTime = simpleDateTime;
      this.location = location;
      this.settings = settings;
      seFrontend = SeFrontend.getFrontend();
      calculateFlags();
      calculateHouses();
      calculateBodies();
   }

   private void calculateFlags() {
      allFlags = new ArrayList<>();
      allFlags.add(SeFlags.SWISSEPH);
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
      houseValues = new HouseValues(seFrontend, simpleDateTime.getJdUt(), (int) flagsValue, location, settings.getHouseSystem());
   }

   private void calculateBodies() {
      bodies = new ArrayList<>();
      for (int i = 0; i < settings.getCelBodies().size(); i++) {
         bodies.add(new CelBody(seFrontend, simpleDateTime.getJdUt(), settings.getCelBodies().get(i), location, allFlags));
      }
   }

   public HouseValues getHouseValues() {
      return houseValues;
   }

   public List<CelBody> getBodies() {
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
}
