/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.shared.Rosetta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public enum ChartTypes {
   UNKNOWN(0, "unknown"),
   NATAL(1, "natal"),
   EVENT(2, "event"),
   HORARY(3, "horary"),
   ELECTION(4, "election");

   private static final String RB_PREFIX = "gen.lookup.charttype.";
   private static final String RB_NAME_POSTFIX = ".name";
   private final int id;
   private final String nameForRB;

   ChartTypes(final int id, final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

   private String getRbKeyForSpecificName(final String name) {
      return RB_PREFIX + name + RB_NAME_POSTFIX;
   }

   public ChartTypes chartTypeForId(final int id) {
      for (ChartTypes chartType : ChartTypes.values()) {
         if (chartType.getId() == id) {
            return chartType;
         }
      }
      return ChartTypes.UNKNOWN;
   }

   public ChartTypes chartTypeForLocalName(final String localName) {
      final Rosetta rosetta = Rosetta.getRosetta();
      for (ChartTypes chartType : ChartTypes.values()) {
         if (rosetta.getText(getRbKeyForSpecificName(chartType.nameForRB)).equals(localName)) {
            return chartType;
         }
      }
      return ChartTypes.UNKNOWN;
   }

   public ObservableList<String> getObservableList() {
      final Rosetta rosetta = Rosetta.getRosetta();
      final List<String> localnames = new ArrayList<>();
      for (ChartTypes chartType : ChartTypes.values()) {
         localnames.add(rosetta.getText(getRbKeyForSpecificName(chartType.nameForRB)));
      }
      return FXCollections.observableArrayList(localnames);
   }


}
