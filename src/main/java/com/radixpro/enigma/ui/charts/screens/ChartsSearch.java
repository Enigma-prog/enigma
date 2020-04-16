/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.charts.screens;

import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.domain.ChartData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;


public class ChartsSearch {

   @FXML
   private TextField searchArg;
   @FXML
   private ListView lvSearchResults;
   @FXML
   private Button btnCancel;
   @FXML
   private Button btnOk;
   private ChartData selectedItem;
   private boolean selectionMade = false;
   private List<ChartData> chartsFound;

   @FXML
   private void onSearch() {
      lvSearchResults.getItems().clear();
      String arg = searchArg.getText();
      chartsFound = new PersistedChartDataApi().searchWildCard(arg);
      for (ChartData chartData : chartsFound) {
         lvSearchResults.getItems().add(chartData.getChartMetaData().getName());
      }
   }

   @FXML
   void onSelectOk() {
      int index = lvSearchResults.getSelectionModel().getSelectedIndex();
      if (index >= 0) {
         selectedItem = chartsFound.get(index);
         selectionMade = true;
      }
      Stage stage = (Stage) btnOk.getScene().getWindow();
      stage.close();
   }

   @FXML
   void onCancel() {
      lvSearchResults.getItems().clear();
      Stage stage = (Stage) btnCancel.getScene().getWindow();
      stage.close();
   }


   public ChartData getSelectedItem() {
      return this.selectedItem;
   }

   public boolean isSelectionMade() {
      return this.selectionMade;
   }
}
