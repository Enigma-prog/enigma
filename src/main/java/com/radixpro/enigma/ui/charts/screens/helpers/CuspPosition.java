/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.NonNull;

/**
 * Calculates the position for a cusp text.
 */
public class CuspPosition {

   private final ChartDrawMetrics metrics;

   public CuspPosition(@NonNull final ChartDrawMetrics metrics) {
      this.metrics = metrics;
   }

   public double[] defineCoordinates(final double angle) {
      double hypothenusa = 0.0;
      if (0.0 <= angle && angle < 45.0) hypothenusa = metrics.getDiameterCuspTextsLeft();
      else if (45.0 <= angle && angle < 135.0) hypothenusa = metrics.getDiameterCuspTextsTop();
      else if (135.0 <= angle && angle < 225.0) hypothenusa = metrics.getDiameterCuspTextsRight();
      else if (225.0 <= angle && angle < 315.0) hypothenusa = metrics.getDiameterCuspTextsBottom();
      else if (315.0 <= angle && angle < 360.0) hypothenusa = metrics.getDiameterCuspTextsLeft();
      return new RectTriangleAbsolute(angle + 180.0, metrics.getCorrForXY()).getCoordinates(hypothenusa);
   }


}

