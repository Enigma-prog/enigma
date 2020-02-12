/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.configs.ConfigEdit;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class ChartsStart {

   @FXML
   String lblTitle;
   @FXML
   MenuItem menuConfigEdit;
   @FXML
   Button btnNewChart;

   private Configuration currentConfig;


   @FXML
   void onNewChart(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chartsinput.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));

      Parent parent = fxmlLoader.load();
      ChartsInput chartsInput = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }

   @FXML
   void onConfigEdit(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/configedit.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));

      Parent parent = fxmlLoader.load();
      ConfigEdit configEdit = fxmlLoader.getController();
      Scene scene = new Scene(parent, 600, 800);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }

   public void initialize() {
      defineConfig();
   }

   private void defineConfig() {
      PersistedPropertyApi propApi = new PersistedPropertyApi();
      int currentConfigId = Integer.parseInt(propApi.read("config").get(0).getValue());
      PersistedConfigurationApi confApi = new PersistedConfigurationApi();
      currentConfig = confApi.read(currentConfigId).get(0);
      System.out.println(currentConfig.getName());
   }

}
