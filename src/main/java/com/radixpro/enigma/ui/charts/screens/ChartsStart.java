/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.configs.ConfigEdit;
import com.radixpro.enigma.ui.shared.InputStatus;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableChartData;
import com.radixpro.enigma.xchg.api.CalculatedFullChart;
import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.val;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChartsStart {

   @FXML
   MenuItem menuConfigEdit;
   @FXML
   Button btnNewChart;
   @FXML
   Button btnSearchChart;
   @FXML
   TableView<PresentableChartData> tvCharts;
   @FXML
   TableColumn<String, PresentableChartData> colName;
   @FXML
   TableColumn<String, PresentableChartData> colData;
   private static final String ROSETTA_LOC = "rb/texts";
   private CalculatedFullChart currentFullChart;

   @FXML
   void onNewChart() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsinput.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = fxmlLoader.load();
      ChartsInput chartsInput = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
      if (chartsInput.getInputStatus() == InputStatus.READY) {
         long newChartId = chartsInput.getNewChartId();
         val chartData = addChart(newChartId);
         showChart(chartData);
         drawChart2D();
      }
   }

   @FXML
   void onSearchChart() throws IOException {
      // show search window
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartssearch.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = fxmlLoader.load();
      ChartsSearch chartsSearch = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
      // read selected chart
      if (chartsSearch.isSelectionMade()) {
         ChartData chartData = chartsSearch.getSelectedItem();
         val presentableChartData = new PresentableChartData(chartData);
         colName.setCellValueFactory(new PropertyValueFactory<>("chartName"));
         colData.setCellValueFactory(new PropertyValueFactory<>("chartDataDescr"));
         tvCharts.getItems().add(presentableChartData);
         showChart(chartData);
      }


      // voeg horoscopen toe aan bestaande set
   }

   @FXML
   void onPositions() throws IOException {
      showPositions();
   }

   @FXML
   void onConfigEdit() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/configedit.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));

      Parent parent = fxmlLoader.load();
      ConfigEdit configEdit = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }

   public void initialize() {
      defineConfig();
      defineCharts();
   }

   private void defineCharts() {
      tvCharts.setPlaceholder(new Label(Rosetta.getRosetta().getText("ui.charts.start.placeholdercharts")));
   }

   private void defineConfig() {
      val propApi = new PersistedPropertyApi();
      val currentConfigId = Integer.parseInt(propApi.read("config").get(0).getValue());
      val confApi = new PersistedConfigurationApi();
      Configuration currentConfig = confApi.read(currentConfigId).get(0);
      // show config
   }

   private ChartData addChart(final long chartId) {
      val api = new PersistedChartDataApi();
      val chartData = api.read(chartId).get(0);
      val presentableChartData = new PresentableChartData(chartData);
      colName.setCellValueFactory(new PropertyValueFactory<>("chartName"));
      colData.setCellValueFactory(new PropertyValueFactory<>("chartDataDescr"));
      tvCharts.getItems().add(presentableChartData);
      return chartData;
   }

   private void showChart(@NonNull final ChartData chartData) throws IOException {
      List<CelestialObjects> requestedBodies = new ArrayList<>();
      requestedBodies.add(CelestialObjects.SUN);
      requestedBodies.add(CelestialObjects.MOON);
      requestedBodies.add(CelestialObjects.MERCURY);
      requestedBodies.add(CelestialObjects.VENUS);
      requestedBodies.add(CelestialObjects.MARS);
      requestedBodies.add(CelestialObjects.JUPITER);
      requestedBodies.add(CelestialObjects.SATURN);
      requestedBodies.add(CelestialObjects.URANUS);
      requestedBodies.add(CelestialObjects.NEPTUNE);
      requestedBodies.add(CelestialObjects.PLUTO);
      requestedBodies.add(CelestialObjects.CHEIRON);
      requestedBodies.add(CelestialObjects.MEAN_NODE);
      val settings = new CalculationSettings(requestedBodies, HouseSystems.PLACIDUS, Ayanamshas.NONE, false,
            false, false);
      currentFullChart = new CalculatedFullChart(chartData.getFullDateTime(),
            chartData.getLocation(), settings);
      showPositions();
      drawChart2D();
   }

   private void showPositions() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsdata.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = fxmlLoader.load();
      ChartsData chartsData = fxmlLoader.getController();
      chartsData.setCalculatedFullChart(currentFullChart);
      Scene scene = new Scene(parent, 980, 1000);
      Stage stage = new Stage();
      stage.initModality(Modality.NONE);
      stage.setScene(scene);
      stage.show();
   }

   private void drawChart2D() throws IOException {
      ChartsDrawing2d chartsDrawing2d = new ChartsDrawing2d();
      chartsDrawing2d.setFullChart(currentFullChart);
   }

}
