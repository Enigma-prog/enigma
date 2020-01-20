/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChartsInput {

   @FXML
   public TextField tfieldName;


   @FXML
   void onCalculate(ActionEvent event) throws IOException {
      System.out.println("Name is " + tfieldName.getText());
   }

}
