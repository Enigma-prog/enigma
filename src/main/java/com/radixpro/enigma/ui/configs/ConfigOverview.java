/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.factories.ButtonFactory;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableConfiguration;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.val;

import java.util.List;

import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;


/**
 * Overview of configurations with the possibility to perform actions on these configurations.
 */
public class ConfigOverview {
   private static final double WIDTH = 600.0;
   private static final double HEIGHT = 700.0;
   private static final double TITLE_HEIGHT = 45.0;
   private static final double SUBTITLE_HEIGHT = 30.0;
   private static final double BTN_PANE_HEIGHT = 30.0;
   private static final double TV_HEIGHT = 200.0;

   private Stage stage;
   private Rosetta rosetta;

   public ConfigOverview() {
      stage = new Stage();
      rosetta = Rosetta.getRosetta();
      showOverview();
   }

   private void showOverview() {
      stage = new Stage();
      stage.setMinHeight(HEIGHT);
      stage.setMinWidth(WIDTH);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(rosetta.getText("ui.configs.overview.title"));
      stage.setScene(new Scene(createVBox()));
      stage.show();
   }

   private VBox createVBox() {
      val vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(WIDTH);
      vBox.setPrefHeight(HEIGHT);
      vBox.getChildren().add(0, createPaneTitle());
      vBox.getChildren().add(1, createPaneStandardSubtitle());
      vBox.getChildren().add(2, createPaneStandard());
      vBox.getChildren().add(3, createPaneBtnsAction());
      vBox.getChildren().add(4, createPaneBtnsGeneral());
      return vBox;
   }

   private Pane createPaneTitle() {
      val pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.configs.overview.title"), "titletext", WIDTH));
      return pane;
   }

   private Pane createPaneStandardSubtitle() {
      val pane = PaneFactory.createPane(SUBTITLE_HEIGHT, WIDTH, "subtitlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.configs.overview.stndsubtitle"), "subtitletext", WIDTH));
      return pane;
   }

   private Pane createPaneStandard() {
      val pane = PaneFactory.createPane(TV_HEIGHT, WIDTH);
      pane.getChildren().add(createTvStandard());
      return pane;
   }

   private Pane createPaneBtnsAction() {
      val pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createBtnBarAction());
      return pane;
   }

   private Pane createPaneBtnsGeneral() {
      val pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createBtnBarGeneral());
      return pane;
   }

   private TableView<PresentableConfiguration> createTvStandard() {
      val tableView = new TableView();
      tableView.setPrefHeight(TV_HEIGHT);
      tableView.setPrefWidth(WIDTH);
      TableColumn<String, PresentableConfiguration> nameColumn = new TableColumn<>("Name");
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("configName"));
      TableColumn<String, PresentableConfiguration> descriptionColumn = new TableColumn<>("Description");
      descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("configDescription"));
      TableColumn<String, PresentableConfiguration> stndColumn = new TableColumn<>("Standard ?");
      stndColumn.setCellValueFactory(new PropertyValueFactory<>("standardIndication"));

      tableView.getColumns().add(nameColumn);
      tableView.getColumns().add(descriptionColumn);
      tableView.getColumns().add(stndColumn);

      PersistedConfigurationApi api = new PersistedConfigurationApi();
      List<Configuration> configs = api.readAll();
      for (Configuration config : configs) {
         PresentableConfiguration presConfig = new PresentableConfiguration(config);
         tableView.getItems().add(presConfig);
      }
      return tableView;
   }

   private ButtonBar createBtnBarAction() {
      val buttonBar = new ButtonBar();
      buttonBar.getButtons().add(ButtonFactory.createButton(rosetta.getText("ui.shared.btn.select"), true));
      buttonBar.getButtons().add(ButtonFactory.createButton(rosetta.getText("ui.shared.btn.new"), true));
      buttonBar.getButtons().add(ButtonFactory.createButton(rosetta.getText("ui.shared.btn.delete"), true));
      return buttonBar;
   }

   private ButtonBar createBtnBarGeneral() {
      val buttonBar = new ButtonBar();
      buttonBar.getButtons().add(ButtonFactory.createButton(rosetta.getText("ui.shared.btn.help"), false));
      buttonBar.getButtons().add(ButtonFactory.createButton(rosetta.getText("ui.shared.btn.cancel"), false));
      buttonBar.getButtons().add(ButtonFactory.createButton(rosetta.getText("ui.shared.btn.ok"), false));
      return buttonBar;
   }

}