/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Dashboard {

   @FXML
   void onCharts(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader();
      loader.setResources(ResourceBundle.getBundle("rb/texts", new Locale("du", "DU")));
      loader.setLocation(getClass().getResource("/fxml/chartsstart.fxml"));  // staat in resources
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
   void onExit(ActionEvent event) throws IOException {
      Platform.exit();
   }

   public void initialize() {
      // CheckVersion      // TODO
   }

}
