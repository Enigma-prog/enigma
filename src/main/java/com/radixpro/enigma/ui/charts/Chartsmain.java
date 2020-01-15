/*
 * Jan Kampherbeek, (c) 2019.
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


public class Chartsmain {

   @FXML
   String lblChartsTitle;

   @FXML
   void onInputForChart(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsinput.fxml"));
      Parent parent = fxmlLoader.load();
      ChartsInput chartsInput = fxmlLoader.getController();
      // setObservableList om resultaten terug te krijgen

      Scene scene = new Scene(parent, 300, 200);
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
