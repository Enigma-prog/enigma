/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import com.radixpro.enigma.shared.EnigmaDictionary;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.shared.Rosetta;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard {

   @FXML
   public Label version;

   @FXML
   void onCharts() {
      FXMLLoader loader = new FXMLLoader();
      loader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));
      loader.setLocation(getClass().getResource("/fxml/chartsstart.fxml"));
      Parent root = null;
      try {
         root = loader.load();
      } catch (IOException e) {
         new FailFastHandler().terminate("Dashboard cannot start.");
      }
      Scene scene = new Scene(Objects.requireNonNull(root));
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }

   @FXML
   void onExit() {
      Platform.exit();
   }

   public void initialize() {
      version.setText(EnigmaDictionary.VERSION);
   }

}
