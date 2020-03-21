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
   private double offsetOuterCircle;      // distance on x- and y-axis for drawing outer circle
   private double sizeOuterCircle;        // size of circle outside of ecliptic signs
   private double offsetSignsCircle;      // distance on x- and y-axis for drawing signs circle
   private double sizeSignsCircle;        // size of circle between houses and signs
   private double offsetHousesCircle;     // distance on x- and y-axis for drawing houses circle
   private double sizeHousesCircle;       // size of circle between aspect-space and houses
   private double sizeDegreesCircle;      // size of circle within degree-lines
   private double sizeDegrees5Circle;     // size of circle within degree-lines for degrees that are amultiple of 5
   private double sizeSignGlyphsCircle;   // size of circle for placing sygn glyphs
   private double sizeGlyphFont;          // size for glyphs, measured in points, both for signs and celestial bodies
   private double sizeTextFont;           // size for text, measured in points
   private double sizeThickLines;         // size for thick lines (asc, mc, ic, desc).
   private double sizeMediumLines;        // size for medium lines (separators between signs)
   private double sizeThinLines;          // size for thin lines (cusps)
   private double offSetGlyphs;           // correction to make sure the x-y-coordinates are at the center of the glyph
   private double diameterCelBodiesMedium;// radius for celestial Bodies (default)
   private double diameterCelBodiesLarge; // radius for celestial Bodies (more distant if there is lack of space)
   private double diameterCelBodiesSmall; // radius for celestial Bodies (if it is crowded)
   private double minAngleObjects;        // the minimum angle between celestial objects (to prevent overlap)
   private double distanceConnectLines;   // the distance between the plotposition for the glyph and the connect line
   private double diameterPosTextsLeft;   // distance for position texts in the left part of the chart
   private double diameterPosTextsRight;  // distance for position texts in the right part of the chart
   private double diameterPosTextsTop;    // distance for position texts in the top part of the chart
   private double diameterPosTextsBottom; // distance for position texts in hte bottom part of the chart
   private double diameterCuspTextsLeft;  // distance for position texts in the left part of the chart
   private double diameterCuspTextsRight; // distance for position texts in the right part of the chart
   private double diameterCuspTextsTop;   // distance for position texts in the top part of the chart
   private double diameterCuspTextsBottom;// distance for position texts in hte bottom part of the chart


   /**
    * Constructor initially sets the base-dimension to 700, this is the width and height of the canvas.
    */
   public ChartDrawMetrics() {
      setCanvasDimension(BASE_DIMENSION);
   }

   /**
    * Change base-dimension. This method should be called after resizing by the user.
    *
    * @param newDimension new size of the canvasm it should the minimum of the height and width.
    */
   public void setCanvasDimension(final double newDimension) {
      this.canvasDimension = newDimension;
      defineValues();
   }

   private void defineValues() {
      offsetOuterCircle = canvasDimension * 0.1;
      sizeOuterCircle = canvasDimension * 0.8;
      sizeSignsCircle = canvasDimension * 0.7;
      offsetSignsCircle = offsetOuterCircle + ((sizeOuterCircle - sizeSignsCircle) / 2);
      sizeHousesCircle = canvasDimension * 0.4;
      offsetHousesCircle = offsetOuterCircle + ((sizeOuterCircle - sizeHousesCircle) / 2);
      sizeDegreesCircle = sizeSignsCircle * 0.99;
      sizeDegrees5Circle = sizeSignsCircle * 0.98;
      sizeSignGlyphsCircle = canvasDimension * 0.75;
      sizeGlyphFont = canvasDimension * 0.035;
      sizeTextFont = canvasDimension * 0.013;
      sizeThickLines = canvasDimension * 0.0075;
      sizeMediumLines = canvasDimension * 0.00375;
      sizeThinLines = canvasDimension * 0.00173;
      offSetGlyphs = sizeGlyphFont / 3.0;
      diameterCelBodiesSmall = canvasDimension * 0.45;
      diameterCelBodiesMedium = canvasDimension * 0.5;
      diameterPosTextsLeft = canvasDimension * 0.3;
      diameterPosTextsRight = canvasDimension * 0.275;
      diameterPosTextsTop = canvasDimension * 0.275;
      diameterPosTextsBottom = canvasDimension * 0.285;

      diameterCuspTextsLeft = canvasDimension * 0.34;
      diameterCuspTextsRight = canvasDimension * 0.31;
      diameterCuspTextsTop = canvasDimension * 0.33;
      diameterCuspTextsBottom = canvasDimension * 0.33;

      diameterCelBodiesLarge = canvasDimension * 0.55;
      minAngleObjects = sizeGlyphFont / 4.2;  // value is in degrees
      distanceConnectLines = canvasDimension * 0.02;

   }

}
