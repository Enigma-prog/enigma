/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.NonNull;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Calculates the position for a degree line.
 */
public class DegreeLine {

   final ChartDrawMetrics metrics;

   public DegreeLine(@NonNull final ChartDrawMetrics metrics) {
      this.metrics = metrics;
   }

   public double[] defineCoordinates(final int index, final double angle) {
      final RectTriangleAbsolute rectTriangle = new RectTriangleAbsolute(angle, metrics.getCorrForXY());
      double[] coords1;
      if (index % 5 == 0) coords1 = rectTriangle.getCoordinates(metrics.getDiameterDegrees5Circle());
      else coords1 = rectTriangle.getCoordinates(metrics.getDiameterDegreesCircle());
      double[] coords2 = rectTriangle.getCoordinates(metrics.getDiameterSignsCircle());
      return ArrayUtils.addAll(coords1, coords2);
   }
}
