/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

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

public class ChartsStart {

   @FXML
   String lblTitle;

   @FXML
   void onNewChart(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsinput.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle("rb/texts", new Locale("du", "DU")));

      Parent parent = fxmlLoader.load();
      ChartsInput chartsInput = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }


   public void initialize() {
      // Initialization code can go here.
      // The parameters url and resources can be omitted if they are not needed

   }


}
