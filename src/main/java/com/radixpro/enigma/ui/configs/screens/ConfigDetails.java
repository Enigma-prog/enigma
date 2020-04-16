/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs.screens;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.configs.helpers.PropertiesForConfig;
import com.radixpro.enigma.ui.shared.Help;
import com.radixpro.enigma.ui.shared.factories.ButtonFactory;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;

/**
 * Presentation of details for a specific configuration.
 */
public class ConfigDetails {

   private static final Logger LOG = Logger.getLogger(ConfigDetails.class);
   private static final double WIDTH = 500.0;
   private static final double HEIGHT = 600.0;
   private static final double TITLE_HEIGHT = 45.0;
   private static final double SUBTITLE_HEIGHT = 30.0;
   private static final double BTN_PANE_HEIGHT = 30.0;
   private static final double TV_HEIGHT = 450.0;
   private static final double SEPARATOR_HEIGHT = 20.0;
   private Stage stage;
   private final Rosetta rosetta;
   private final String configName;
   private final PropertiesForConfig propertiesForConfig;
   private Button btnExit;
   private Button btnHelp;

   public ConfigDetails(final String configName, final PropertiesForConfig propertiesForConfig, final Rosetta rosetta) {
      this.rosetta = checkNotNull(rosetta);
      this.configName = checkNotNull(configName);
      this.propertiesForConfig = checkNotNull(propertiesForConfig);
      stage = new Stage();
      showDetails();
   }

   private void showDetails() {
      stage = new Stage();
      stage.setMinHeight(HEIGHT);
      stage.setMinWidth(WIDTH);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(rosetta.getText("ui.configs.overview.title"));
      stage.setScene(new Scene(createVBox()));
      stage.show();
   }

   private VBox createVBox() {
      VBox vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(WIDTH);
      vBox.setPrefHeight(HEIGHT);
      vBox.getChildren().add(0, createPaneTitle());
      vBox.getChildren().add(1, createPaneSubTitle());
      vBox.getChildren().add(2, createPaneData());
      vBox.getChildren().add(3, createPaneSeparator());
      vBox.getChildren().add(4, createPaneBtns());

      return vBox;
   }

   private Pane createPaneTitle() {
      Pane pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.configs.overview.title"), "titletext", WIDTH));
      return pane;
   }

   private Pane createPaneSubTitle() {
      Pane pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "subtitlepane");
      pane.getChildren().add(LabelFactory.createLabel(configName, "subtitletext", WIDTH));
      return pane;
   }

   private Pane createPaneData() {
      Pane pane = PaneFactory.createPane(TV_HEIGHT, WIDTH);
      pane.getChildren().add(createTableView());
      return pane;
   }

   private Pane createPaneSeparator() {
      return PaneFactory.createPane(SEPARATOR_HEIGHT, WIDTH);
   }

   private Pane createPaneBtns() {
      Pane pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createButtonBar());
      return pane;
   }

   private TableView<PresentableProperty> createTableView() {
      TableView tableView = new TableView<PresentableProperty>();
      tableView.setPrefHeight(TV_HEIGHT);
      tableView.setPrefWidth(WIDTH);
      TableColumn propertyColumn = new TableColumn<PresentableProperty, String>("Property");
      propertyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      TableColumn valueColumn = new TableColumn<PresentableProperty, String>("Value");
      valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
      tableView.getColumns().add(propertyColumn);
      tableView.getColumns().add(valueColumn);
      List<PresentableProperty> properties = propertiesForConfig.getProperties();
      for (PresentableProperty prop : properties) {
         tableView.getItems().add(prop);
      }
      return tableView;
   }


   private ButtonBar createButtonBar() {
      ButtonBar buttonBar = new ButtonBar();
      btnHelp = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.help"), false);
      btnExit = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.exit"), false);

      btnHelp.setOnAction(click -> onHelp());
      btnExit.setOnAction(click -> onExit());

      buttonBar.getButtons().add(btnHelp);
      buttonBar.getButtons().add(btnExit);

      return buttonBar;
   }

   private void onHelp() {
      new Help(rosetta.getHelpText("help.configdetails.title"), rosetta.getHelpText("help.configdetails.content"));
   }

   private void onExit() {
      stage.close();
   }


}
