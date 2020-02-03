/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Help {

   @FXML
   Label lblHelpTitle;
   @FXML
   Label lblHelpContent;


   public void setTitle(final String title) {
      lblHelpTitle.setText(title);
   }

   public void setContent(final String content) {
      lblHelpContent.setText(content);
   }

}
