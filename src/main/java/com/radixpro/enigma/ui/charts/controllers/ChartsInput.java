/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.controllers;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.Help;
import com.radixpro.enigma.ui.shared.InputStatus;
import com.radixpro.enigma.ui.shared.validation.*;
import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.val;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.radixpro.enigma.ui.shared.StyleDictionary.INPUT_DEFAULT_STYLE;
import static com.radixpro.enigma.ui.shared.StyleDictionary.INPUT_ERROR_STYLE;

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
   public Label lbllocaltime;
   @FXML
   public ChoiceBox<String> subject;
   @FXML
   public ChoiceBox<String> eastwest;
   @FXML
   public ChoiceBox<String> northsouth;
   @FXML
   public ChoiceBox<String> calendar;
   @FXML
   public ChoiceBox<String> localeastwest;
   @FXML
   public CheckBox cbDst;
   @FXML
   public Button calculatebtn;
   @FXML
   public Button cancelBtn;
   @FXML
   public ChoiceBox<String> rating;
   @FXML
   public ChoiceBox<String> timezone;

   private boolean timeZoneLocalSelected = false;
   private long newChartId;
   private ValidatedLongitude valLong;
   private ValidatedLongitude valLongLocalTime;
   private ValidatedLatitude valLat;
   private ValidatedChartName valChartName;
   private ValidatedDate valDate;
   private ValidatedTime valTime;
   private InputStatus inputStatus = InputStatus.INCOMPLETE;

   @FXML
   void onCalculate() {
      newChartId = saveData();
      Stage stage = (Stage) calculatebtn.getScene().getWindow();
      stage.close();
   }

   @FXML
   void onCancel() {
      inputStatus = InputStatus.CANCELLED;
      Stage stage = (Stage) cancelBtn.getScene().getWindow();
      stage.close();
   }

   @FXML
   void onHelp() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/help.fxml"));
      fxmlLoader.setResources(ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale()));
      Parent parent = fxmlLoader.load();
      Help help = fxmlLoader.getController();
      help.setTitle(Rosetta.getRosetta().getHelpText("help.chartsinput.title"));
      help.setContent(Rosetta.getRosetta().getHelpText("help.chartsinput.content"));
      Scene scene = new Scene(parent, 530, 600);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
   }

   public InputStatus getInputStatus() {
      return inputStatus;
   }

   public long getNewChartId() {
      return newChartId;
   }

   public void initialize() {
      initSubject();
      initRating();
      initLatitude();
      initLongitude();
      initCalendar();
      initTimeZone();
      initLocalEastWest();
      defineListeners();
   }

   private void defineListeners() {
      name.textProperty().addListener((observable, oldValue, newValue) -> validateName(newValue));
      longitudeValue.textProperty().addListener((observable, oldValue, newValue) -> validateLongitude(newValue));
      latitudeValue.textProperty().addListener((observable, oldValue, newValue) -> validateLatitude(newValue));
      date.textProperty().addListener((observable, oldValue, newValue) -> validateDate(newValue));
      time.textProperty().addListener((observable, oldValue, newValue) -> validateTime(newValue));
      timezone.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            checkTimeZones(newValue));
      localtime.textProperty().addListener((observable, oldValue, newValue) -> validateLocalTime(newValue));

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
      latList.add(rosetta.getText("ui.shared.direction.north.char"));
      latList.add(rosetta.getText("ui.shared.direction.south.char"));
      var observableList = FXCollections.observableArrayList(latList);
      northsouth.setItems(observableList);
      northsouth.getSelectionModel().select(0);
   }

   private void initLongitude() {
      var rosetta = Rosetta.getRosetta();
      List<String> longList = new ArrayList<>();
      longList.add(rosetta.getText("ui.shared.direction.east.char"));
      longList.add(rosetta.getText("ui.shared.direction.west.char"));
      var observableList = FXCollections.observableArrayList(longList);
      eastwest.setItems(observableList);
      eastwest.getSelectionModel().select(0);
   }

   private void initCalendar() {
      var rosetta = Rosetta.getRosetta();
      List<String> calList = new ArrayList<>();
      calList.add(rosetta.getText("ui.shared.calendar.gregorian.char"));
      calList.add(rosetta.getText("ui.shared.calendar.julian.char"));
      var observableList = FXCollections.observableArrayList(calList);
      calendar.setItems(observableList);
      calendar.getSelectionModel().select(0);
   }

   private void initTimeZone() {
      var observableList = TimeZones.UT.getObservableList();
      timezone.setItems(observableList);
      timezone.getSelectionModel().select(1);  // UT
   }

   private void initLocalEastWest() {
      var rosetta = Rosetta.getRosetta();
      List<String> longList = new ArrayList<>();
      longList.add(rosetta.getText("ui.shared.direction.east.char"));
      longList.add(rosetta.getText("ui.shared.direction.west.char"));
      var observableList = FXCollections.observableArrayList(longList);
      localeastwest.setItems(observableList);
      localeastwest.getSelectionModel().select(0);
   }

   private void validateName(final String newName) {
      valChartName = new ValidatedChartName(newName);
      name.setText(valChartName.getNameText());
      name.setStyle(valChartName.isValidated() ? INPUT_DEFAULT_STYLE : INPUT_ERROR_STYLE);  //textinput
      checkStatus();
   }

   private void validateLongitude(final String newLongitude) {
      valLong = new ValidatedLongitude(newLongitude);
      longitudeValue.setStyle(valLong.isValidated() ? INPUT_DEFAULT_STYLE : INPUT_ERROR_STYLE);
      checkStatus();
   }

   private void validateLocalTime(final String newLocalTime) {
      valLongLocalTime = new ValidatedLongitude(newLocalTime);
      localtime.setStyle(valLongLocalTime.isValidated() ? INPUT_DEFAULT_STYLE : INPUT_ERROR_STYLE);
      checkStatus();
   }

   private void validateLatitude(final String newLatitude) {
      valLat = new ValidatedLatitude(newLatitude);
      latitudeValue.setStyle(valLat.isValidated() ? INPUT_DEFAULT_STYLE : INPUT_ERROR_STYLE);
      checkStatus();
   }

   private void validateDate(final String newDate) {
      valDate = new ValidatedDate(newDate + '/' + calendar.getValue());
      date.setStyle(valDate.isValidated() ? INPUT_DEFAULT_STYLE : INPUT_ERROR_STYLE);
      checkStatus();
   }

   private void validateTime(final String newTime) {
      valTime = new ValidatedTime(newTime);
      time.setStyle(valTime.isValidated() ? INPUT_DEFAULT_STYLE : INPUT_ERROR_STYLE);
      checkStatus();
   }

   private void checkTimeZones(final String newValue) {
      val selected = TimeZones.UT.timeZoneForName(newValue);
      if (selected == TimeZones.LMT) {
         localtime.setEditable(true);
         localtime.setDisable(false);
         localtime.setFocusTraversable(true);
         lbllocaltime.setDisable(false);
         localeastwest.setDisable(false);
         localeastwest.setFocusTraversable(true);
         timeZoneLocalSelected = true;
      } else {
         localtime.setEditable(false);
         localtime.setDisable(true);
         localtime.setFocusTraversable(false);
         lbllocaltime.setDisable(true);
         localeastwest.setDisable(true);
         localeastwest.setFocusTraversable(false);
         timeZoneLocalSelected = false;
      }
      checkStatus();
   }

   private void checkStatus() {
      val inputOk = (valChartName != null && valChartName.isValidated()
            && valLat != null && valLat.isValidated()
            && valLong != null && valLong.isValidated()
            && valDate != null && valDate.isValidated()
            && valTime != null && valTime.isValidated()
            && ((valLongLocalTime != null && valLongLocalTime.isValidated()) || !timeZoneLocalSelected));
      calculatebtn.setDisable(!inputOk);
      calculatebtn.setFocusTraversable(inputOk);
      if (inputOk) inputStatus = InputStatus.READY;
   }

   private long saveData() {
      val api = new PersistedChartDataApi();
      long chartId = api.getMaxId() + 1;
      val chartData = new ChartData(chartId, constructFullDateTime(), constructLocation(), constructMetaData());
      api.insert(chartData);
      return chartId;
   }

   private ChartMetaData constructMetaData() {
      val inputName = name.getText();
      val inputDescription = description.getText().trim();
      val inputSource = source.getText().trim();
      val inputRating = Ratings.ZZ.ratingForName(rating.getValue());
      val inputChartType = ChartTypes.UNKNOWN.chartTypeForLocalName(subject.getValue());
      return new ChartMetaData(inputName, inputDescription, inputSource, inputChartType, inputRating);
   }

   private Location constructLocation() {
      val enteredLocation = locationName.getText().trim();
      String longDir = northsouth.getValue();
      if (longDir.equalsIgnoreCase("O")) longDir = "E";
      val longitudeCoordinate = new GeographicCoordinate(valLong.getDegrees(), valLong.getMinutes(),
            valLong.getSeconds(), longDir, valLong.getValue());
      String latDir = eastwest.getValue();
      if (latDir.equalsIgnoreCase("Z")) latDir = "S";
      val latitudeCoordinate = new GeographicCoordinate(valLat.getDegrees(), valLat.getMinutes(),
            valLat.getSeconds(), latDir, valLat.getValue());
      return new Location(longitudeCoordinate, latitudeCoordinate, enteredLocation);
   }

   private FullDateTime constructFullDateTime() {
      val dateTime = new SimpleDateTime(valDate.getSimpleDate(), valTime.getSimpleTime());
      val selectedTimeZone = TimeZones.UT.timeZoneForName(timezone.getValue());
      val selectedDst = cbDst.isSelected();
      double offSetForLmt = 0.0;
      if (selectedTimeZone == TimeZones.LMT) {
         offSetForLmt = valLongLocalTime.getValue() * 15.0;
         if (localeastwest.getValue().equalsIgnoreCase("W")) offSetForLmt = -offSetForLmt;
      }
      return new FullDateTime(dateTime, selectedTimeZone, selectedDst, offSetForLmt);
   }

}
