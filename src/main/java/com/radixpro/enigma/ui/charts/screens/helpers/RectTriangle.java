/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.Getter;
import lombok.val;

/**
 * Defines a rectangular triangle and some basic math to calculate the not yet defined angle.
 */
public class RectTriangle {
   @Getter
   private final Point pointAtEndOfHyp;
   private final int hypothenusa;
   private final double angle;

   /**
    * Constructor reads data and performs calculation. The resulting point is relative to the center of the circle.
    *
    * @param hypothenusa The hypothenusa of the rectangular triangle.
    * @param angle       The angle  as seen frok the center.
    */
   public RectTriangle(final int hypothenusa, final double angle) {
      this.hypothenusa = hypothenusa;
      this.angle = angle;
      pointAtEndOfHyp = definePoint();
   }

   private Point definePoint() {
      val ySize = (int) Math.round(Math.sin(Math.toRadians(angle)) * hypothenusa);
      val xSize = (int) Math.round(Math.cos(Math.toRadians(angle)) * hypothenusa);
      return new Point(xSize, ySize);
   }


}

