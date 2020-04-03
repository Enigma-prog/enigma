/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.factories;

import javafx.scene.control.Button;
import lombok.NonNull;

/**
 * Factory for buttons.
 */
public class ButtonFactory {

   private ButtonFactory() {
      // prevent instantiation
   }

   public static Button createButton(@NonNull final String text, final boolean disable) {
      Button btn = new Button();
      btn.setMnemonicParsing(false);
      btn.setDisable(disable);
      btn.setText(text);
      return btn;
   }
}
