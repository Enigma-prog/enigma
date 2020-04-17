/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs.screens;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.configs.factories.ConfigDetailsFactory;
import com.radixpro.enigma.ui.configs.factories.ConfigEditFactory;
import com.radixpro.enigma.ui.configs.factories.ConfigNewFactory;
import com.radixpro.enigma.ui.shared.Help;
import com.radixpro.enigma.ui.shared.InputStatus;
import com.radixpro.enigma.ui.shared.factories.ButtonFactory;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableConfiguration;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;


/**
 * Overview of configurations with the possibility to perform actions on these configurations.
 */
public class ConfigOverview {
   private static final double WIDTH = 700.0;
   private static final double HEIGHT = 600.0;
   private static final double TITLE_HEIGHT = 45.0;
   private static final double BTN_PANE_HEIGHT = 30.0;
   private static final double TV_HEIGHT = 400.0;
   private static final double INSTRUCTION_HEIGHT = 45.0;
   private static final double SEPARATOR_HEIGHT = 20.0;
   private Stage stage;
   private Rosetta rosetta;
   private ObservableList<PresentableConfiguration> selectedItems;
   private Button btnSelect;
   private Button btnNew;
   private Button btnDetails;
   private Button btnEdit;
   private Button btnDelete;
   private Button btnHelp;
   private Button btnExit;
   private PersistedConfigurationApi api;

   public ConfigOverview() {
      api = new PersistedConfigurationApi();
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
      showOrReshow();
   }

   private void showOrReshow() {
      stage.setScene(new Scene(createVBox()));
      stage.show();
   }

   private VBox createVBox() {
      final VBox vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(WIDTH);
      vBox.setPrefHeight(HEIGHT);
      vBox.getChildren().add(0, createPaneTitle());
      vBox.getChildren().add(1, createPaneInstruction());
      vBox.getChildren().add(2, createPaneStandard());
      vBox.getChildren().add(3, createPaneSeparator());
      vBox.getChildren().add(4, createPaneBtns());
      return vBox;
   }

   private Pane createPaneSeparator() {
      return PaneFactory.createPane(SEPARATOR_HEIGHT, WIDTH);
   }

   private Pane createPaneTitle() {
      final Pane pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.configs.details.title"), "titletext", WIDTH));
      return pane;
   }

   private Pane createPaneInstruction() {
      final Pane pane = PaneFactory.createPane(INSTRUCTION_HEIGHT, WIDTH);
      Label lblInstruction = LabelFactory.createLabel(rosetta.getText("ui.configs.overview.instruction"));
      lblInstruction.setPrefHeight(INSTRUCTION_HEIGHT);
      lblInstruction.setPrefWidth(WIDTH);
      lblInstruction.setAlignment(Pos.CENTER);
      pane.getChildren().add(lblInstruction);
      return pane;
   }

   private Pane createPaneStandard() {
      final Pane pane = PaneFactory.createPane(TV_HEIGHT, WIDTH);
      pane.getChildren().add(createTableView());
      return pane;
   }

   private Pane createPaneBtns() {
      final Pane pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createButtonBar());
      return pane;
   }

   private TableView<PresentableConfiguration> createTableView() {
      final TableView tableView = new TableView<PresentableConfiguration>();
      tableView.setPrefHeight(TV_HEIGHT);
      tableView.setPrefWidth(WIDTH);
      TableColumn<PresentableConfiguration, String> nameColumn = new TableColumn<>("Name");
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("configName"));
      TableColumn<PresentableConfiguration, String> descriptionColumn = new TableColumn<>("Description");
      descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("configDescription"));
      TableColumn<PresentableConfiguration, String> stndColumn = new TableColumn<>("Standard ?");
      stndColumn.setCellValueFactory(new PropertyValueFactory<>("standardIndication"));
      TableViewSelectionModel<PresentableConfiguration> selectionModel = tableView.getSelectionModel();
      selectionModel.setSelectionMode(SelectionMode.SINGLE);
      selectedItems = selectionModel.getSelectedItems();
      selectedItems.addListener((ListChangeListener<PresentableConfiguration>) change -> onSelect());

      tableView.getColumns().add(nameColumn);
      tableView.getColumns().add(descriptionColumn);
      tableView.getColumns().add(stndColumn);

      List<Configuration> configs = api.readAll();
      for (Configuration config : configs) {
         PresentableConfiguration presConfig = new PresentableConfiguration(config);
         tableView.getItems().add(presConfig);
      }
      return tableView;
   }

   private ButtonBar createButtonBar() {
      final ButtonBar buttonBar = new ButtonBar();
      btnSelect = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.select"), true);
      btnNew = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.new"), true);
      btnDetails = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.details"), true);
      btnEdit = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.edit"), true);
      btnDelete = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.delete"), true);
      btnHelp = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.help"), false);
      btnExit = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.exit"), false);

      btnNew.setOnAction(click -> onNew());
      btnDetails.setOnAction(click -> onDetails());
      btnEdit.setOnAction(click -> onEdit());
      btnHelp.setOnAction(click -> onHelp());
      btnExit.setOnAction(click -> onExit());

      buttonBar.getButtons().add(btnSelect);
      buttonBar.getButtons().add(btnDetails);
      buttonBar.getButtons().add(btnEdit);
      buttonBar.getButtons().add(btnNew);
      buttonBar.getButtons().add(btnDelete);
      buttonBar.getButtons().add(btnHelp);
      buttonBar.getButtons().add(btnExit);

      return buttonBar;
   }


   private void onSelect() {
      if (selectedItems.size() > 0) {
         btnSelect.setDisable(false);
         btnDetails.setDisable(false);
         btnNew.setDisable(false);
         PresentableConfiguration config = selectedItems.get(0);
         if (config.getStandardIndication().equals("Yes")) {
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
         } else {
            btnEdit.setDisable(false);
            btnDelete.setDisable(false);
         }
      } else {
         btnSelect.setDisable(true);
         btnDetails.setDisable(true);
         btnNew.setDisable(true);
         btnEdit.setDisable(true);
         btnDelete.setDisable(true);
      }
   }

   private void onNew() {
      PresentableConfiguration config = selectedItems.get(0);
      long configId = config.getConfigId();
      ConfigNew configNew = new ConfigNewFactory().createConfigNew(api.read((int) configId).get(0)); // TODO use long consistently, also in api

      if (InputStatus.READY == configNew.getInputStatus()) {
         long newConfigId = configNew.getNewConfigId();
         new ConfigEditFactory().createConfigEdit(api.read((int) newConfigId).get(0));
      }
   }

   private void onDetails() {
      PresentableConfiguration config = selectedItems.get(0);
      long configId = config.getConfigId();
      new ConfigDetailsFactory().createConfigDetails(api.read((int) configId).get(0));// TODO use long consistently, also in api
   }

   private void onEdit() {
      PresentableConfiguration config = selectedItems.get(0);
      long configId = config.getConfigId();
      ConfigEdit configEdit = new ConfigEditFactory().createConfigEdit(api.read((int) configId).get(0));// TODO use long consistently, also in api
      if (InputStatus.READY == configEdit.getInputStatus()) {
         showOrReshow();
      }
   }

   private void onHelp() {
      new Help(rosetta.getHelpText("help.configoverview.title"), rosetta.getHelpText("help.configoverview.content"));
   }

   private void onExit() {
      stage.close();
   }

}