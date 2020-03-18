/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.be.astron.assist.HousePosition;
import com.radixpro.enigma.shared.Range;
import com.radixpro.enigma.ui.charts.screens.helpers.ChartDrawMetrics;
import com.radixpro.enigma.ui.charts.screens.helpers.Point;
import com.radixpro.enigma.ui.charts.screens.helpers.RectTriangle;
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
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.val;

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
      gc.setLineWidth(2d);
      gc.setGlobalAlpha(0.5d);
      drawCircle(metrics.getOffsetOuterCircle(), metrics.getSizeOuterCircle());
      drawCircle(metrics.getOffsetSignsCircle(), metrics.getSizeSignsCircle());
      drawCircle(metrics.getOffsetHousesCircle(), metrics.getSizeHousesCircle());
      drawSignSeparators();
      drawCorners();
      drawHouses();
   }

   private void drawCircle(final double offset, final double size) {
      gc.strokeOval(offset, offset, size, size);
   }

   private void drawSignSeparators() {
//      int startY = (int) (metrics.getOffsetOuterCircle() + metrics.getSizeOuterCircle() / 2);
//      int startX = startY;
//      Point center = new Point(startX, startY);

      val hypothenusaSmall = (int) Math.round(metrics.getSizeOuterCircle() / 2);
      val hypothenusaLarge = (int) Math.round(metrics.getSizeSignsCircle() / 2);
      double angle = 30 - offsetAsc % 30;


      val outerOffset = metrics.getOffsetOuterCircle();
      for (int i = 1; i <= 12; i++) {
         Point startPointFromCenter = new RectTriangle(hypothenusaSmall, angle).getPointAtEndOfHyp();
         Point endPointFromCenter = new RectTriangle(hypothenusaLarge, angle).getPointAtEndOfHyp();
         double corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
         double realX1 = corrForXY + startPointFromCenter.getXPos();
         double realY1 = corrForXY + startPointFromCenter.getYPos();
         double realX2 = corrForXY + endPointFromCenter.getXPos();
         double realY2 = corrForXY + endPointFromCenter.getYPos();
         gc.setLineWidth(2d);
         gc.strokeLine(realX1, realY1, realX2, realY2);
         angle += 30.0;
      }
   }

   private void drawCorners() {
      gc.setLineWidth(4.0);

      double distanceFromCenter = metrics.getSizeOuterCircle() / 2 + metrics.getOffsetOuterCircle();
      double xPos1 = 0.0;
      double yPos1 = distanceFromCenter;
      double xPos2 = distanceFromCenter - metrics.getSizeHousesCircle() / 2;
      double yPos2 = distanceFromCenter;
      gc.strokeLine(xPos1, yPos1, xPos2, yPos2);  // asc line

      xPos1 = distanceFromCenter + metrics.getSizeHousesCircle() / 2;
      yPos1 = distanceFromCenter;
      xPos2 = distanceFromCenter * 2.0;
      yPos2 = distanceFromCenter;
      gc.strokeLine(xPos1, yPos1, xPos2, yPos2);  // desc line

      double angleMc = new Range(0.0, 360.0).checkValue(fullChart.getHouseValues().getAscendant().getLongitude()
            - fullChart.getHouseValues().getMc().getLongitude());

      double hypothenusaLarge = metrics.getSizeOuterCircle() / 2 + metrics.getOffsetOuterCircle();
      double hypothenusaSmall = metrics.getSizeHousesCircle() / 2;
      Point point1 = new RectTriangle(hypothenusaSmall, angleMc).getPointAtEndOfHyp();
      Point point2 = new RectTriangle(hypothenusaLarge, angleMc).getPointAtEndOfHyp();

      val outerOffset = metrics.getOffsetOuterCircle();
      double corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;
      double realX1 = corrForXY + point1.getXPos();
      double realY1 = corrForXY + point1.getYPos();
      double realX2 = corrForXY + point2.getXPos();
      double realY2 = corrForXY + point2.getYPos();
      gc.strokeLine(realX1, realY1, realX2, realY2);  // ic

      point1 = new RectTriangle(hypothenusaSmall, angleMc + 180.0).getPointAtEndOfHyp();
      point2 = new RectTriangle(hypothenusaLarge, angleMc + 180.0).getPointAtEndOfHyp();
      realX1 = corrForXY + point1.getXPos();
      realY1 = corrForXY + point1.getYPos();
      realX2 = corrForXY + point2.getXPos();
      realY2 = corrForXY + point2.getYPos();
      gc.strokeLine(realX1, realY1, realX2, realY2);  // ic
   }

   private void drawHouses() {
      gc.setLineWidth(1.0);
      val houseSystem = fullChart.getSettings().getHouseSystem();
      val quadrant = houseSystem.isQuadrantSystem();
      double angle;
      double asc = fullChart.getHouseValues().getAscendant().getLongitude();
      val outerOffset = metrics.getOffsetOuterCircle();
      double corrForXY = outerOffset + Math.round(metrics.getSizeOuterCircle() / 2);
      final List<HousePosition> cusps = fullChart.getHouseValues().getCusps();
      double hypothenusaLarge = metrics.getSizeSignsCircle() / 2;
      double hypothenusaSmall = metrics.getSizeHousesCircle() / 2;

      for (int i = 1; i <= 12; i++) {
         if (!quadrant || (i != 1 && i != 4 && i != 7 && i != 10)) {
            double longitude = cusps.get(i).getLongitude();
            angle = asc - longitude;
            Point point1 = new RectTriangle(hypothenusaSmall, angle).getPointAtEndOfHyp();
            Point point2 = new RectTriangle(hypothenusaLarge, angle).getPointAtEndOfHyp();
            double realX1 = corrForXY + point1.getXPos();
            double realY1 = corrForXY + point1.getYPos();
            double realX2 = corrForXY + point2.getXPos();
            double realY2 = corrForXY + point2.getYPos();
            gc.strokeLine(realX1, realY1, realX2, realY2);
         }

      }

   }


}
