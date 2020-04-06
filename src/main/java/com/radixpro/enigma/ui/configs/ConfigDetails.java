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
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.domain.CelObjectCategory;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import com.radixpro.enigma.xchg.domain.config.ConfiguredAspect;
import com.radixpro.enigma.xchg.domain.config.ConfiguredCelObject;
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
import lombok.val;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
   private Rosetta rosetta;
   private Configuration configuration;
   private Button btnExit;
   private Button btnHelp;

   public ConfigDetails(final long idConfig) {
      defineConfiguration(idConfig);
      stage = new Stage();
      rosetta = Rosetta.getRosetta();
      showDetails();
   }

   private void defineConfiguration(final long idConfig) {
      PersistedConfigurationApi api = new PersistedConfigurationApi();
      configuration = api.read((int) idConfig).get(0);
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
      val vBox = new VBox();
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
      val pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(LabelFactory.createLabel(rosetta.getText("ui.configs.overview.title"), "titletext", WIDTH));
      return pane;
   }

   private Pane createPaneSubTitle() {
      val pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "subtitlepane");
      pane.getChildren().add(LabelFactory.createLabel(configuration.getName(), "subtitletext", WIDTH));
      return pane;
   }

   private Pane createPaneData() {
      val pane = PaneFactory.createPane(TV_HEIGHT, WIDTH);
      pane.getChildren().add(createTableView());
      return pane;
   }

   private Pane createPaneSeparator() {
      return PaneFactory.createPane(SEPARATOR_HEIGHT, WIDTH);
   }

   private Pane createPaneBtns() {
      val pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH);
      pane.getChildren().add(createButtonBar());
      return pane;
   }

   private TableView<PresentableProperty> createTableView() {
      val tableView = new TableView<PresentableProperty>();
      tableView.setPrefHeight(TV_HEIGHT);
      tableView.setPrefWidth(WIDTH);
      TableColumn propertyColumn = new TableColumn<PresentableProperty, String>("Property");
      propertyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      TableColumn valueColumn = new TableColumn<PresentableProperty, String>("Value");
      valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
      tableView.getColumns().add(propertyColumn);
      tableView.getColumns().add(valueColumn);
      List<PresentableProperty> properties = createProperties();
      for (PresentableProperty prop : properties) {
         tableView.getItems().add(prop);
      }
      return tableView;
   }


   private ButtonBar createButtonBar() {
      val buttonBar = new ButtonBar();
      btnHelp = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.help"), false);
      btnExit = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.exit"), false);

//      btnHelp.setOnAction(click -> onHelp());

      buttonBar.getButtons().add(btnHelp);
      buttonBar.getButtons().add(btnExit);

      return buttonBar;
   }

   private List<PresentableProperty> createProperties() {
      List<PresentableProperty> properties = new ArrayList<>();
      properties.add(new PresentableProperty("Name", configuration.getName()));
      properties.add(new PresentableProperty("Description", configuration.getDescription()));
      properties.add(new PresentableProperty("Housesystem", rosetta.getText(configuration.getAstronConfiguration().getHouseSystem().getNameForRB())));
      properties.add(new PresentableProperty("Ayanamsha", rosetta.getText(configuration.getAstronConfiguration().getAyanamsha().getNameForRB())));
      properties.add(new PresentableProperty("Ecliptic projection", rosetta.getText(configuration.getAstronConfiguration().getEclipticProjection().getNameForRB())));
      properties.add(new PresentableProperty("Observer position", rosetta.getText(configuration.getAstronConfiguration().getObserverPosition().getNameForRB())));

      List<ConfiguredCelObject> celObjects = configuration.getAstronConfiguration().getCelObjects();
      val classicCelObjectsAsText = new StringBuilder();
      val modernCelObjectsAsText = new StringBuilder();
      val extraplutCelObjectsAsText = new StringBuilder();
      val asteroidCelObjectsAsText = new StringBuilder();
      val centaurCelObjectsAsText = new StringBuilder();
      val intersectionsCelObjectsAsText = new StringBuilder();
      val hypothetsCelObjectsAsText = new StringBuilder();

      int category;
      String nameText;
      for (ConfiguredCelObject celObject : celObjects) {
         category = celObject.getCelObject().getCategory().getId();
         nameText = rosetta.getText(celObject.getCelObject().getNameForRB()) + " ";
         switch (category) {
            case 1:
               classicCelObjectsAsText.append(nameText);
               break;
            case 2:
               modernCelObjectsAsText.append(nameText);
               break;
            case 3:
               extraplutCelObjectsAsText.append(nameText);
               break;
            case 4:
               asteroidCelObjectsAsText.append(nameText);
               break;
            case 5:
               centaurCelObjectsAsText.append(nameText);
               break;
            case 6:
               intersectionsCelObjectsAsText.append(nameText);
               break;
            case 7:
               hypothetsCelObjectsAsText.append(nameText);
               break;
            default:
               LOG.error("Invalid category for celestial body while showing details of configuration." +
                     "Received category with Id : " + category + ". Celestial object was not shown.");
         }
      }
      properties.add(new PresentableProperty("Celestial objects and points", ""));
      if (classicCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.CLASSICS.getNameForRB()), classicCelObjectsAsText.toString()));
      if (modernCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.MODERN.getNameForRB()), modernCelObjectsAsText.toString()));
      if (extraplutCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.EXTRA_PLUT.getNameForRB()), extraplutCelObjectsAsText.toString()));
      if (asteroidCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.ASTEROIDS.getNameForRB()), asteroidCelObjectsAsText.toString()));
      if (centaurCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.CENTAURS.getNameForRB()), centaurCelObjectsAsText.toString()));
      if (intersectionsCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.INTERSECTIONS.getNameForRB()), intersectionsCelObjectsAsText.toString()));
      if (hypothetsCelObjectsAsText.length() > 0) properties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.HYPOTHETS.getNameForRB()), hypothetsCelObjectsAsText.toString()));
      // TODO create rbName for AspectOrbStructure
      properties.add(new PresentableProperty("Aspect orb structure", configuration.getDelinConfiguration().getAspectConfiguration().getOrbStructure().name()));
      properties.add(new PresentableProperty("Aspect base orb", Double.toString(configuration.getDelinConfiguration().getAspectConfiguration().getBaseOrb())));
      properties.add(new PresentableProperty("Draw ingoing/outgoing", configuration.getDelinConfiguration().getAspectConfiguration().isDrawInOutGoing() ? "Yes" : "No"));

      // add aspects
      List<ConfiguredAspect> aspects = configuration.getDelinConfiguration().getAspectConfiguration().getAspects();
      val majorAspectsAsText = new StringBuilder();
      val minorAspectsAsText = new StringBuilder();
      val microAspectsAsText = new StringBuilder();
      // TODO add equatorial aspects as category
      for (ConfiguredAspect aspect : aspects) {
         category = aspect.getAspect().getImportance();
         nameText = rosetta.getText(aspect.getAspect().getFullRbId()) + " ";
         switch (category) {
            case 1:
               majorAspectsAsText.append(nameText);
               break;
            case 2:
               minorAspectsAsText.append(nameText);
               break;
            case 3:
               microAspectsAsText.append(nameText);
               break;
            default:
               LOG.error("Invalid category for aspect body while showing details of configuration." +
                     "Received category with Id : " + category + ". Aspect was not shown.");
         }
      }
      properties.add(new PresentableProperty("Aspects", ""));
      if (majorAspectsAsText.length() > 0) properties.add(new PresentableProperty(
            "Major aspects", majorAspectsAsText.toString()));
      if (minorAspectsAsText.length() > 0) properties.add(new PresentableProperty(
            "Minor aspects", minorAspectsAsText.toString()));
      if (microAspectsAsText.length() > 0) properties.add(new PresentableProperty(
            "Micro aspects", microAspectsAsText.toString()));

      return properties;
   }

}
