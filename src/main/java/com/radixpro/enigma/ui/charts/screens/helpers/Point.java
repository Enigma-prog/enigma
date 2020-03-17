/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import lombok.Getter;

/**
 * Location of a point on the canvas.
 */
@Getter
public class Point {

   private final int xPos;
   private final int yPos;

   /**
    * Constructor defines both points.
    *
    * @param xPos the coordinate on the x-axis
    * @param yPos the coordinate on the y-axis
    */
   public Point(final int xPos, final int yPos) {
      this.xPos = xPos;
      this.yPos = yPos;
   }
}
