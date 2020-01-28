/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.validation.ValidatedChartName;
import com.radixpro.enigma.xchg.domain.ChartTypes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
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
   public CheckComboBox rating;
   private ResourceBundle resourceBundle;
   private boolean errorsFound;

   @FXML
   void onCalculate(ActionEvent event) throws IOException {
      validate();
      if (!errorsFound) {
         saveData();
         // close screen
      }

   }

   public void initialize() {
      resourceBundle = ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale());
      initSubject();
      initRating();
   }

   private void initSubject() {
      ObservableList<String> observableList = ChartTypes.NATAL.getObservableList();
      subject.setItems(observableList);
      subject.getSelectionModel().select(1);  // Natal
   }

   private void initRating() {
//      ObservableList<Ratings> observableList = Rati
   }


   private void validate() {
      errorsFound = false;
      validateName();
   }


   private void validateName() {
      ValidatedChartName valName = new ValidatedChartName(name.getText());
      name.setText(valName.getNameText());
      if (valName.isValidated()) name.getStyleClass().add("fixed");
      else {
         name.getStyleClass().add("error");
         errorsFound = true;
      }
   }

   private void saveData() {
      String selectedSubject = (String) subject.getValue();
      ChartTypes selectedChartType = ChartTypes.UNKNOWN.chartTypeForLocalName(selectedSubject);
      System.out.println(selectedChartType.name());
   }

}
