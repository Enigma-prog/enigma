/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.xchg.domain.ChartTypes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChartsInput {

   @FXML
   public TextField name;
   @FXML
   public TextField source;
   @FXML
   public TextField description;
   @FXML
   public TextField locationName;
   @FXML
   public TextField longitudeValue;
   @FXML
   public TextField latitudeValue;
   @FXML
   public TextField date;
   @FXML
   public TextField time;
   @FXML
   public ChoiceBox subject;
   @FXML
   public ChoiceBox rating;


   @FXML
   void onCalculate(ActionEvent event) throws IOException {
      System.out.println("Name is " + name.getText());
   }

   public void initialize() {
      ResourceBundle mybundle = ResourceBundle.getBundle("rb/texts", new Locale("du", "DU"));
      ChartTypes defaultChartType = ChartTypes.UNKNOWN;
      ObservableList<ChartTypes> observableList = defaultChartType.getObservableList();
      subject.setItems(observableList);
   }

}
