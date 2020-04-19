/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import com.radixpro.enigma.shared.EnigmaDictionary;
import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.charts.screens.ChartsStart;
import com.radixpro.enigma.ui.shared.factories.ButtonFactory;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;

public class Dashboard {

   private Stage stage;
   private Rosetta rosetta;

   public Dashboard() {
      stage = new Stage();
      rosetta = Rosetta.getRosetta();
      showDashboard();

   }

   void onCharts() {
      new ChartsStart(new Stage(), rosetta);
   }


   void onExit() {
      Platform.exit();
   }

   private void showDashboard() {

      ButtonBar buttonBar = createButtonBar();
      ImageView imageView = createImage();

      Label lblInstruct = LabelFactory.createLabel(rosetta.getText("ui.db.instruct"));
      Label lblDescription = LabelFactory.createLabel(rosetta.getText("ui.db.describe") + ": " + EnigmaDictionary.VERSION,
            20.0, 28.0, "descriptiontext");
      Label lblTitle = LabelFactory.createLabel(rosetta.getText("ui.db.title"), 247.0, 9.0, "titletext");
      Pane titlePane = PaneFactory.createPane(57.0, 620.0, "titlepane");
      Pane descriptionPane = PaneFactory.createPane(185.0, 120.0, "descriptionpane");

      titlePane.getChildren().add(lblTitle);
      descriptionPane.getChildren().add(lblDescription);


      BorderPane.setAlignment(titlePane, Pos.CENTER);
      BorderPane.setAlignment(descriptionPane, Pos.CENTER);
      BorderPane.setAlignment(lblInstruct, Pos.CENTER_LEFT);
      BorderPane.setAlignment(imageView, Pos.CENTER);
      BorderPane.setAlignment(buttonBar, Pos.CENTER);

      BorderPane borderPane = new BorderPane();
      borderPane.setPrefHeight(250.0);
      borderPane.setPrefWidth(620.0);
      borderPane.getStylesheets().add(STYLESHEET);
      borderPane.setTop(titlePane);
      borderPane.setLeft(imageView);
      borderPane.setRight(descriptionPane);
      borderPane.setCenter(lblInstruct);
      borderPane.setBottom(buttonBar);


      stage = new Stage();
      stage.setMinHeight(250.0);
      stage.setMinWidth(620.0);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(rosetta.getText("ui.helptitle"));
      stage.setScene(new Scene(borderPane));
      stage.show();
   }


   private ButtonBar createButtonBar() {
      ButtonBar buttonBar = new ButtonBar();
      buttonBar.setPrefWidth(600.0);

      Button btnCharts = ButtonFactory.createButton(rosetta.getText("ui.db.btn.charts"), false);
      Button btnPeriods = ButtonFactory.createButton(rosetta.getText("ui.db.btn.periods"), true);
      Button btnStats = ButtonFactory.createButton(rosetta.getText("ui.db.btn.stats"), true);
      Button btnTools = ButtonFactory.createButton(rosetta.getText("ui.db.btn.tools"), true);
      Button btnExit = ButtonFactory.createButton(rosetta.getText("ui.shared.btn.exit"), false);

      btnCharts.setOnAction(click -> onCharts());
      btnExit.setOnAction(click -> onExit());

      buttonBar.getButtons().add(btnCharts);
      buttonBar.getButtons().add(btnPeriods);
      buttonBar.getButtons().add(btnStats);
      buttonBar.getButtons().add(btnTools);
      buttonBar.getButtons().add(btnExit);

      return buttonBar;
   }

   private ImageView createImage() {
      Image image = new Image("img/ziggurat.png");
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(223.0);
      imageView.setFitHeight(86.0);
      imageView.setPickOnBounds(true);
      imageView.setPreserveRatio(true);
      return imageView;
   }

}
