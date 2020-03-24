/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens.helpers;

import com.radixpro.enigma.be.astron.main.CelObjectPosition;
import com.radixpro.enigma.shared.Range;
import com.radixpro.enigma.ui.shared.formatters.SexagesimalFormatter;
import com.radixpro.enigma.xchg.api.CalculatedFullChart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * Image of a radix wheel.
 */
public class RadixWheel {

   private static final Color FRAME_COLOR = Color.BLUE;
   private static final Color POSITION_COLOR = Color.GRAY;
   private static final Color GLYPH_COLOR = Color.BLACK;
   private static final double FRAME_GLOBAL_ALPHA = 0.5d;
   private static final String GLYPH_FONTNAME = "EnigmaAstrology";
   private static final String TEXT_FONTNAME = "Arial";
   private final GraphicsContext gc;
   private final ChartDrawMetrics metrics;
   private final CalculatedFullChart cfChart;
   private double offsetAsc;
   private double corrForXY;

   /**
    * Constructing this class automatically draws a radix wheel, works like a SVG image.
    *
    * @param gc                  The GraphicsContext
    * @param metrics             Dynamic metrics, will be resized if required.
    * @param calculatedFullChart Data for the calcualted chart.
    */
   public RadixWheel(@NonNull final GraphicsContext gc, @NonNull final ChartDrawMetrics metrics,
                     @NonNull final CalculatedFullChart calculatedFullChart) {
      this.gc = gc;
      this.metrics = metrics;
      this.cfChart = calculatedFullChart;
      defineGlobals();
      performDraw();
   }

   private void defineGlobals() {
      corrForXY = metrics.getOffsetOuterCircle() + metrics.getSizeOuterCircle() / 2;
      offsetAsc = cfChart.getHouseValues().getAscendant().getLongitude() % 30;
   }

   private void prepareCircles() {
      gc.setLineWidth(metrics.getWidthMediumLines());
      gc.setStroke(FRAME_COLOR);
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
   }

   private void prepareSmallLines() {
      gc.setLineWidth(metrics.getWidthThinLines());
      gc.setStroke(FRAME_COLOR);
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
   }

   private void prepareMediumLines() {
      gc.setLineWidth(metrics.getWidthMediumLines());
      gc.setStroke(FRAME_COLOR);
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
   }

   private void prepareThickLines() {
      gc.setLineWidth(metrics.getWidthThickLines());
      gc.setStroke(FRAME_COLOR);
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
   }

