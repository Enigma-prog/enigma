/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.configs.screens.ConfigEdit;
import com.radixpro.enigma.ui.configs.screens.ConfigOverview;
import com.radixpro.enigma.ui.shared.InputStatus;
import com.radixpro.enigma.ui.shared.factories.ButtonFactory;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableChartData;
import com.radixpro.enigma.xchg.api.CalculatedFullChart;
import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;

public class ChartsStart {

   private static final Logger LOG = Logger.getLogger(ChartsStart.class);

   private static final double WIDTH = 700.0;
   private static final double HEIGHT = 600.0;
   private static final double TITLE_HEIGHT = 45.0;
   private static final double SUBTITLE_HEIGHT = 30.0;
   private static final double TV_HEIGHT = 250.0;
   private static final double BTN_PANE_HEIGHT = 30.0;
   private static final double SEPARATOR_HEIGHT = 20.0;
   private static final double CONFIG_DETAIL_PANE_HEIGHT = 100.0;
   private final Stage stage;
   private final Rosetta rosetta;
   private Button btnNewChart;
   private Button btnSearchChart;
   private Button btnDeleteChart;
   private Button btnShowChart;
   private Button btnHelp;
   private Button btnConfig;
   private Button btnExit;
   private TableView<PresentableChartData> tvCharts;
   private TableColumn<String, PresentableChartData> colName;
   private TableColumn<String, PresentableChartData> colData;
   private CalculatedFullChart currentFullChart;
   private static final String ROSETTA_LOC = "rb/texts";

   public ChartsStart(Stage stage, Rosetta rosetta) {
      this.stage = checkNotNull(stage);
      this.rosetta = checkNotNull(rosetta);
      showChartOverview();
   }

   private void showChartOverview() {
      stage.setMinHeight(HEIGHT);
      stage.setMinWidth(WIDTH);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(rosetta.getText("ui.charts.start.pagetitle"));
      stage.setScene(new Scene(createVBox()));
      stage.showAndWait();
   }

   private VBox createVBox() {
      VBox vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(WIDTH);
      vBox.setPrefHeight(HEIGHT);
      tvCharts = createTableViewCharts();
      vBox.getChildren().add(0, createPaneTitle());
      vBox.getChildren().add(1, createPaneSubTitleCharts());
      vBox.getChildren().add(2, tvCharts);
      vBox.getChildren().add(3, createPaneChartBtns());
      vBox.getChildren().add(4, createPaneSeparator());
      vBox.getChildren().add(5, createPaneSubTitleConfigs());
      vBox.getChildren().add(6, createPaneConfigDetails());
      vBox.getChildren().add(7, createPaneGeneralButtons());
      return vBox;
   }

   private Pane createPaneTitle() {
      final Pane pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.charts.start.pagetitle"), "titletext", WIDTH));
      return pane;
   }

