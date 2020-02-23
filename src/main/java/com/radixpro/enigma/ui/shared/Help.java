/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Help {

   @FXML
   Label lblHelpTitle;
   @FXML
   WebView helpText;
   private static final String PREFIX = "<div style=\"padding:10px; font-family: sans-serif, Arial;\">";
   private static final String POSTFIX = "</div>";
   @FXML
   Button closeBtn;

   @FXML
   public void onClose() {
      Stage stage = (Stage) closeBtn.getScene().getWindow();
      stage.close();
   }

   public void setTitle(final String title) {
      lblHelpTitle.setText(title);
   }

   public void setContent(final String content) {
      helpText.getEngine().loadContent(PREFIX + content + POSTFIX);
   }

}
