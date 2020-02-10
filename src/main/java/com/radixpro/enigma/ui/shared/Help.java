/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

public class Help {

   @FXML
   Label lblHelpTitle;
   @FXML
   WebView helpText;


   public void setTitle(final String title) {
      lblHelpTitle.setText(title);
   }

   public void setContent(final String content) {
      helpText.getEngine().loadContent(content);
   }

}
