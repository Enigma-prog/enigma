/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.val;
import org.apache.commons.lang3.ArrayUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Calculates the coordinates to draw a cusp line.
 */
public class CuspLinePlotCoordinates implements PlotCoordinates {

   final RectTriangleAbsolute rectTriangle;

   /**
    * Constructor should be used via factory.
    *
    * @param rectTriangle An already instantiated RectTriangleAbsolute.
    * @see com.radixpro.enigma.ui.shared.factories.PlotCoordinatesFactory
    */
   public CuspLinePlotCoordinates(RectTriangleAbsolute rectTriangle) {
      this.rectTriangle = checkNotNull(rectTriangle);
   }

   /**
    * Define the coordinates for the given angle.
    * The class knows the size of the hypothenusa.
    *
    * @param angle       Angle in degrees.
    * @param drawMetrics Actual instance of ChartDrawMetrics.
    * @return The calculated coordinates.
    */
   @Override
   public double[] defineCoordinates(final double angle, final DrawMetrics drawMetrics) {
      checkNotNull(drawMetrics);
      val metrics = (ChartDrawMetrics) drawMetrics;
      val coords1 = rectTriangle.getCoordinates(metrics.getDiameterHousesCircle());
      val coords2 = rectTriangle.getCoordinates(metrics.getDiameterSignsCircle());
      return ArrayUtils.addAll(coords1, coords2);
   }

}
