/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import com.radixpro.enigma.shared.EnigmaDictionary;
import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.xchg.api.VersionApi;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class Dashboard {

   @FXML
   public Label version;
   @FXML
   public Button btnCharts;

   @FXML
   void onCharts(ActionEvent event) {
      FXMLLoader loader = new FXMLLoader();
      loader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));
      loader.setLocation(getClass().getResource("/fxml/chartsstart.fxml"));
      Parent root = null;
      try {
         root = loader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }

   @FXML
   void onExit(ActionEvent event) {
      Platform.exit();
   }

   public void initialize() {
      new VersionApi().checkAndUpdate();
      version.setText(EnigmaDictionary.VERSION);
   }

}
