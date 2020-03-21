/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.be.astron.assist.HousePosition;
import com.radixpro.enigma.be.astron.main.CelObjectPosition;
import com.radixpro.enigma.shared.Range;
import com.radixpro.enigma.ui.charts.screens.helpers.*;
import com.radixpro.enigma.ui.shared.formatters.SexagesimalFormatter;
import com.radixpro.enigma.xchg.api.CalculatedFullChart;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ChartsDrawing2d {

   private ChartDrawMetrics metrics;

   private Canvas canvas;
   private CalculatedFullChart fullChart;
   private GraphicsContext gc;
   private double offsetAsc;

   public void setFullChart(@NonNull final CalculatedFullChart fullChart) {
      this.fullChart = fullChart;
      final Stage stage = new Stage();
      drawChart(stage);
   }

   private void drawChart(@NonNull final Stage stage) {
      metrics = new ChartDrawMetrics();
      offsetAsc = fullChart.getHouseValues().getAscendant().getLongitude() % 30;

      canvas = new Canvas(metrics.getCanvasDimension(), metrics.getCanvasDimension());
      gc = canvas.getGraphicsContext2D();
      gc.setFont(new Font("Courier", 10));

      Pane chartPane = new Pane(canvas);
      Label lblName = new Label();
      lblName.setText("Horoscoop voor: ");
      Button btnOk = new Button("OK");
      btnOk.setDefaultButton(true);
      Button btnHelp = new Button("Help");

      GridPane gridPane = new GridPane();
      ColumnConstraints columnConstraints = new ColumnConstraints();
      columnConstraints.setPercentWidth(50.0);
      gridPane.getColumnConstraints().add(0, columnConstraints);
      gridPane.getColumnConstraints().add(1, columnConstraints);
      RowConstraints rowConstraintsTitle = new RowConstraints();
      rowConstraintsTitle.setMaxHeight(40.0);
      rowConstraintsTitle.setMinHeight(40.0);
      rowConstraintsTitle.setValignment(VPos.CENTER);
      RowConstraints rowConstraintsChart = new RowConstraints();
      rowConstraintsChart.setPrefHeight(840.0);
      RowConstraints rowConstraintsButtons = new RowConstraints();
      rowConstraintsButtons.setMaxHeight(40.0);
      rowConstraintsButtons.setMinHeight(40.0);
      gridPane.getRowConstraints().add(0, rowConstraintsTitle);
      gridPane.getRowConstraints().add(1, rowConstraintsChart);
      gridPane.getRowConstraints().add(2, rowConstraintsButtons);
      gridPane.setGridLinesVisible(false);

      gridPane.add(lblName, 0, 0, 2, 1);          // node col row colspan rowspan
      gridPane.add(chartPane, 0, 1, 2, 1);
      gridPane.add(btnHelp, 0, 2, 1, 1);
      gridPane.add(btnOk, 1, 2, 1, 1);

      stage.setMinHeight(400.0);
      stage.setMinWidth(320.0);
      stage.setScene(new Scene(gridPane, 700, 1000));  // pane, hor pos, vert pos
      stage.setTitle("Drawing of chart");
      stage.show();


      canvas.widthProperty().bind(chartPane.widthProperty());
      canvas.heightProperty().bind(chartPane.heightProperty());

      metrics.setCanvasDimension(Math.min(canvas.getWidth(), canvas.getHeight()));

      stageSizeChangeListener(stage);
      performDraw();
   }


   private void stageSizeChangeListener(Stage stage) {
      stage.widthProperty().addListener((observable, oldValue, newValue) -> {
         metrics.setCanvasDimension(Math.min(canvas.getWidth(), canvas.getHeight()));
         performDraw();
      });

      stage.heightProperty().addListener((observable, oldValue, newValue) -> {
         metrics.setCanvasDimension(Math.min(canvas.getWidth(), canvas.getHeight()));
         performDraw();
      });


   }

   private void performDraw() {
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      gc.setFill(Color.WHITE);
      gc.fill();
      gc.setStroke(Color.BLUE);
      gc.setLineWidth(metrics.getSizeMediumLines());
      gc.setGlobalAlpha(0.5d);
      drawCircle(metrics.getOffsetOuterCircle(), metrics.getSizeOuterCircle());
      drawCircle(metrics.getOffsetSignsCircle(), metrics.getSizeSignsCircle());
      drawCircle(metrics.getOffsetHousesCircle(), metrics.getSizeHousesCircle());
      drawSignSeparators();
      drawCorners();
      drawHouses();
      drawDegreeLines();
      drawSignGlyphs();
      drawPlanets();
   }

   private void drawCircle(final double offset, final double size) {
      gc.strokeOval(offset, offset, size, size);
   }

   private void drawSignSeparators() {
      val hypothenusaSmall = metrics.getSizeOuterCircle() / 2;
      val hypothenusaLarge = metrics.getSizeSignsCircle() / 2;
      double angle = 30 - offsetAsc % 30;
      val outerOffset = metrics.getOffsetOuterCircle();
      for (int i = 1; i <= 12; i++) {
         Point startPointFromCenter = new RectTriangle(hypothenusaSmall, angle).getPointAtEndOfHyp();
         Point endPointFromCenter = new RectTriangle(hypothenusaLarge, angle).getPointAtEndOfHyp();
         double corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
         final List<Double> values = convertCoordinateSet(startPointFromCenter, endPointFromCenter, corrForXY);
         gc.setLineWidth(2d);
         gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));
         angle += 30.0;
      }
   }

   private void drawCorners() {
      gc.setLineWidth(metrics.getSizeThickLines());

      double distanceFromCenter = metrics.getSizeOuterCircle() / 2 + metrics.getOffsetOuterCircle();
      double xPos1 = 0.0;
      double yPos1 = distanceFromCenter;
      double xPos2 = distanceFromCenter - (metrics.getSizeHousesCircle() / 2) - (metrics.getSizeThickLines() / 2);
      double yPos2 = distanceFromCenter;
      gc.strokeLine(xPos1, yPos1, xPos2, yPos2);  // asc line

      xPos1 = distanceFromCenter + (metrics.getSizeHousesCircle() / 2) + (metrics.getSizeThickLines() / 2);
      yPos1 = distanceFromCenter;
      xPos2 = distanceFromCenter * 2.0;
      yPos2 = distanceFromCenter;
      gc.strokeLine(xPos1, yPos1, xPos2, yPos2);  // desc line

      double angleMc = new Range(0.0, 360.0).checkValue(fullChart.getHouseValues().getAscendant().getLongitude()
            - fullChart.getHouseValues().getMc().getLongitude());

      double hypothenusaLarge = metrics.getSizeOuterCircle() / 2 + metrics.getOffsetOuterCircle();
      double hypothenusaSmall = (metrics.getSizeHousesCircle() / 2) + (metrics.getSizeThickLines() / 2);
      Point point1 = new RectTriangle(hypothenusaSmall, angleMc).getPointAtEndOfHyp();
      Point point2 = new RectTriangle(hypothenusaLarge, angleMc).getPointAtEndOfHyp();

      val outerOffset = metrics.getOffsetOuterCircle();
      double corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
      List<Double> values = convertCoordinateSet(point1, point2, corrForXY);
      gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));

      point1 = new RectTriangle(hypothenusaSmall, angleMc + 180.0).getPointAtEndOfHyp();
      point2 = new RectTriangle(hypothenusaLarge, angleMc + 180.0).getPointAtEndOfHyp();
      values = convertCoordinateSet(point1, point2, corrForXY);
      gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));
   }

   private void drawHouses() {
      gc.setLineWidth(metrics.getSizeThinLines());
      val houseSystem = fullChart.getSettings().getHouseSystem();
      val quadrant = houseSystem.isQuadrantSystem();
      double angle;
      double asc = fullChart.getHouseValues().getAscendant().getLongitude();
      val outerOffset = metrics.getOffsetOuterCircle();
      double corrForXY = outerOffset + Math.round(metrics.getSizeOuterCircle() / 2);
      final List<HousePosition> cusps = fullChart.getHouseValues().getCusps();
      double hypothenusaLarge = metrics.getSizeSignsCircle() / 2;
      double hypothenusaSmall = metrics.getSizeHousesCircle() / 2;
      List<Double> values;
      for (int i = 1; i <= 12; i++) {
         if (!quadrant || (i != 1 && i != 4 && i != 7 && i != 10)) {
            double longitude = cusps.get(i).getLongitude();
            angle = asc - longitude;
            Point point1 = new RectTriangle(hypothenusaSmall, angle).getPointAtEndOfHyp();
            Point point2 = new RectTriangle(hypothenusaLarge, angle).getPointAtEndOfHyp();
            values = convertCoordinateSet(point1, point2, corrForXY);
            gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));
         }
         double hypothenusa = 0.0;
         double angleForQuadrant = new Range(0.0, 360.0).checkValue(asc - cusps.get(i).getLongitude());
         if (0.0 <= angleForQuadrant && angleForQuadrant < 45.0) hypothenusa = metrics.getDiameterCuspTextsLeft();
         if (45.0 <= angleForQuadrant && angleForQuadrant < 135.0) hypothenusa = metrics.getDiameterCuspTextsTop();
         if (135.0 <= angleForQuadrant && angleForQuadrant < 225.0) hypothenusa = metrics.getDiameterCuspTextsRight();
         if (225.0 <= angleForQuadrant && angleForQuadrant < 315.0) hypothenusa = metrics.getDiameterCuspTextsBottom();
         if (315.0 <= angleForQuadrant && angleForQuadrant < 360.0) hypothenusa = metrics.getDiameterCuspTextsLeft();

         Point startPointFromCenter = new RectTriangle(hypothenusa, angleForQuadrant + 180.0).getPointAtEndOfHyp();
         values = convertCoordinateSet(startPointFromCenter, startPointFromCenter, corrForXY);  // FIXME, use method with only one point
         String posText = (new SexagesimalFormatter(2).formatDm(cusps.get(i).getLongitude() % 30.0));
         gc.setFont(new Font("Arial", metrics.getSizeTextFont()));
         gc.setFill(Color.RED);
         gc.fillText(posText, values.get(0), values.get(1));
      }
   }

   private void drawDegreeLines() {
      gc.setLineWidth(1.0);
      val hypothenusaSmall = metrics.getSizeDegrees5Circle() / 2;
      val hypothenusaMedium = metrics.getSizeDegreesCircle() / 2;
      val hypothenusaLarge = metrics.getSizeSignsCircle() / 2;
      double angle = 30 - offsetAsc % 30;
      val outerOffset = metrics.getOffsetOuterCircle();
      for (int i = 0; i <= 359; i++) {
         Point startPointFromCenter;
         if (i % 5 == 0) startPointFromCenter = new RectTriangle(hypothenusaSmall, angle).getPointAtEndOfHyp();
         else startPointFromCenter = new RectTriangle(hypothenusaMedium, angle).getPointAtEndOfHyp();
         Point endPointFromCenter = new RectTriangle(hypothenusaLarge, angle).getPointAtEndOfHyp();
         double corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
         final List<Double> values = convertCoordinateSet(startPointFromCenter, endPointFromCenter, corrForXY);
         gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));
         angle += 1.0;
      }
   }

   private void drawSignGlyphs() {
      gc.setStroke(Color.BLACK);
      gc.setFill(Color.BLACK);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.6);
      gc.setFont(new Font("EnigmaAstrology", metrics.getSizeGlyphFont()));
      double angle = 165 + offsetAsc % 30;      // 180 degrees (correct quadrant) minus 15 (glyph in center of sign).
      val hypothenusa = metrics.getSizeSignGlyphsCircle() / 2;
      val outerOffset = metrics.getOffsetOuterCircle();
      for (int i = 1; i <= 12; i++) {
         Point startPointFromCenter = new RectTriangle(hypothenusa, angle).getPointAtEndOfHyp();
         double corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
         final List<Double> values = convertCoordinateSet(startPointFromCenter, startPointFromCenter, corrForXY);  // FIXME, use method with only one point
         int signIndex = (int) (fullChart.getHouseValues().getAscendant().getLongitude() / 30) + i;
         if (signIndex > 12) signIndex = signIndex - 12;
         String glyph = signGlyphFromIndex(signIndex);
         gc.fillText(glyph, values.get(0) - metrics.getOffSetGlyphs(), values.get(1) + metrics.getOffSetGlyphs());
         angle -= 30.0;
         if (angle < 0.0) angle += 360.0;
      }
      gc.setFont(new Font("Courier", 10));
   }

   private void drawPlanets() {
      gc.setStroke(Color.BLACK);
      gc.setFill(Color.BLACK);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.6);
      gc.setFont(new Font("EnigmaAstrology", metrics.getSizeGlyphFont()));
      val bodies = fullChart.getBodies();
      val outerOffset = metrics.getOffsetOuterCircle();
      val corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
      val ascendant = fullChart.getHouseValues().getAscendant().getLongitude();
      double longitude;
      double angle;
      double angle1;
      double angle2;
      double minDist = metrics.getMinAngleObjects();
      double distance;
      /*
      sort bodies on position, use class PlotBodyInfo
      calculate plotposition
      check for minimal distance and change plotpositions if necessary
      use sorted and corrected plotpositions to draw glyphs for planets
       */

      final List<PlotBodyInfo> plotBodyInfos = new ArrayList<>();
      for (CelObjectPosition bodyPos : bodies) {
         longitude = bodyPos.getEclipticalPosition().getMainPosition();
         angle = new Range(0.0, 360.0).checkValue(ascendant - longitude);
         plotBodyInfos.add(new PlotBodyInfo(bodyPos.getCelestialBody(), angle, longitude));
      }
      Collections.sort(plotBodyInfos, new PlotBodyInfoComparator());
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
         gc.setFont(new Font("EnigmaAstrology", metrics.getSizeGlyphFont()));
         Point startPointFromCenter = new RectTriangle(hypothenusa, bodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
         final List<Double> values = convertCoordinateSet(startPointFromCenter, startPointFromCenter, corrForXY);  // FIXME, use method with only one point
         int bodyIndex = bodyInfo.getCelObject().getId();
         String glyph = celBodyGlyphFromIndex(bodyIndex);
         gc.fillText(glyph, values.get(0) - metrics.getOffSetGlyphs(), values.get(1) + metrics.getOffSetGlyphs());
         drawConnectLines(bodyInfo);

         drawPosition(bodyInfo);
      }
   }

   private void drawConnectLines(@NonNull final PlotBodyInfo plotBodyInfo) {
      gc.setStroke(Color.GREEN);
      gc.setFill(Color.GREEN);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.3);
      val corrForXY = metrics.getOffsetOuterCircle() + metrics.getSizeOuterCircle() / 2;
      val hypothenusa1 = metrics.getDiameterCelBodiesMedium() / 2 - metrics.getDistanceConnectLines();
      val hypothenusa2 = metrics.getSizeHousesCircle() / 2;
      val hypothenusa3 = metrics.getDiameterCelBodiesMedium() / 2 + metrics.getDistanceConnectLines();
      val hypothenusa4 = metrics.getSizeSignsCircle() / 2;
      Point startPointFromCenter = new RectTriangle(hypothenusa1, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      Point endPointFromCenter = new RectTriangle(hypothenusa2, plotBodyInfo.getAngleFromAsc() + 180.0).getPointAtEndOfHyp();
      List<Double> values = convertCoordinateSet(startPointFromCenter, endPointFromCenter, corrForXY);
      gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));
      startPointFromCenter = new RectTriangle(hypothenusa3, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      endPointFromCenter = new RectTriangle(hypothenusa4, plotBodyInfo.getAngleFromAsc() + 180.0).getPointAtEndOfHyp();
      values = convertCoordinateSet(startPointFromCenter, endPointFromCenter, corrForXY);
      gc.strokeLine(values.get(0), values.get(1), values.get(2), values.get(3));
      gc.setStroke(Color.BLACK);
      gc.setFill(Color.BLACK);
      gc.setLineWidth(1d);
      gc.setGlobalAlpha(0.6);
   }

   private void drawPosition(@NonNull final PlotBodyInfo plotBodyInfo) {
      val corrForXY = metrics.getOffsetOuterCircle() + metrics.getSizeOuterCircle() / 2;
      double hypothenusa = 0.0;
      val angleForQuadrant = plotBodyInfo.getAngleFromAsc();
      if (0.0 < angleForQuadrant && angleForQuadrant <= 45.0) hypothenusa = metrics.getDiameterPosTextsLeft();
      if (45.0 < angleForQuadrant && angleForQuadrant <= 135.0) hypothenusa = metrics.getDiameterPosTextsTop();
      if (135.0 < angleForQuadrant && angleForQuadrant <= 225.0) hypothenusa = metrics.getDiameterPosTextsRight();
      if (225.0 < angleForQuadrant && angleForQuadrant <= 315.0) hypothenusa = metrics.getDiameterPosTextsBottom();
      if (315.0 < angleForQuadrant && angleForQuadrant <= 360.0) hypothenusa = metrics.getDiameterPosTextsLeft();
      Point startPointFromCenter = new RectTriangle(hypothenusa, plotBodyInfo.getCorrectedAngle() + 180.0).getPointAtEndOfHyp();
      final List<Double> values = convertCoordinateSet(startPointFromCenter, startPointFromCenter, corrForXY);  // FIXME, use method with only one point
      gc.setFont(new Font("Arial", metrics.getSizeTextFont()));
      gc.fillText(plotBodyInfo.getPosText(), values.get(0), values.get(1));
   }


   private String signGlyphFromIndex(final int index) {
      // todo use sign-glyphs from settings
      String glyphs = "1234567890-=";
      return glyphs.substring(index - 1, index);
   }

   private String celBodyGlyphFromIndex(final int index) {
      // todo use celbody-glyphs from settings
      String glyph;
      switch (index) {
         case 1:
            glyph = "a";
            break;   // Sun
         case 2:
            glyph = "b";
            break;   // Moon
         case 3:
            glyph = "c";
            break;   // Mercury
         case 4:
            glyph = "d";
            break;   // Venus
         case 5:
            glyph = "e";
            break;   // Earth
         case 6:
            glyph = "f";
            break;   // Mars
         case 7:
            glyph = "g";
            break;   // Jupiter
         case 8:
            glyph = "h";
            break;   // Saturn
         case 9:
            glyph = "i";
            break;   // Uranus
         case 10:
            glyph = "j";
            break;  // Neptune
         case 11:
            glyph = "k";
            break;  // Pluto
         case 12:
            glyph = "w";
            break;  // Chiron
         case 13:
         case 14:
            glyph = "{";
            break;        // Lunar node
         default:
            glyph = "";
      }
      return glyph;
   }


   // Converts coordinates from RectTriangle to actual positions on the canvas.
   private List<Double> convertCoordinateSet(@NonNull final Point point1, @NonNull final Point point2,
                                             final double corrForXY) {
      List<Double> values = new ArrayList<>();
      values.add(corrForXY + point1.getXPos());
      values.add(corrForXY + point1.getYPos());
      values.add(corrForXY + point2.getXPos());
      values.add(corrForXY + point2.getYPos());
      return values;
   }


}
