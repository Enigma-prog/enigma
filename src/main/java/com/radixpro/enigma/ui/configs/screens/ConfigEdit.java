/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs.screens;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import com.radixpro.enigma.xchg.domain.config.ConfiguredCelObject;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.controlsfx.control.CheckComboBox;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;

/**
 * Edit screen for existing configurations.
 */
public class ConfigEdit {

   private static final Logger LOG = Logger.getLogger(ConfigEdit.class);

   private static final double WIDTH = 500.0;
   private static final double HEIGHT = 600.0;
   private static final double TITLE_HEIGHT = 45.0;
   private static final double SUBTITLE_HEIGHT = 30.0;
   private static final double DATA_TEXT_WIDTH = 150.0;
   private static final double DATA_INPUT_WIDTH = 350.0;

   private final Stage stage;
   private final Rosetta rosetta;
   private final Configuration config;
   private TextField nameInput;
   private TextField descriptionInput;
   private ChoiceBox observerPosSelection;
   private ChoiceBox houseSystemSelection;
   private ChoiceBox eclipticProjSelection;
   private ChoiceBox<String> ayanamshaSelection;
   private CheckComboBox celObjectsSelection;

   public ConfigEdit(final Configuration config, final Stage stage, final Rosetta rosetta) {
      this.rosetta = checkNotNull(rosetta);
      this.config = checkNotNull(config);
      this.stage = checkNotNull(stage);
      showEditableConfiguration();
   }

   private void showEditableConfiguration() {
      stage.setMinHeight(HEIGHT);
      stage.setMinWidth(WIDTH);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(rosetta.getText("ui.configs.edit.title"));
      stage.setScene(new Scene(createVBox()));
      stage.show();
   }

   private VBox createVBox() {
      final VBox vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(WIDTH);
      vBox.setPrefHeight(HEIGHT);
      vBox.getChildren().add(0, createPaneTitle());
      vBox.getChildren().add(1, createPaneSubTitle());
      vBox.getChildren().add(2, createGridPane());
      return vBox;
   }

   private Pane createPaneTitle() {
      final Pane pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.configs.edit.title"), "titletext", WIDTH));
      return pane;
   }

   private Pane createPaneSubTitle() {
      final Pane pane = PaneFactory.createPane(SUBTITLE_HEIGHT, WIDTH, "subtitlepane");
      pane.getChildren().add(LabelFactory.createLabel(config.getName(), "subtitletext", WIDTH));
      return pane;
   }

   private GridPane createGridPane() {
      GridPane gridPane = new GridPane();
      gridPane.setPrefHeight(WIDTH);
      gridPane.setHgap(6.0);
      gridPane.setVgap(6.0);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.name"), DATA_TEXT_WIDTH), 0, 1, 1, 1);
      nameInput = new TextField();
      nameInput.setPrefWidth(DATA_INPUT_WIDTH);
      nameInput.setText(config.getName());
      gridPane.add(nameInput, 1, 1, 1, 1);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.description"), DATA_TEXT_WIDTH), 0, 2, 1, 1);
      descriptionInput = new TextField();
      descriptionInput.setPrefWidth(DATA_INPUT_WIDTH);
      descriptionInput.setText(config.getDescription());
      gridPane.add(descriptionInput, 1, 2, 1, 1);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.housesystem"), DATA_TEXT_WIDTH), 0, 3, 1, 1);
      houseSystemSelection = createHouseSystemChoiceBox();
      gridPane.add(houseSystemSelection, 1, 3, 1, 1);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.observerposition"), DATA_TEXT_WIDTH), 0, 4, 1, 1);
      observerPosSelection = createObserverPosChoiceBox();
      gridPane.add(observerPosSelection, 1, 4, 1, 1);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.eclipticprojection"), DATA_TEXT_WIDTH), 0, 5, 1, 1);
      eclipticProjSelection = createEclipticProjChoiceBox();
      gridPane.add(eclipticProjSelection, 1, 5, 1, 1);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.ayanamsha"), DATA_TEXT_WIDTH), 0, 6, 1, 1);
      ayanamshaSelection = createAyanamshaSelection();
      gridPane.add(ayanamshaSelection, 1, 6, 1, 1);
      gridPane.add(LabelFactory.createLabel(rosetta.getText("ui.general.celobjects"), DATA_TEXT_WIDTH), 0, 7, 1, 1);
      celObjectsSelection = createCelObjectComboBox();
      gridPane.add(celObjectsSelection, 1, 7, 1, 1);


      onEclipticChange();
      return gridPane;
   }

   private ChoiceBox createHouseSystemChoiceBox() {
      ChoiceBox choiceBox = new ChoiceBox();
      choiceBox.setPrefWidth(DATA_INPUT_WIDTH);
      ObservableList<String> houses = HouseSystems.UNKNOWN.getObservableList();
      choiceBox.setItems(houses);
      choiceBox.getSelectionModel().select(config.getAstronConfiguration().getHouseSystem().getId());
      return choiceBox;
   }

   private ChoiceBox createObserverPosChoiceBox() {
      ChoiceBox choiceBox = new ChoiceBox();
      choiceBox.setPrefWidth(DATA_INPUT_WIDTH);
      ObservableList<String> observerPositions = ObserverPositions.UNKNOWN.getObservableList();
      choiceBox.setItems(observerPositions);
      choiceBox.getSelectionModel().select(config.getAstronConfiguration().getObserverPosition().getId());
      return choiceBox;
   }

   private ChoiceBox createEclipticProjChoiceBox() {
      ChoiceBox choiceBox = new ChoiceBox();
      choiceBox.setPrefWidth(DATA_INPUT_WIDTH);
      ObservableList<String> eclipticProjections = EclipticProjections.UNKNOWN.getObservableList();
      choiceBox.setItems(eclipticProjections);
      choiceBox.getSelectionModel().select(config.getAstronConfiguration().getEclipticProjection().getId());
      choiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, newValue) -> onEclipticChange());
      return choiceBox;
   }

   private ChoiceBox createAyanamshaSelection() {
      ChoiceBox<String> choiceBox = new ChoiceBox(Ayanamshas.NONE.getObservableList());
      choiceBox.setPrefWidth(DATA_INPUT_WIDTH);
      choiceBox.getSelectionModel().select(config.getAstronConfiguration().getAyanamsha().getId());
      return choiceBox;
   }


   private CheckComboBox createCelObjectComboBox() {
      CheckComboBox checkComboBox = new CheckComboBox();
      checkComboBox.setPrefWidth(DATA_INPUT_WIDTH);
      ObservableList<String> celObjects = CelestialObjects.SUN.getObservableList();
      for (String name : celObjects) {
         checkComboBox.getItems().add(name);
      }
      for (ConfiguredCelObject celestialObjec : config.getAstronConfiguration().getCelObjects()) {
         checkComboBox.getCheckModel().check(celestialObjec.getCelObject().getId());
      }
      return checkComboBox;
   }

   private void onEclipticChange() {
      if (eclipticProjSelection.getSelectionModel().getSelectedIndex() == 2) ayanamshaSelection.setDisable(false);
      else ayanamshaSelection.setDisable(true);
   }

}
