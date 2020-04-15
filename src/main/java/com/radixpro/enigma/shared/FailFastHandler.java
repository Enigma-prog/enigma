/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

import com.radixpro.enigma.ui.shared.controllers.EmergencyExit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Is called in case of a unrecoverable error. Shows a message and gives only the option to terminate the application.
 */
public class FailFastHandler {

   private static final Logger LOG = Logger.getLogger(FailFastHandler.class);

   public void terminate(String causeOfError) {
      if (null == causeOfError || causeOfError.isEmpty()) causeOfError = "No cause of error mentioned.";
      LOG.info("Showing termination message as a unrecoverable error occurred. Cause of error: " + causeOfError);
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/emergencyexit.fxml"));
      loader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));
      Parent parent;
      try {
         parent = loader.load();
         EmergencyExit emergencyExit = loader.getController();
         emergencyExit.setExplanationTxt(causeOfError);
         emergencyExit.initialize();
         Scene scene = new Scene(parent, 600, 400);
         Stage stage = new Stage();
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.setScene(scene);
         stage.showAndWait();
      } catch (IOException e) {
         LOG.error("Could not start the termination popup : " + e.getMessage());
      }
   }

}

