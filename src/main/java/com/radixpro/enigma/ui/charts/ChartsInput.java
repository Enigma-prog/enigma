/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts;

import com.radixpro.enigma.be.astron.core.SeFrontend;
import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.Help;
import com.radixpro.enigma.ui.shared.validation.*;
import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.domain.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
   @FXML
   public ChoiceBox subject;
   @FXML
   public ChoiceBox rating;
   @FXML
   public ChoiceBox timezone;
   @FXML
   public ChoiceBox<String> eastwest;
   @FXML
   public ChoiceBox<String> northsouth;
   @FXML
   public ChoiceBox<String> calendar;
   @FXML
   public ChoiceBox<String> localeastwest;
   @FXML
   public CheckBox dst;
   @FXML
   public Button calculatebtn;
   @FXML
   public Button helpBtn;


   private ResourceBundle resourceBundle;
   private final String textInputDefaultStyle = "-fx-background-radius:5; -fx-background-color:blanchedalmond;";
   private final String textInputErrorStyle = "-fx-background-radius:5; -fx-background-color:yellow;";
   private boolean nameOk = false;
   private boolean latitudeOk = false;
   private boolean longitudeOk = false;
   private boolean dateOk = false;
   private boolean timeOk = false;
   private boolean localTimeOk = false;
   private boolean timeZoneLocalSelected = false;

   private SimpleDate dateInput;
   private SimpleTime timeInput;
   private double longitudeInput;
   private double latitudeInput;
   private double localTimeInput;

   @FXML
   void onCalculate(ActionEvent event) {
      saveData();
      // close screen
   }

   @FXML
   void onHelp(ActionEvent event) throws IOException {
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


   public void initialize() {
      resourceBundle = ResourceBundle.getBundle("rb/texts", Rosetta.getRosetta().getLocale());
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
      timezone.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue observable, Object oldValue, Object newValue) -> checkTimeZones((String) newValue));
      localtime.textProperty().addListener((observable, oldValue, newValue) -> validateLocalTime(newValue));

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
      localeastwest.setStyle(textInputDefaultStyle);
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
      ObservableList observableList = FXCollections.observableArrayList(latList);
      northsouth.setItems(observableList);
      northsouth.getSelectionModel().select(0);
   }

   private void initLongitude() {
      var rosetta = Rosetta.getRosetta();
      List<String> longList = new ArrayList<>();
      longList.add(rosetta.getText("ui.shared.direction.east.char"));
      longList.add(rosetta.getText("ui.shared.direction.west.char"));
      ObservableList observableList = FXCollections.observableArrayList(longList);
      eastwest.setItems(observableList);
      eastwest.getSelectionModel().select(0);
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

   private void initTimeZone() {
      ObservableList<String> observableList = TimeZones.UT.getObservableList();
      timezone.setItems(observableList);
      timezone.getSelectionModel().select(1);  // UT
   }

   private void initLocalEastWest() {
      var rosetta = Rosetta.getRosetta();
      List<String> longList = new ArrayList<>();
      longList.add(rosetta.getText("ui.shared.direction.east.char"));
      longList.add(rosetta.getText("ui.shared.direction.west.char"));
      ObservableList observableList = FXCollections.observableArrayList(longList);
      localeastwest.setItems(observableList);
      localeastwest.getSelectionModel().select(0);
   }

   private void validateName(final String newName) {
      var valName = new ValidatedChartName(newName);
      name.setText(valName.getNameText());
      if (valName.isValidated()) {
         name.setStyle(textInputDefaultStyle);
         nameOk = true;
      } else {
         name.setStyle(textInputErrorStyle);
         nameOk = false;
      }
      checkStatus();
   }

   private void validateLongitude(final String newLongitude) {
      var valLong = new ValidatedLongitude(newLongitude);
      if (valLong.isValidated()) {
         longitudeInput = valLong.getValue();
         longitudeValue.setStyle(textInputDefaultStyle);
         longitudeOk = true;
      } else {
         longitudeValue.setStyle(textInputErrorStyle);
         longitudeOk = false;
      }
      checkStatus();
   }

   private void validateLocalTime(final String newLocalTime) {
      var valLocalTime = new ValidatedLongitude(newLocalTime);
      if (valLocalTime.isValidated()) {
         localTimeInput = valLocalTime.getValue();
         localtime.setStyle(textInputDefaultStyle);
         localTimeOk = true;
      } else {
         localtime.setStyle(textInputErrorStyle);
         localTimeOk = false;
      }
      checkStatus();
   }

   private void validateLatitude(final String newLatitude) {
      var valLat = new ValidatedLatitude(newLatitude);
      if (valLat.isValidated()) {
         latitudeInput = valLat.getValue();
         latitudeValue.setStyle(textInputDefaultStyle);
         latitudeOk = true;
      } else {
         latitudeValue.setStyle(textInputErrorStyle);
         latitudeOk = false;
      }
      checkStatus();
   }

   private void validateDate(final String newDate) {
      var valDate = new ValidatedDate(newDate + '/' + calendar.getValue());
      if (valDate.isValidated()) {
         date.setStyle(textInputDefaultStyle);
         dateInput = valDate.getSimpleDate();
         dateOk = true;
      } else {
         date.setStyle(textInputErrorStyle);
         dateInput = null;
         dateOk = false;
      }
      checkStatus();
   }

   private void validateTime(final String newTime) {
      var valTime = new ValidatedTime(newTime);
      if (valTime.isValidated()) {
         time.setStyle(textInputDefaultStyle);
         timeInput = valTime.getSimpleTime();
         timeOk = true;
      } else {
         time.setStyle(textInputErrorStyle);
         timeInput = null;
         timeOk = false;
      }
      checkStatus();
   }

   private void checkTimeZones(final String newValue) {
      TimeZones selected = TimeZones.UT.timeZoneForZoneName(newValue);
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

   private void saveData() {
      final var inputName = name.getText();
      final var inputDescription = description.getText().trim();
      final var inputSource = source.getText().trim();
      final var inputRating = Ratings.ZZ.chartTypeForRatingName((String) rating.getValue());
      final var inputChartType = ChartTypes.UNKNOWN.chartTypeForLocalName((String) subject.getValue());


      final var enteredLocation = locationName.getText().trim();    // // todo: add after updating ChartMetaData

      final var dateTime = new SimpleDateTime(SeFrontend.getFrontend(), dateInput, timeInput);  // todo handle timezone
      final var location = new Location(latitudeInput, longitudeInput);
      final var metaData = new ChartMetaData(inputName, inputDescription, inputSource, inputChartType, inputRating);

      final var api = new PersistedChartDataApi();
      final long chartId = api.getMaxId();
      final var chartData = new ChartData(chartId, dateTime, location, metaData);
      api.insert(chartData);

      System.out.println(api.read((int) chartId).get(0).getChartMetaData().getName());

   }

}