   private void prepareConnectLines() {
      gc.setStroke(Color.GREEN);
      gc.setFill(Color.GREEN);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.3);
   }

   private void preparePositonTexts() {
      gc.setLineWidth(metrics.getWidthThinLines());
      gc.setStroke(POSITION_COLOR);
      gc.setFill(POSITION_COLOR);
      gc.setFont(new Font(TEXT_FONTNAME, metrics.getSizeTextFont()));
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
   }

   private void prepareGlyphs() {
      gc.setStroke(GLYPH_COLOR);
      gc.setFill(GLYPH_COLOR);
      gc.setFont(new Font(GLYPH_FONTNAME, metrics.getSizeGlyphFont()));
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
   }

   private void prepareCelObjects() {
      gc.setStroke(GLYPH_COLOR);
      gc.setFill(GLYPH_COLOR);
      gc.setLineWidth(metrics.getWidthThinLines());
      gc.setGlobalAlpha(FRAME_GLOBAL_ALPHA);
      gc.setFont(new Font(GLYPH_FONTNAME, metrics.getSizeGlyphFont()));
   }

   private void performDraw() {
      drawCircles();
      drawSignSeparators();
      drawDegreeLines();
      drawCorners();
      drawHouses();
      drawCornerPositions();
      drawCuspPositions();
      drawSignGlyphs();
      drawCelObjects();
   }

   private void drawCircles() {
      prepareCircles();
      drawSpecificCircle(metrics.getOffsetOuterCircle(), metrics.getSizeOuterCircle());
      drawSpecificCircle(metrics.getOffsetSignsCircle(), metrics.getSizeSignsCircle());
      drawSpecificCircle(metrics.getOffsetHousesCircle(), metrics.getSizeHousesCircle());
   }

   private void drawSpecificCircle(final double offset, final double size) {
      gc.strokeOval(offset, offset, size, size);
   }

   private void drawSignSeparators() {
      prepareMediumLines();
      final SignSeparator separator = new SignSeparator(metrics);
      var angle = 30 - (offsetAsc % 30);
      double[] positions;
      for (int i = 1; i <= 12; i++) {
         positions = separator.defineCoordinates(angle);
         gc.strokeLine(positions[0], positions[1], positions[2], positions[3]);
         angle += 30.0;
      }
   }

   private void drawDegreeLines() {
      prepareSmallLines();
      double angle = 30 - offsetAsc % 30;
      final DegreeLine degreeLine = new DegreeLine(metrics);
      double[] positions;
      for (int i = 0; i <= 359; i++) {
         positions = degreeLine.defineCoordinates(i, angle);
         gc.strokeLine(positions[0], positions[1], positions[2], positions[3]);
         angle += 1.0;
      }
   }

   private void drawCorners() {
      prepareThickLines();
      // Ascendant and descendant
      val distanceFromCenter = metrics.getDiameterOuterCircle() + metrics.getOffsetOuterCircle();
      var xPos1 = 0.0;
      var xPos2 = distanceFromCenter - metrics.getDiameterHousesCircle() - (metrics.getWidthThickLines() / 2);
      gc.strokeLine(xPos1, distanceFromCenter, xPos2, distanceFromCenter);  // asc line
      xPos1 = distanceFromCenter + metrics.getDiameterHousesCircle() + (metrics.getWidthThickLines() / 2);
      xPos2 = distanceFromCenter * 2.0;
      gc.strokeLine(xPos1, distanceFromCenter, xPos2, distanceFromCenter);  // desc line
      // MC and IC
      val angleMc = new Range(0.0, 360.0).checkValue(cfChart.getHouseValues().getAscendant().getLongitude()
            - cfChart.getHouseValues().getMc().getLongitude());
      val hypothenusaLarge = metrics.getDiameterOuterCircle() + metrics.getOffsetOuterCircle();
      val hypothenusaSmall = metrics.getDiameterHousesCircle() + (metrics.getWidthThickLines() / 2);
      var point1 = new RectTriangle(hypothenusaSmall, angleMc).getPointAtEndOfHyp();
      var point2 = new RectTriangle(hypothenusaLarge, angleMc).getPointAtEndOfHyp();
      drawLine(point1, point2);     // MC
      point1 = new RectTriangle(hypothenusaSmall, angleMc + 180.0).getPointAtEndOfHyp();
      point2 = new RectTriangle(hypothenusaLarge, angleMc + 180.0).getPointAtEndOfHyp();
      drawLine(point1, point2);    // IC
   }

   private void drawHouses() {
      prepareSmallLines();
      val quadrantSystem = cfChart.getSettings().getHouseSystem().isQuadrantSystem();
      double angle;
      val asc = cfChart.getHouseValues().getAscendant().getLongitude();
      val cusps = cfChart.getHouseValues().getCusps();
      for (int i = 1; i <= 12; i++) {
         if (!quadrantSystem || (i != 1 && i != 4 && i != 7 && i != 10)) {
            val longitude = cusps.get(i).getLongitude();
            angle = asc - longitude;
            val point1 = new RectTriangle(metrics.getDiameterHousesCircle(), angle).getPointAtEndOfHyp();
            val point2 = new RectTriangle(metrics.getDiameterSignsCircle(), angle).getPointAtEndOfHyp();
            drawLine(point1, point2);
         }
      }
   }

   private void drawCornerPositions() {
      preparePositonTexts();
      // Ascendant and descendant
      var xPos = metrics.getOffsetOuterCircle() / 2.0;
      val yPos = metrics.getDiameterOuterCircle() + metrics.getOffsetOuterCircle() - metrics.getWidthThickLines();
      String posText = new SexagesimalFormatter(2).formatDm(cfChart.getHouseValues().getAscendant().getLongitude() % 30.0);
      gc.fillText(posText, xPos, yPos);
      xPos = metrics.getDiameterOuterCircle() * 2.0 + metrics.getOffsetOuterCircle() * 1.25;
      gc.fillText(posText, xPos, yPos);
      // MC and IC
      val angleMc = new Range(0.0, 360.0).checkValue(cfChart.getHouseValues().getAscendant().getLongitude()
            - cfChart.getHouseValues().getMc().getLongitude());
      val hypothenusa = metrics.getDiameterOuterCircle() + metrics.getOffsetOuterCircle() / 2.0;
      var point = new RectTriangle(hypothenusa, angleMc).getPointAtEndOfHyp();
      posText = new SexagesimalFormatter(2).formatDm(cfChart.getHouseValues().getMc().getLongitude() % 30.0);
      writeText(posText, point, 0.0, 0.0);
      point = new RectTriangle(hypothenusa, angleMc + 180.0).getPointAtEndOfHyp();
      writeText(posText, point, 0.0, 0.0);
   }

   private void drawCuspPositions() {
      preparePositonTexts();
      val quadrantSystem = cfChart.getSettings().getHouseSystem().isQuadrantSystem();
      val asc = cfChart.getHouseValues().getAscendant().getLongitude();
      val cusps = cfChart.getHouseValues().getCusps();
      double hypothenusa;
      for (int i = 1; i <= 12; i++) {
         if (!quadrantSystem || (i != 1 && i != 4 && i != 7 && i != 10)) {
            val quadrant = defineQuadrant(asc - cusps.get(i).getLongitude());
            switch (quadrant) {
               case 1:
                  hypothenusa = metrics.getDiameterCuspTextsLeft();
                  break;
               case 2:
                  hypothenusa = metrics.getDiameterCuspTextsTop();
                  break;
               case 3:
                  hypothenusa = metrics.getDiameterCuspTextsRight();
                  break;
               case 4:
                  hypothenusa = metrics.getDiameterCuspTextsBottom();
                  break;
               default:
                  hypothenusa = 0.0;
            }
            val startPoint = new RectTriangle(hypothenusa, asc - cusps.get(i).getLongitude() + 180.0).getPointAtEndOfHyp();
            val posText = new SexagesimalFormatter(2).formatDm(cusps.get(i).getLongitude() % 30.0);
            writeText(posText, startPoint, 0.0, 0.0);
         }
      }
   }

   private void drawSignGlyphs() {
      prepareGlyphs();
      double angle = 165.0 + offsetAsc % 30;      // 180 degrees (correct quadrant) minus 15 (glyph in center of sign).
      for (int i = 1; i <= 12; i++) {
         Point startPoint = new RectTriangle(metrics.getDiameterSignGlyphsCircle(), angle).getPointAtEndOfHyp();
         int signIndex = (int) (cfChart.getHouseValues().getAscendant().getLongitude() / 30) + i;
         if (signIndex > 12) signIndex -= 12;
         writeText(new GlyphForSign(signIndex).getGlyph(), startPoint, -metrics.getOffSetGlyphs(), metrics.getOffSetGlyphs());
         angle -= 30.0;
         if (angle < 0.0) angle += 360.0;
      }
   }

   private void drawCelObjects() {
      prepareCelObjects();
      val bodies = cfChart.getBodies();
      val ascendant = cfChart.getHouseValues().getAscendant().getLongitude();
      double longitude;
      double angle;
      double angle1;
      double angle2;
      double minDist = metrics.getMinAngleObjects();
      double distance;
      final List<PlotBodyInfo> plotBodyInfos = new ArrayList<>();
      for (CelObjectPosition bodyPos : bodies) {
         longitude = bodyPos.getEclipticalPosition().getMainPosition();
         angle = new Range(0.0, 360.0).checkValue(ascendant - longitude);
         plotBodyInfos.add(new PlotBodyInfo(bodyPos.getCelestialBody(), angle, longitude));
      }
      plotBodyInfos.sort(new PlotBodyInfoComparator());
      int maxIndex = plotBodyInfos.size() - 1;
      for (int i = 0; i < plotBodyInfos.size(); i++) {
         if (i > 0) {
            angle1 = plotBodyInfos.get(i).getCorrectedAngle();
            angle2 = plotBodyInfos.get(i - 1).getCorrectedAngle();
            distance = angle1 - angle2;
         } else {
            angle1 = plotBodyInfos.get(i).getCorrectedAngle();
            angle2 = plotBodyInfos.get(maxIndex).getCorrectedAngle();
            distance = angle1 - angle2 + 360.0;
         }
         if (distance < minDist) {
            plotBodyInfos.get(i).setCorrectedAngle(angle2 + minDist);
         }
      }
      val hypothenusa = metrics.getDiameterCelBodiesMedium() / 2;
      for (PlotBodyInfo bodyInfo : plotBodyInfos) {
         gc.setFont(new Font(GLYPH_FONTNAME, metrics.getSizeGlyphFont()));  // TODO add to prepare function
         val startPoint = new RectTriangle(hypothenusa, bodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
         val bodyIndex = bodyInfo.getCelObject().getId();
         writeText(new GlyphForCelObject(bodyIndex).getGlyph(), startPoint, -metrics.getOffSetGlyphs(), metrics.getOffSetGlyphs());
         drawConnectLines(bodyInfo);
         drawPosition(bodyInfo);
      }
   }


   private void drawConnectLines(@NonNull final PlotBodyInfo plotBodyInfo) {
      prepareConnectLines();
      val hypothenusa1 = metrics.getDiameterCelBodiesMedium() / 2 - metrics.getDistanceConnectLines();
      val hypothenusa2 = metrics.getSizeHousesCircle() / 2;
      val hypothenusa3 = metrics.getDiameterCelBodiesMedium() / 2 + metrics.getDistanceConnectLines();
      val hypothenusa4 = metrics.getSizeSignsCircle() / 2;
      var startPoint = new RectTriangle(hypothenusa1, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      var endPoint = new RectTriangle(hypothenusa2, plotBodyInfo.getAngleFromAsc() + 180.0).getPointAtEndOfHyp();
      drawLine(startPoint, endPoint);
      startPoint = new RectTriangle(hypothenusa3, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      endPoint = new RectTriangle(hypothenusa4, plotBodyInfo.getAngleFromAsc() + 180.0).getPointAtEndOfHyp();
      drawLine(startPoint, endPoint);
      gc.setStroke(Color.BLACK);
      gc.setFill(Color.BLACK);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.6);
   }

   private void drawPosition(@NonNull final PlotBodyInfo plotBodyInfo) {
      double hypothenusa;
      int quadrant = defineQuadrant(plotBodyInfo.getAngleFromAsc());
      switch (quadrant) {
         case 1:
            hypothenusa = metrics.getDiameterPosTextsLeft();
            break;
         case 2:
            hypothenusa = metrics.getDiameterPosTextsTop();
            break;
         case 3:
            hypothenusa = metrics.getDiameterPosTextsRight();
            break;
         case 4:
            hypothenusa = metrics.getDiameterPosTextsBottom();
            break;
         default:
            hypothenusa = 0.0;
      }
      Point centerPoint = new RectTriangle(hypothenusa, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      gc.setFont(new Font(TEXT_FONTNAME, metrics.getSizeTextFont()));
      gc.fillText(plotBodyInfo.getPosText(), centerPoint.getXPos() + corrForXY, centerPoint.getYPos() + corrForXY);
      writeText(plotBodyInfo.getPosText(), centerPoint, 0.0, 0.0);
   }

   // quadrants 0f 90 degrees each: 1 with the asc as center, 3 with the desc as center, 2 top, between 1 and 3,
   // 4 bottom, between 2 and 4.
   private int defineQuadrant(final double degreesFromAsc) {
      val angleForQuadrant = new Range(0, 360).checkValue(degreesFromAsc);
      var quadrant = 0;
      if (0.0 <= angleForQuadrant && angleForQuadrant < 45.0) quadrant = 1;
      else if (45.0 <= angleForQuadrant && angleForQuadrant < 135.0) quadrant = 2;
      else if (135.0 <= angleForQuadrant && angleForQuadrant < 225.0) quadrant = 3;
      else if (225.0 <= angleForQuadrant && angleForQuadrant < 315.0) quadrant = 4;
      else if (315.0 <= angleForQuadrant && angleForQuadrant < 360.0) quadrant = 1;
      return quadrant;
   }

   private void drawLine(@NonNull final Point point1, @NonNull final Point point2) {
      gc.strokeLine(point1.getXPos() + corrForXY, point1.getYPos() + corrForXY,
            point2.getXPos() + corrForXY, point2.getYPos() + corrForXY);
   }

   private void writeText(@NonNull final String text, @NonNull final Point point, final double xOffset, final double yOffset) {
      gc.fillText(text, point.getXPos() + corrForXY + xOffset, point.getYPos() + corrForXY + yOffset);
   }

}
