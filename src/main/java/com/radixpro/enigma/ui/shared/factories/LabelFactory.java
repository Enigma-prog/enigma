/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.factories;

import javafx.scene.control.Label;
import lombok.NonNull;
import lombok.val;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for buttons.
 */
public class LabelFactory {

   private LabelFactory() {
      // prevent instantiation
   }

   public static Label createLabel(@NonNull final String text, final double layoutX, final double layoutY,
                                   @NonNull final String styleClass) {
      val label = createLabel(text, layoutX, layoutY);
      label.getStyleClass().add(styleClass);
      return label;
   }

   public static Label createLabel(@NonNull final String text, final double layoutX, final double layoutY) {
      val label = createLabel(text);
      label.setLayoutX(layoutX);
      label.setLayoutY(layoutY);
      return label;
   }


   public static Label createLabel(@NonNull final String text, @NonNull final String styleClass, final double width) {
      val label = createLabel(text, styleClass);
      label.setPrefWidth(width);
      return label;
   }

   public static Label createLabel(final String text, final double width) {
      val label = createLabel(checkNotNull(text));
      label.setPrefWidth(width);
      return label;
   }

   public static Label createLabel(@NonNull final String text, @NonNull final String styleClass) {
      val label = createLabel(text);
      label.getStyleClass().add(styleClass);
      return label;
   }

   public static Label createLabel(@NonNull final String text) {
      val label = new Label();
      label.setText(text);
      return label;
   }

}
