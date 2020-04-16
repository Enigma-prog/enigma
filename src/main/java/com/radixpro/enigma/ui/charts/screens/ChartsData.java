/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.be.astron.assist.HousePosition;
import com.radixpro.enigma.be.astron.main.CelObjectPosition;
import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableCelObjectPosition;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableEclipticPosition;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableMundanePosition;
import com.radixpro.enigma.xchg.api.CalculatedFullChart;
import com.radixpro.enigma.xchg.domain.CelestialObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.val;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChartsData {

   @FXML
   Label lblTitle;
   @FXML
   Label lblLocName;
   @FXML
   TableView tvCelObjectData;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEclColBodyGlyph;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEclColLongitude;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEclColSignGlyph;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEclColLongSpeed;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEclColLatitude;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEclColLatSpeed;
   //   @FXML
//   TableView tvEquatorialData;
//   @FXML
//   TableColumn<String, PresentableEclipticPosition> tvEquColBodyGlyph;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEquColRa;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEquColRaSpeed;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEquColDecl;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvEquColDeclSpeed;
   //   @FXML
//   TableView tvHorizontalData;
//   @FXML
//   TableColumn<String, PresentableEclipticPosition> tvHorColBodyGlyph;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvHorColAzimuth;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvHorColAltitude;
   //   @FXML
//   TableView tvDistanceData;
//   @FXML
//   TableColumn<String, PresentableEclipticPosition> tvDistColBodyGlyph;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvDistColDistance;
   @FXML
   TableColumn<String, PresentableEclipticPosition> tvDistColDistSpeed;
   @FXML
   TableView tvMundaneData;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColName;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColLongitude;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColSignGlyph;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColRa;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColDecl;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColAzimuth;
   @FXML
   TableColumn<String, PresentableMundanePosition> tvMundColAltitude;


   private CalculatedFullChart calculatedFullChart;

   public void initialize() {
//      populate();
   }

   public void populate() {
      val titlePrefix = Rosetta.getRosetta().getText("ui.charts.data.pagetitleprefix");
      lblTitle.setText(titlePrefix + " " + "Jan");
      // add name and other metadata to FullChart and use correct name
      lblLocName.setText(calculatedFullChart.getLocation().getName());
      CelestialObjects celObject;
      final String glyphFont = " -fx-font-family: \"EnigmaAstrology\";  -fx-font-size: 14;";
      final String dataFont = "-fx-font-family: \"Courier\";";
      tvEclColBodyGlyph.setStyle(glyphFont);
      tvEclColSignGlyph.setStyle(glyphFont);
      tvEclColLongitude.setStyle(dataFont);
      tvMundColSignGlyph.setStyle(glyphFont);

      final List<CelObjectPosition> bodies = calculatedFullChart.getBodies();
      var count = bodies.size();
      // ecliptical
      for (int i = 0; i < count; i++) {
         CelObjectPosition celObjectPosition = calculatedFullChart.getBodies().get(i);
         celObject = celObjectPosition.getCelestialBody();

//         val presEclipticPos = new PresentableEclipticPosition(celObject, celObjectPosition.getEclipticalPosition());
         val presPos = new PresentableCelObjectPosition(celObjectPosition, celObjectPosition.getHorizontalPosition());   // TODO should be one parameter
         tvEclColBodyGlyph.setCellValueFactory(new PropertyValueFactory<>("celBodyGlyph"));
         tvEclColLongitude.setCellValueFactory(new PropertyValueFactory<>("formattedLongitude"));
         tvEclColSignGlyph.setCellValueFactory(new PropertyValueFactory<>("signGlyph"));
         tvEclColLongSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedLongSpeed"));
         tvEclColLatitude.setCellValueFactory(new PropertyValueFactory<>("formattedLatitude"));
         tvEclColLatSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedLatSpeed"));
         tvEquColRa.setCellValueFactory(new PropertyValueFactory<>("formattedRightAscension"));
         tvEquColRaSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedRaSpeed"));
         tvEquColDecl.setCellValueFactory(new PropertyValueFactory<>("formattedDeclination"));
         tvEquColDeclSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedDeclSpeed"));
         tvHorColAzimuth.setCellValueFactory(new PropertyValueFactory<>("formattedAzimuth"));
         tvHorColAltitude.setCellValueFactory(new PropertyValueFactory<>("formattedAltitude"));
         tvDistColDistance.setCellValueFactory(new PropertyValueFactory<>("formattedDistance"));
         tvDistColDistSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedDistSpeed"));

         tvCelObjectData.getItems().add(presPos);
      }
//      // equatorial
//      for (int i = 0; i < count; i++) {
//         CelObjectPosition celObjectPosition = calculatedFullChart.getBodies().get(i);
//         celObject = celObjectPosition.getCelestialBody();
//         val presEquatorialPos = new PresentableEquatorialPosition(celObject, celObjectPosition.getEquatorialPosition());
//         tvEquColBodyGlyph.setCellValueFactory(new PropertyValueFactory<>("celBodyGlyph"));
//         tvEquColRa.setCellValueFactory(new PropertyValueFactory<>("formattedRightAscension"));
//         tvEquColRaSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedRaSpeed"));
//         tvEquColDecl.setCellValueFactory(new PropertyValueFactory<>("formattedDeclination"));
//         tvEquColDeclSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedDeclSpeed"));
//         tvEquatorialData.getItems().add(presEquatorialPos);
//      }
//      // horizontal
//      for (int i = 0; i < count; i++) {
//         CelObjectPosition celObjectPosition = calculatedFullChart.getBodies().get(i);
//         celObject = celObjectPosition.getCelestialBody();
//         val presHorizontalPos = new PresentableHorizontalPosition(celObject, celObjectPosition.getHorizontalPosition());
//         tvHorColBodyGlyph.setCellValueFactory(new PropertyValueFactory<>("celBodyGlyph"));
//         tvHorColAzimuth.setCellValueFactory(new PropertyValueFactory<>("formattedAzimuth"));
//         tvHorColAltitude.setCellValueFactory(new PropertyValueFactory<>("formattedAltitude"));
//         tvHorizontalData.getItems().add(presHorizontalPos);
//      }
//      // distance
//      for (int i = 0; i < count; i++) {
//         CelObjectPosition celObjectPosition = calculatedFullChart.getBodies().get(i);
//         celObject = celObjectPosition.getCelestialBody();
//         val presDistancePos = new PresentableDistancePosition(celObject, celObjectPosition.getEclipticalPosition());
//         tvDistColBodyGlyph.setCellValueFactory(new PropertyValueFactory<>("celBodyGlyph"));
//         tvDistColDistance.setCellValueFactory(new PropertyValueFactory<>("formattedDistance"));
//         tvDistColDistSpeed.setCellValueFactory(new PropertyValueFactory<>("formattedDistSpeed"));
//         tvDistanceData.getItems().add(presDistancePos);
//      }

      val rosetta = Rosetta.getRosetta();
      handlePresMundPos(rosetta.getText("ui.shared.mc"), calculatedFullChart.getHouseValues().getMc());
      handlePresMundPos(rosetta.getText("ui.shared.asc"), calculatedFullChart.getHouseValues().getAscendant());
      final List<HousePosition> cusps = calculatedFullChart.getHouseValues().getCusps();
      count = cusps.size() - 1;  // index for houses runs from 1..12
      for (int i = 1; i <= count; i++) {
         handlePresMundPos(Integer.toString(i), cusps.get(i));
      }
      handlePresMundPos(rosetta.getText("ui.shared.vertex"), calculatedFullChart.getHouseValues().getVertex());
      handlePresMundPos(rosetta.getText("ui.shared.eastpoint"), calculatedFullChart.getHouseValues().getEastpoint());
   }


   private void handlePresMundPos(final String name, final HousePosition pos) {
      checkNotNull(name);
      checkNotNull(pos);
      val presMundPos = new PresentableMundanePosition(name, pos);
      tvMundColName.setCellValueFactory(new PropertyValueFactory<>("name"));
      tvMundColLongitude.setCellValueFactory(new PropertyValueFactory<>("formattedLongitude"));
      tvMundColSignGlyph.setCellValueFactory(new PropertyValueFactory<>("signGlyph"));
      tvMundColRa.setCellValueFactory(new PropertyValueFactory<>("formattedRa"));
      tvMundColDecl.setCellValueFactory(new PropertyValueFactory<>("formattedDeclination"));
      tvMundColAzimuth.setCellValueFactory(new PropertyValueFactory<>("formattedAzimuth"));
      tvMundColAltitude.setCellValueFactory(new PropertyValueFactory<>("formattedAltitude"));
      tvMundaneData.getItems().add(presMundPos);
   }


   public void setCalculatedFullChart(CalculatedFullChart calculatedFullChart) {
      this.calculatedFullChart = calculatedFullChart;
      populate();
   }
}
