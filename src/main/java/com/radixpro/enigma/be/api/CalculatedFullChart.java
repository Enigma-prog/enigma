/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.be.astron.assist.CalculationSettings;
import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;
import com.radixpro.enigma.be.astron.main.CelBodyPosition;
import com.radixpro.enigma.be.astron.main.FullChart;
import com.radixpro.enigma.be.astron.main.MundaneValues;

import java.util.List;

/**
 * API for a calculated full chart. Contains all positions and additional astronomical info.
 */
public class CalculatedFullChart {

   private final FullChart fullchart;

   public CalculatedFullChart(final SimpleDateTime simpleDateTime, final Location location, final CalculationSettings settings) {
      fullchart = new FullChart(simpleDateTime, location, settings);
   }

   public List<CelBodyPosition> getBodies() {
      return fullchart.getBodies();
   }

   public MundaneValues getHouseValues() {
      return fullchart.getMundaneValues();
   }

   public CalculationSettings getSettings() {
      return fullchart.getSettings();
   }

   public SimpleDateTime getDateTime() {
      return fullchart.getSimpleDateTime();
   }

   public Location getLocation() {
      return fullchart.getLocation();
   }

   public double getObliquity() {
      return fullchart.getObliquity();
   }

}
