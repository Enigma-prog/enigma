/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

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


public class ChartsDrawing2d {

   private Canvas canvas;
   private CalculatedFullChart fullChart;
   private GraphicsContext gc;
   private double radius = 580;

   public void setFullChart(@NonNull final CalculatedFullChart fullChart) {
      this.fullChart = fullChart;
      final Stage stage = new Stage();
      performDraw(stage);
   }

   private void performDraw(@NonNull final Stage stage) {

      canvas = new Canvas(700, 700);
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
      gridPane.setGridLinesVisible(true);

      gridPane.add(lblName, 0, 0, 2, 1);          // node col row colspan rowspan
      gridPane.add(chartPane, 0, 1, 2, 1);
      gridPane.add(btnHelp, 0, 2, 1, 1);
      gridPane.add(btnOk, 1, 2, 1, 1);

      stage.setMinHeight(400.0);
      stage.setMinWidth(320.0);
      stage.setScene(new Scene(gridPane, 800, 1000));  // pane, hor pos, vert pos
      stage.setTitle("Drawing of chart");
      stage.show();


      canvas.widthProperty().bind(chartPane.widthProperty());
      canvas.heightProperty().bind(chartPane.heightProperty());

      radius = (Math.min(canvas.getWidth(), canvas.getHeight())) * 0.8;

      stageSizeChangeListener(stage);
      performDraw();
   }


   private void stageSizeChangeListener(Stage stage) {
      stage.widthProperty().addListener((observable, oldValue, newValue) -> {
         radius = (Math.min(canvas.getWidth(), canvas.getHeight())) * 0.8;
         performDraw();
      });

      stage.heightProperty().addListener((observable, oldValue, newValue) -> {
         radius = (Math.min(canvas.getWidth(), canvas.getHeight())) * 0.8;
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
      gc.strokeOval(60, 60, radius, radius);  // x-as, y-as, breedte, hoogte

   }


}
