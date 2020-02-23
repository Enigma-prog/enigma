/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.controllers;

import com.radixpro.enigma.shared.Rosetta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChartsData {

   @FXML
   Label lblTitle;


   public void initialize() {
      populate();
   }

   public void populate() {
      final String titlePrefix = Rosetta.getRosetta().getText("ui.charts.data.pagetitleprefix");
      lblTitle.setText(titlePrefix + " " + "Jan");   // todo add name and other metadata to FullChart and use correct name


   }

}
