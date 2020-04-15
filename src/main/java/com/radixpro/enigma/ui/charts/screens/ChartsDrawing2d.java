/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.shared.Range;
import com.radixpro.enigma.ui.charts.screens.helpers.ChartDrawMetrics;
import com.radixpro.enigma.ui.charts.screens.helpers.Point;
import com.radixpro.enigma.ui.charts.screens.helpers.RadixWheel;
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

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class ChartsDrawing2d {

   private ChartDrawMetrics metrics;

   private Canvas canvas;
   private CalculatedFullChart fullChart;
   private GraphicsContext gc;
   private double offsetAsc;
   private double outerOffset;
   private double corrForXY;

   public void setFullChart(final CalculatedFullChart fullChart) {
      this.fullChart = checkNotNull(fullChart);
      final Stage stage = new Stage();
      drawChart(stage);
   }

   private void drawChart(final Stage stage) {
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
      outerOffset = metrics.getOffsetOuterCircle();
      corrForXY = outerOffset + metrics.getSizeOuterCircle() / 2;

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
      gc.setLineWidth(metrics.getWidthMediumLines());
      gc.setGlobalAlpha(0.5d);
      RadixWheel wheel = new RadixWheel(gc, metrics, fullChart);
   }


   // Converts coordinates from RectTriangle to actual positions on the canvas.
   private List<Double> convertCoordinateSet(final Point point1, final Point point2) {
      List<Double> values = new ArrayList<>();
      values.add(corrForXY + point1.getXPos());
      values.add(corrForXY + point1.getYPos());
      values.add(corrForXY + point2.getXPos());
      values.add(corrForXY + point2.getYPos());
      return values;
   }

   // Converts coordinates from RectTriangle to actual positions on the canvas.
   private List<Double> convertCoordinateSet(final Point point) {
      List<Double> values = new ArrayList<>();
      values.add(corrForXY + point.getXPos());
      values.add(corrForXY + point.getYPos());
      return values;
   }

   // quadrants 0f 90 degrees each: 1 with the asc as center, 3 with the desc as center, 2 top, between 1 and 3,
   // 4 bottom, between 2 and 4.
   private int defineQuadrant(final double degreesFromAsc) {
      final double angleForQuadrant = new Range(0, 360).checkValue(degreesFromAsc);
      int quadrant = 0;
      if (0.0 <= angleForQuadrant && angleForQuadrant < 45.0) quadrant = 1;
      else if (45.0 <= angleForQuadrant && angleForQuadrant < 135.0) quadrant = 2;
      else if (135.0 <= angleForQuadrant && angleForQuadrant < 225.0) quadrant = 3;
      else if (225.0 <= angleForQuadrant && angleForQuadrant < 315.0) quadrant = 4;
      else if (315.0 <= angleForQuadrant && angleForQuadrant < 360.0) quadrant = 1;
      return quadrant;
   }
}
