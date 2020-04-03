/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.factories;

import javafx.scene.layout.Pane;
import lombok.NonNull;

/**
 * Factory for Panes.
 */
public class PaneFactory {

   private PaneFactory() {
      // prevent instantiation
   }

   public static Pane createPane(final double height, final double width, @NonNull final String styleClass) {
      Pane pane = createPane(height, width);
      pane.getStyleClass().add(styleClass);
      return pane;
   }

   public static Pane createPane(final double height, final double width) {
      Pane pane = new Pane();
      pane.setPrefWidth(width);
      pane.setPrefHeight(height);
      return pane;
   }

}