   private Pane createPaneSubTitleCharts() {
      final Pane pane = PaneFactory.createPane(SUBTITLE_HEIGHT, WIDTH, "subtitlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.charts.start.chartstitle"), "subtitletext", WIDTH));
      return pane;
   }

   private Pane createPaneSubTitleConfigs() {
      final Pane pane = PaneFactory.createPane(SUBTITLE_HEIGHT, WIDTH, "subtitlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.charts.start.configtitle"), "subtitletext", WIDTH));
      return pane;
   }

   private TableView<PresentableChartData> createTableViewCharts() {
      TableView<PresentableChartData> tableView = new TableView<>();
      tableView.setPrefHeight(TV_HEIGHT);
      tableView.setPrefWidth(WIDTH);
      TableColumn<PresentableChartData, String> nameColumn = new TableColumn<>(rosetta.getText("ui.charts.start.colheaderchartname"));
      TableColumn<PresentableChartData, String> dataColumn = new TableColumn<>(rosetta.getText("ui.charts.start.colheaderchartdata"));
      nameColumn.setCellFactory(new PropertyValueFactory("chartName"));
      dataColumn.setCellFactory(new PropertyValueFactory("chartDataDescr"));
      tableView.getColumns().add(nameColumn);
      tableView.getColumns().add(dataColumn);
      return tableView;
   }

   private Pane createPaneChartBtns() {
      final Pane pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createButtonBarCharts());
      return pane;
   }

   private ButtonBar createButtonBarCharts() {
      ButtonBar buttonBar = new ButtonBar();
      btnShowChart = ButtonFactory.createButton(rosetta.getText("ui.charts.start.btn.show"), true);
      btnDeleteChart = ButtonFactory.createButton(rosetta.getText("ui.charts.start.btn.delete"), true);
      btnNewChart = ButtonFactory.createButton(rosetta.getText("ui.charts.start.btn.new"), false);
      btnSearchChart = ButtonFactory.createButton(rosetta.getText("ui.charts.start.btn.search"), false);

      btnNewChart.setOnAction(click -> onNewChart());
      btnSearchChart.setOnAction(click -> onSearchChart());

      buttonBar.getButtons().add(btnShowChart);
      buttonBar.getButtons().add(btnDeleteChart);
      buttonBar.getButtons().add(btnNewChart);
      buttonBar.getButtons().add(btnSearchChart);
      return buttonBar;
   }

   private Pane createPaneGeneralButtons() {
      final Pane pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createButtonBarGeneral());
      return pane;
   }

   private ButtonBar createButtonBarGeneral() {
      ButtonBar buttonBar = new ButtonBar();
      btnHelp = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.help"), false);
      btnConfig = ButtonFactory.createButton(rosetta.getText("ui.charts.start.btn.config"), false);
      btnExit = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.exit"), false);

      btnConfig.setOnAction(click -> onConfig());

      buttonBar.getButtons().add(btnHelp);
      buttonBar.getButtons().add(btnConfig);
      buttonBar.getButtons().add(btnExit);
      return buttonBar;
   }


   private Pane createPaneSeparator() {
      return PaneFactory.createPane(SEPARATOR_HEIGHT, WIDTH);
   }

   private Pane createPaneConfigDetails() {
      Pane pane = PaneFactory.createPane(CONFIG_DETAIL_PANE_HEIGHT, WIDTH);

      // use TableView from ConfigDetails. Move method createTableView from ConfigDetails to a separate class.

      return pane;
   }


   void onConfig() {
      ConfigOverview configOverview = new ConfigOverview();
   }

   void onNewChart() {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsinput.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = null;
      try {
         parent = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }
      ChartsInput chartsInput = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
      if (chartsInput.getInputStatus() == InputStatus.READY) {
         long newChartId = chartsInput.getNewChartId();
         ChartData chartData = addChart(newChartId);
         try {
            showChart(chartData);
         } catch (Exception e) {
            e.printStackTrace();
         }
         drawChart2D();
      }
   }

   //   @FXML
   void onSearchChart() {
      // show search window
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartssearch.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = null;
      try {
         parent = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }
      ChartsSearch chartsSearch = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
      // read selected chart
      if (chartsSearch.isSelectionMade()) {
         ChartData chartData = chartsSearch.getSelectedItem();
         PresentableChartData presentableChartData = new PresentableChartData(chartData);
         colName.setCellValueFactory(new PropertyValueFactory<>("chartName"));
         colData.setCellValueFactory(new PropertyValueFactory<>("chartDataDescr"));
         tvCharts.getItems().add(presentableChartData);
         showChart(chartData);
      }


      // voeg horoscopen toe aan bestaande set
   }

   //   @FXML
   void onPositions() {
      showPositions();
   }

   //   @FXML
   void onConfigEdit() {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/configedit.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));

      Parent parent = null;
      try {
         parent = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }
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
      Configuration currentConfig = confApi.read(currentConfigId).get(0);
      // show config
   }

   private ChartData addChart(final long chartId) {
      PersistedChartDataApi api = new PersistedChartDataApi();
      ChartData chartData = api.read(chartId).get(0);
      PresentableChartData presentableChartData = new PresentableChartData(chartData);
      colName.setCellValueFactory(new PropertyValueFactory<>("chartName"));
      colData.setCellValueFactory(new PropertyValueFactory<>("chartDataDescr"));
      tvCharts.getItems().add(presentableChartData);
      return chartData;
   }

   private void showChart(final ChartData chartData) {
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
      CalculationSettings settings = new CalculationSettings(requestedBodies, HouseSystems.PLACIDUS, Ayanamshas.NONE, false,
            false, false);
      currentFullChart = new CalculatedFullChart(chartData.getFullDateTime(),
            chartData.getLocation(), settings);
      showPositions();
      drawChart2D();
   }

   private void showPositions() {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsdata.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle(ROSETTA_LOC, Rosetta.getRosetta().getLocale()));
      Parent parent = null;
      try {
         parent = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }
      ChartsData chartsData = fxmlLoader.getController();
      chartsData.setCalculatedFullChart(currentFullChart);
      Scene scene = new Scene(parent, 980, 1000);
      Stage stage = new Stage();
      stage.initModality(Modality.NONE);
      stage.setScene(scene);
      stage.show();
   }

   private void drawChart2D() {
      ChartsDrawing2d chartsDrawing2d = new ChartsDrawing2d();
      chartsDrawing2d.setFullChart(currentFullChart);
   }

}
