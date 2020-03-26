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
      val angleMc = cfChart.getHouseValues().getAscendant().getLongitude()
            - cfChart.getHouseValues().getMc().getLongitude();
      val cornerLines = new CornerLines(metrics);
      double[] coordinates = cornerLines.defineCoordinates(angleMc);
      gc.strokeLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);         // asc
      gc.strokeLine(coordinates[4], coordinates[5], coordinates[6], coordinates[7]);         // desc
      gc.strokeLine(coordinates[8], coordinates[9], coordinates[10], coordinates[11]);       // mc
      gc.strokeLine(coordinates[12], coordinates[13], coordinates[14], coordinates[15]);     // ic
   }

   private void drawHouses() {
      prepareSmallLines();
      val quadrantSystem = cfChart.getSettings().getHouseSystem().isQuadrantSystem();
      double angle;
      double[] positions;
      val asc = cfChart.getHouseValues().getAscendant().getLongitude();
      val cusps = cfChart.getHouseValues().getCusps();
      val cuspLine = new CuspLine(metrics);
      for (int i = 1; i <= 12; i++) {
         if (!quadrantSystem || (i != 1 && i != 4 && i != 7 && i != 10)) {
            val longitude = cusps.get(i).getLongitude();
            angle = asc - longitude;
            positions = cuspLine.defineCoordinates(angle);
            gc.strokeLine(positions[0], positions[1], positions[2], positions[3]);
         }
      }
   }

   private void drawCornerPositions() {
      preparePositonTexts();
      val angleMc = cfChart.getHouseValues().getAscendant().getLongitude() - cfChart.getHouseValues().getMc().getLongitude();
      val cornerPositions = new CornerPositions(metrics);
      double[] coordinates = cornerPositions.defineCoordinates(angleMc);
      String posText = new SexagesimalFormatter(2).formatDm(cfChart.getHouseValues().getAscendant().getLongitude() % 30.0);
      gc.fillText(posText, coordinates[0], coordinates[1]);
      gc.fillText(posText, coordinates[2], coordinates[3]);
      posText = new SexagesimalFormatter(2).formatDm(cfChart.getHouseValues().getMc().getLongitude() % 30.0);
      gc.fillText(posText, coordinates[4], coordinates[5]);
      gc.fillText(posText, coordinates[6], coordinates[7]);
   }

   private void drawCuspPositions() {
      preparePositonTexts();
      val quadrantSystem = cfChart.getSettings().getHouseSystem().isQuadrantSystem();
      val asc = cfChart.getHouseValues().getAscendant().getLongitude();
      val cusps = cfChart.getHouseValues().getCusps();
      val cuspPosition = new CuspPosition(metrics);
      double[] coordinates;
      for (int i = 1; i <= 12; i++) {
         if (!quadrantSystem || (i != 1 && i != 4 && i != 7 && i != 10)) {
            coordinates = cuspPosition.defineCoordinates(new Range(0.0, 360.0).checkValue(asc - cusps.get(i).getLongitude()));
            val posText = new SexagesimalFormatter(2).formatDm(cusps.get(i).getLongitude() % 30.0);
            gc.fillText(posText, coordinates[0], coordinates[1]);
         }
      }
   }

   private void drawSignGlyphs() {
      prepareGlyphs();
      double angle = 165.0 + offsetAsc % 30;      // 180 degrees (correct quadrant) minus 15 (glyph in center of sign).
      for (int i = 1; i <= 12; i++) {
         Point point = new RectTriangle(metrics.getDiameterSignGlyphsCircle(), angle).getPointAtEndOfHyp();
         int signIndex = (int) (cfChart.getHouseValues().getAscendant().getLongitude() / 30) + i;
         if (signIndex > 12) signIndex -= 12;
         gc.fillText(new GlyphForSign(signIndex).getGlyph(), point.getXPos() + corrForXY - metrics.getOffSetGlyphs(), point.getYPos() + corrForXY + metrics.getOffSetGlyphs());
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
      val celObject = new CelObject(metrics);
      for (PlotBodyInfo bodyInfo : plotBodyInfos) {
         gc.setFont(new Font(GLYPH_FONTNAME, metrics.getSizeGlyphFont()));  // TODO add to prepare function
         val bodyIndex = bodyInfo.getCelObject().getId();
         val coordinates = celObject.defineCoordinates(bodyInfo.getCorrectedAngle());
         gc.fillText(new GlyphForCelObject(bodyIndex).getGlyph(), coordinates[0], coordinates[1]);
         drawConnectLines(bodyInfo);
         drawCelObjectPosition(bodyInfo);
      }
   }

   private void drawConnectLines(@NonNull final PlotBodyInfo plotBodyInfo) {
      prepareConnectLines();
      val hypothenusa1 = metrics.getDiameterCelBodiesMedium() - metrics.getDistanceConnectLines();
      val hypothenusa2 = metrics.getSizeHousesCircle() / 2;
      val hypothenusa3 = metrics.getDiameterCelBodiesMedium() + metrics.getDistanceConnectLines();
      val hypothenusa4 = metrics.getSizeSignsCircle() / 2;
      var startPoint = new RectTriangle(hypothenusa1, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      var endPoint = new RectTriangle(hypothenusa2, plotBodyInfo.getAngleFromAsc() + 180.0).getPointAtEndOfHyp();
      gc.strokeLine(startPoint.getXPos() + corrForXY, startPoint.getYPos() + corrForXY,
            endPoint.getXPos() + corrForXY, endPoint.getYPos() + corrForXY);
      startPoint = new RectTriangle(hypothenusa3, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      endPoint = new RectTriangle(hypothenusa4, plotBodyInfo.getAngleFromAsc() + 180.0).getPointAtEndOfHyp();
      gc.strokeLine(startPoint.getXPos() + corrForXY, startPoint.getYPos() + corrForXY,
            endPoint.getXPos() + corrForXY, endPoint.getYPos() + corrForXY);
      gc.setStroke(Color.BLACK);
      gc.setFill(Color.BLACK);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.6);
   }

   private void drawCelObjectPosition(@NonNull final PlotBodyInfo plotBodyInfo) {
      gc.setFont(new Font(TEXT_FONTNAME, metrics.getSizeTextFont()));
      double[] coordinates = new CelObjectPlotPosition(metrics).defineCoordinates(plotBodyInfo.getCorrectedAngle());
      gc.fillText(plotBodyInfo.getPosText(), coordinates[0], coordinates[1]);
   }

}
