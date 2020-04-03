/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.factories.ButtonFactory;
import com.radixpro.enigma.ui.shared.factories.LabelFactory;
import com.radixpro.enigma.ui.shared.factories.PaneFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.val;

import static com.radixpro.enigma.ui.shared.StyleDictionary.STYLESHEET;

/**
 * Display of help text. Handles all help texts.
 */
public class Help {

   private static final double WIDTH = 530.0;
   private static final double HEIGHT = 600.0;
   private static final double TITLE_HEIGHT = 80.0;
   private static final String PREFIX = "<div style=\"padding:10px; font-family: sans-serif, Arial;\">";
   private static final String POSTFIX = "</div>";
   private static final double BTN_PANE_HEIGHT = 30.0;
   private static final double CONTENT_HEIGHT = 490.0;
   private final String title;
   private final String content;
   private Stage stage;

   /**
    * Define title and content of the help page.
    *
    * @param title   Title of the help page.
    * @param content Textual content of the help page.
    */
   public Help(@NonNull final String title, @NonNull final String content) {
      this.title = title;
      this.content = content;
      showContent();
   }

   private void showContent() {
      stage = new Stage();
      stage.setMinHeight(HEIGHT);
      stage.setMinWidth(WIDTH);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(Rosetta.getRosetta().getText("ui.helptitle"));
      stage.setScene(new Scene(createVBox()));
      stage.show();
   }

   public void onClose() {
      stage.close();
   }

   private Label createLblHelpTitle() {
      return LabelFactory.createLabel(title, "titletext");
   }

   private Pane createTitlePane() {
      val pane = PaneFactory.createPane(TITLE_HEIGHT, WIDTH, "titlepane");
      pane.getChildren().add(createLblHelpTitle());
      return pane;
   }

   private Pane createContentPane() {
      val pane = PaneFactory.createPane(CONTENT_HEIGHT, WIDTH);
      pane.getChildren().add(createContentWebView());
      return pane;
   }

   private Pane createButtonPane() {
      val pane = PaneFactory.createPane(BTN_PANE_HEIGHT, WIDTH, "helppane");
      pane.getChildren().add(createCloseButton());
      return pane;
   }

   private WebView createContentWebView() {
      val webView = new WebView();
      val webEngine = webView.getEngine();
      String fullContent = PREFIX + content + POSTFIX;
      webView.setPrefWidth(WIDTH);
      webView.setPrefHeight(CONTENT_HEIGHT);
      webEngine.loadContent(fullContent, "text/html");
      return webView;
   }

   private Button createCloseButton() {
      val button = ButtonFactory.createButton("ui.shared.btn.exit", false);
      button.setOnAction(click -> onClose());
      return button;
   }

   private VBox createVBox() {
      val vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(WIDTH);
      vBox.setPrefHeight(HEIGHT);
      vBox.getChildren().add(0, createTitlePane());
      vBox.getChildren().add(1, createContentPane());
      vBox.getChildren().add(2, createButtonPane());
      return vBox;
   }

}
