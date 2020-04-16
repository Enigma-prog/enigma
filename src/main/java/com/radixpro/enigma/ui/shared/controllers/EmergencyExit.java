/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.controllers;

import com.radixpro.enigma.shared.Rosetta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for popup that handles terminating Enigma in case of an unrecoverable error.
 */
public class EmergencyExit {

   @FXML
   Label lblExplanation;
   String explanationTxt = "";

   @FXML
   void onExit() {
      System.exit(0);
   }

   public void initialize() {
      lblExplanation.setText(Rosetta.getRosetta().getText("emergencyexit.intro") + explanationTxt);

   }

   public void setExplanationTxt(String explanationTxt) {
      this.explanationTxt = explanationTxt;
   }
}
