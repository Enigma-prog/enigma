/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.xchg.domain.ChartTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;

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
   public CheckComboBox subject;
   @FXML
   public CheckComboBox rating;


   @FXML
   void onCalculate(ActionEvent event) throws IOException {
      System.out.println("Name is " + name.getText());
   }

   public void initialize() {

      subject.getItems().add(ChartTypes.UNKNOWN.name());
      subject.getItems().add(ChartTypes.NATAL.name());

   }

}
