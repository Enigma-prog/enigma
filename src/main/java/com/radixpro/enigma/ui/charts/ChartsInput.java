/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.validation.ValidatedChartName;
import com.radixpro.enigma.ui.shared.validation.ValidatedLongitude;
import com.radixpro.enigma.xchg.domain.ChartTypes;
import com.radixpro.enigma.xchg.domain.Ratings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
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
   public TextField localtime;
   @FXML
   public ChoiceBox subject;
   @FXML
   public ChoiceBox rating;
   @FXML
   public ChoiceBox timezone;
   @FXML
   public ChoiceBox eastwest;
   @FXML
   public ChoiceBox northsouth;
   @FXML
   public ChoiceBox calendar;
   @FXML
   public CheckBox dst;


   private ResourceBundle resourceBundle;
   private String textInputDefaultStyle = "-fx-background-radius:5; -fx-background-color:blanchedalmond;";
   private String textInputErrorStyle = "-fx-background-radius:5; -fx-background-color:yellow;";
   private boolean errorsFound;
   private double longitudeInput;
   private double latitudeInput;

   @FXML
   void onCalculate(ActionEvent event) {
      validate();
      if (!errorsFound) {
         saveData();
         // close screen
      }

   }

   public void initialize() {
      resourceBundle = ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale());
      setDynamicStyles();
      initSubject();
      initRating();
      initLatitude();
      initLongitude();
      initCalendar();
   }

   private void setDynamicStyles() {
      name.setStyle(textInputDefaultStyle);
      source.setStyle(textInputDefaultStyle);
      description.setStyle(textInputDefaultStyle);
      locationName.setStyle(textInputDefaultStyle);
      longitudeValue.setStyle(textInputDefaultStyle);
      latitudeValue.setStyle(textInputDefaultStyle);
      date.setStyle(textInputDefaultStyle);
      time.setStyle(textInputDefaultStyle);
      subject.setStyle(textInputDefaultStyle);
      rating.setStyle(textInputDefaultStyle);
      timezone.setStyle(textInputDefaultStyle);
      localtime.setStyle(textInputDefaultStyle);
      eastwest.setStyle(textInputDefaultStyle);
      northsouth.setStyle(textInputDefaultStyle);
      calendar.setStyle(textInputDefaultStyle);
   }

   private void initSubject() {
      ObservableList<String> observableList = ChartTypes.NATAL.getObservableList();
      subject.setItems(observableList);
      subject.getSelectionModel().select(1);  // Natal
   }

   private void initRating() {
      ObservableList<String> observableList = Ratings.ZZ.getObservableList();
      rating.setItems(observableList);
      rating.getSelectionModel().select(0);  // Unknown
   }

   private void initLatitude() {
      var rosetta = Rosetta.getRosetta();
      List<String> latList = new ArrayList<>();
      latList.add(rosetta.getText("ui.shared.direction.east.char"));
      latList.add(rosetta.getText("ui.shared.direction.west.char"));
      ObservableList observableList = FXCollections.observableArrayList(latList);
      eastwest.setItems(observableList);
      eastwest.getSelectionModel().select(0);
   }

   private void initLongitude() {
      var rosetta = Rosetta.getRosetta();
      List<String> longList = new ArrayList<>();
      longList.add(rosetta.getText("ui.shared.direction.north.char"));
      longList.add(rosetta.getText("ui.shared.direction.south.char"));
      ObservableList observableList = FXCollections.observableArrayList(longList);
      northsouth.setItems(observableList);
      northsouth.getSelectionModel().select(0);
   }

   private void initCalendar() {
      var rosetta = Rosetta.getRosetta();
      List<String> calList = new ArrayList<>();
      calList.add(rosetta.getText("ui.shared.calendar.gregorian.char"));
      calList.add(rosetta.getText("ui.shared.calendar.julian.char"));
      ObservableList observableList = FXCollections.observableArrayList(calList);
      calendar.setItems(observableList);
      calendar.getSelectionModel().select(0);
   }

   private void validate() {
      errorsFound = false;
      validateName();
//      validateLongitude();
   }


   private void validateName() {
      var valName = new ValidatedChartName(name.getText());
      name.setText(valName.getNameText());
//      if (valName.isValidated()) name.getStyleClass().add("fixed");
////      else {
////         name.getStyleClass().add("error");
////         errorsFound = true;
////      }
      if (valName.isValidated()) name.setStyle(textInputDefaultStyle);
      else name.setStyle(textInputErrorStyle);
   }

   private void validateLongitude() {
      var valLong = new ValidatedLongitude(longitudeValue.getText());
      if (valLong.isValidated()) {
         longitudeInput = valLong.getValue();
//         longitudeValue.getStyleClass().setAll(defaultStyleClassesTextInput);
      } else {
//         longitudeValue.getStyleClass().setAll(errorStyleClassesTextInput);
         errorsFound = true;
      }
   }

   private void saveData() {
      final String selName = name.getText();
      final String selSubject = (String) subject.getValue();
      final ChartTypes selectedChartType = ChartTypes.UNKNOWN.chartTypeForLocalName(selSubject);
      final String selRating = (String) rating.getValue();
      final Ratings selectedRating = Ratings.ZZ.chartTypeForRatingName(selRating);
      final String sourceDescription = source.getText().trim();
      final String enteredDescription = description.getText().trim();
      final String enteredLocation = locationName.getText().trim();


      System.out.println(selName);
      System.out.println(selectedChartType.name());
      System.out.println(selectedRating.name());
      System.out.println(sourceDescription);
      System.out.println(enteredDescription);
      System.out.println(enteredLocation);
      System.out.println(longitudeInput);

   }

}
