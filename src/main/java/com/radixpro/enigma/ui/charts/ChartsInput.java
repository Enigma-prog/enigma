/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.Help;
import com.radixpro.enigma.ui.shared.validation.*;
import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
   public Label lbllocaltime;
   private static final String TEXT_INPUT_DEFAULT_STYLE = "-fx-background-radius:5; -fx-background-color:blanchedalmond;";
   private static final String TEXT_INPUT_ERROR_STYLE = "-fx-background-radius:5; -fx-background-color:yellow;";
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
   public Button calculatebtn;
   @FXML
   public ChoiceBox<String> rating;
   @FXML
   public ChoiceBox<String> timezone;
   private boolean nameOk = false;
   private boolean latitudeOk = false;
   private boolean longitudeOk = false;
   private boolean dateOk = false;
   private boolean timeOk = false;
   private boolean localTimeOk = false;
   private boolean timeZoneLocalSelected = false;

   private SimpleDate dateInput;
   private SimpleTime timeInput;
   private long newChartId;

   private ValidatedLongitude valLong;
   private ValidatedLatitude valLat;

   @FXML
   void onCalculate() {
      newChartId = saveData();
      Stage stage = (Stage) calculatebtn.getScene().getWindow();
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

   public long getNewChartId() {
      return newChartId;
   }

   public void initialize() {
      setDynamicStyles();
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

   private void setDynamicStyles() {
      name.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      source.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      description.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      locationName.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      longitudeValue.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      latitudeValue.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      date.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      time.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      subject.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      rating.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      timezone.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      localtime.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      eastwest.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      northsouth.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      calendar.setStyle(TEXT_INPUT_DEFAULT_STYLE);
      localeastwest.setStyle(TEXT_INPUT_DEFAULT_STYLE);
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
      var valName = new ValidatedChartName(newName);
      name.setText(valName.getNameText());
      if (valName.isValidated()) {
         name.setStyle(TEXT_INPUT_DEFAULT_STYLE);
         nameOk = true;
      } else {
         name.setStyle(TEXT_INPUT_ERROR_STYLE);
         nameOk = false;
      }
      checkStatus();
   }

   private void validateLongitude(final String newLongitude) {
      var valLong = new ValidatedLongitude(newLongitude);
      if (valLong.isValidated()) {
         longitudeValue.setStyle(TEXT_INPUT_DEFAULT_STYLE);
         longitudeOk = true;
      } else {
         longitudeValue.setStyle(TEXT_INPUT_ERROR_STYLE);
         longitudeOk = false;
      }
      checkStatus();
   }

   private void validateLocalTime(final String newLocalTime) {
      var valLocalTime = new ValidatedLongitude(newLocalTime);
      if (valLocalTime.isValidated()) {
         localtime.setStyle(TEXT_INPUT_DEFAULT_STYLE);
         localTimeOk = true;
      } else {
         localtime.setStyle(TEXT_INPUT_ERROR_STYLE);
         localTimeOk = false;
      }
      checkStatus();
   }

   private void validateLatitude(final String newLatitude) {
      valLat = new ValidatedLatitude(newLatitude);
      if (valLat.isValidated()) {
         latitudeValue.setStyle(TEXT_INPUT_DEFAULT_STYLE);
         latitudeOk = true;
      } else {
         latitudeValue.setStyle(TEXT_INPUT_ERROR_STYLE);
         latitudeOk = false;
      }
      checkStatus();
   }

   private void validateDate(final String newDate) {
      var valDate = new ValidatedDate(newDate + '/' + calendar.getValue());
      if (valDate.isValidated()) {
         date.setStyle(TEXT_INPUT_DEFAULT_STYLE);
         dateInput = valDate.getSimpleDate();
         dateOk = true;
      } else {
         date.setStyle(TEXT_INPUT_ERROR_STYLE);
         dateInput = null;
         dateOk = false;
      }
      checkStatus();
   }

   private void validateTime(final String newTime) {
      var valTime = new ValidatedTime(newTime);
      if (valTime.isValidated()) {
         time.setStyle(TEXT_INPUT_DEFAULT_STYLE);
         timeInput = valTime.getSimpleTime();
         timeOk = true;
      } else {
         time.setStyle(TEXT_INPUT_ERROR_STYLE);
         timeInput = null;
         timeOk = false;
      }
      checkStatus();
   }

   private void checkTimeZones(final String newValue) {
      TimeZones selected = TimeZones.UT.timeZoneForName(newValue);
      if (selected == TimeZones.LMT) {
         localtime.setEditable(true);
         localtime.setDisable(false);
         lbllocaltime.setDisable(false);
         localeastwest.setDisable(false);
         timeZoneLocalSelected = true;
      } else {
         localtime.setEditable(false);
         localtime.setDisable(true);
         lbllocaltime.setDisable(true);
         localeastwest.setDisable(true);
         timeZoneLocalSelected = false;
      }
      checkStatus();
   }

   private void checkStatus() {
      calculatebtn.setDisable(!(nameOk && latitudeOk && longitudeOk && dateOk && timeOk
            && (localTimeOk || !timeZoneLocalSelected)));
   }

   private long saveData() {
      final var inputName = name.getText();
      final var inputDescription = description.getText().trim();
      final var inputSource = source.getText().trim();
      final var inputRating = Ratings.ZZ.ratingForName(rating.getValue());
      final var inputChartType = ChartTypes.UNKNOWN.chartTypeForLocalName((String) subject.getValue());

      final var enteredLocation = locationName.getText().trim();
      String longDir = northsouth.getValue();
      GeographicCoordinate longitudeCoordinate = new GeographicCoordinate(valLong.getDegrees(), valLong.getMinutes(),
            valLong.getSeconds(), longDir, valLong.getValue());
      String latDir = eastwest.getValue();
      GeographicCoordinate latitudeCoordinate = new GeographicCoordinate(valLat.getDegrees(), valLat.getMinutes(),
            valLat.getSeconds(), latDir, valLat.getValue());
      final var location = new Location(longitudeCoordinate, latitudeCoordinate, enteredLocation);

      final var dateTime = new SimpleDateTime(dateInput, timeInput);  // todo handle timezone

      final var metaData = new ChartMetaData(inputName, inputDescription, inputSource, inputChartType, inputRating);

      final var api = new PersistedChartDataApi();
      final long chartId = api.getMaxId() + 1;
      final FullDateTime fullDateTime = new FullDateTime(dateTime, TimeZones.UT, false, 0.0);  // TODO use real values instead of dummy values
      final var chartData = new ChartData(chartId, fullDateTime, location, metaData);
      api.insert(chartData);
      return chartId;
   }

}
