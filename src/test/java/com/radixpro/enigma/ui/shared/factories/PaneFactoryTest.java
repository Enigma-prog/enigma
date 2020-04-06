/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.factories;

import com.radixpro.enigma.testsupport.JfxTestRunner;
import javafx.scene.layout.Pane;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.radixpro.enigma.testsupport.TextConstants.DELTA_8_POS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JfxTestRunner.class)
public class PaneFactoryTest {

   private final double height = 220.5;
   private final double width = 111.1;
   private final String styleClass = "paneStyle";

   @Test
   public void createPaneHeightWidthStyle() {
      Object result = PaneFactory.createPane(height, width, styleClass);
      assertTrue(result instanceof Pane);
      Pane paneResult = (Pane) result;
      assertEquals(height, paneResult.getPrefHeight(), DELTA_8_POS);
      assertEquals(width, paneResult.getPrefWidth(), DELTA_8_POS);
      assertTrue(paneResult.getStyleClass().contains(styleClass));
   }

   @Test
   public void testCreatePaneHeightWidth() {
      Object result = PaneFactory.createPane(height, width);
      assertTrue(result instanceof Pane);
      Pane paneResult = (Pane) result;
      assertEquals(height, paneResult.getPrefHeight(), DELTA_8_POS);
      assertEquals(width, paneResult.getPrefWidth(), DELTA_8_POS);
   }
}