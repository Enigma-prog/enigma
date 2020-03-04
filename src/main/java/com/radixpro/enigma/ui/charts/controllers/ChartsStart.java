/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.controllers;

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
   TableView<PresentableChartData> tvCharts;
   @FXML
   TableColumn<String, PresentableChartData> colName;
   @FXML
   TableColumn<String, PresentableChartData> colData;
   private static final String ROSETTA_LOC = "rb/texts";

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
         // temporary solution, replace with data from configuration
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
         CalculatedFullChart calculatedFullChart = new CalculatedFullChart(chartData.getFullDateTime(),
               chartData.getLocation(), settings);
         // show positions
         // show drawing
      }
   }

   @FXML
   void onPositions() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsdata.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = fxmlLoader.load();
      ChartsData chartsData = new ChartsData();
      // define actual fullchart for ChartsData
      Scene scene = new Scene(parent, 1000, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
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

}
