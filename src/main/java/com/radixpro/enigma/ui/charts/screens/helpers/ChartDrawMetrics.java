/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.Getter;

/**
 * Metrics for drawing a chart. Supports resizing.
 * This object is mutable as the value for canvasDimension can be set.
 */
@Getter
public class ChartDrawMetrics {

   private static final double BASE_DIMENSION = 700;
   private double canvasDimension;
   private double offsetOuterCircle;   // distance on x- and y-axis for drawing outer circle
   private double sizeOuterCircle;     // size of circle outside of ecliptic signs
   private double offsetSignsCircle;   // distance on x- and y-axis for drawing signs circle
   private double sizeSignsCircle;     // size of circle between houses and signs
   private double offsetHousesCircle;  // distance on x- and y-axis for drawing houses circle
   private double sizeHousesCircle;    // size of circle between aspect-space and houses

   public ChartDrawMetrics() {
      setCanvasDimension(BASE_DIMENSION);
   }


   public void setCanvasDimension(final double newDimension) {
      this.canvasDimension = newDimension;
      defineValues();
   }

   private void defineValues() {
      offsetOuterCircle = canvasDimension * 0.1;
      sizeOuterCircle = canvasDimension * 0.8;
      sizeSignsCircle = canvasDimension * 0.7;
      offsetSignsCircle = offsetOuterCircle + (sizeOuterCircle - sizeSignsCircle) / 2;
      sizeHousesCircle = canvasDimension * 0.4;
      offsetHousesCircle = offsetOuterCircle + (sizeOuterCircle - sizeHousesCircle) / 2;

   }

}
