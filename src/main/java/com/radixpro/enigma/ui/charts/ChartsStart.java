/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.configs.ConfigEdit;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableChartData;
import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

   private Configuration currentConfig;


   @FXML
   void onNewChart() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsinput.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));
      Parent parent = fxmlLoader.load();
      ChartsInput chartsInput = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();

      long newChartId = chartsInput.getNewChartId();
      addChart(newChartId);
   }

   @FXML
   void onConfigEdit() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/configedit.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));

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
      PersistedPropertyApi propApi = new PersistedPropertyApi();
      int currentConfigId = Integer.parseInt(propApi.read("config").get(0).getValue());
      PersistedConfigurationApi confApi = new PersistedConfigurationApi();
      currentConfig = confApi.read(currentConfigId).get(0);
   }

   private void addChart(final long chartId) {
      var api = new PersistedChartDataApi();
      final var chartData = api.read(chartId).get(0);
      final var presentableChartData = new PresentableChartData(chartData);
      colName.setCellValueFactory(new PropertyValueFactory<>("chartName"));
      colData.setCellValueFactory(new PropertyValueFactory<>("chartDataDescr"));

      tvCharts.getItems().add(presentableChartData);

   }

}
