/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.NonNull;
import lombok.val;

/**
 * Factory for implementations of PlotCoordinates.
 */
public class PlotCoordinatesFactory {

   /**
    * Create instance of CuspLinePlotCoordinates.
    *
    * @param angle       The angle to be used for calculating the coordinates.
    * @param drawMetrics . An acutal instance of ChartDrawMetrics.
    * @return instance of CuspLinePlotCoordinates.
    */
   public static CuspLinePlotCoordinates createCuspLinePlotCoordinates(final double angle,
                                                                       @NonNull final DrawMetrics drawMetrics) {
      val metrics = (ChartDrawMetrics) drawMetrics;
      val rectTriangle = new RectTriangleAbsolute(angle, metrics.getCorrForXY());
      return new CuspLinePlotCoordinates(rectTriangle);
   }

   /**
    * Create instance of CuspTextCoordinates.
    *
    * @param angle       The angle to be used for calculating the coordinates.
    * @param drawMetrics . An acutal instance of ChartDrawMetrics.
    * @return instance of CuspTextPlotCoordinates.
    */
   public static CuspTextPlotCoordinates createCuspTextPlotCoordinates(final double angle,
                                                                       @NonNull final DrawMetrics drawMetrics) {
      val metrics = (ChartDrawMetrics) drawMetrics;
      val rectTriangle = new RectTriangleAbsolute(angle, metrics.getCorrForXY());
      return new CuspTextPlotCoordinates(rectTriangle);
   }
}
