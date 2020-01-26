/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class App extends Application {
   private final static Logger LOG = Logger.getLogger(Application.class);
   private Stage primaryStage;

   public static void main(String[] args) {
      launch(args);
   }


   @Override
   public void start(Stage primaryStage) {
      LOG.info("Started Enigma.");
      this.primaryStage = primaryStage;
      showDashboard();
   }

   private void showDashboard() {
      FXMLLoader loader = new FXMLLoader();
      loader.setResources(ResourceBundle.getBundle("rb/texts", new Locale("du", "DU")));
      loader.setLocation(getClass().getResource("/fxml/dashboard.fxml"));  // staat in resources
      Parent root = null;
      try {
         root = loader.load();
      } catch (IOException e) {
         LOG.error("Could not start the root app. Exception : " + e.getMessage());
      }
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
   }


   private void showChartStart() {
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
      primaryStage.setTitle("Enigma version 2020.1");
      primaryStage.setScene(scene);
      primaryStage.show();
   }




}
