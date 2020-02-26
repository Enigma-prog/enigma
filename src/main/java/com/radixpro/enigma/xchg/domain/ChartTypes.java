/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.shared.Rosetta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ChartTypes {
   UNKNOWN(0, "charttype.unknown"),
   FEMALE(1, "charttype.female"),
   MALE(2, "charttype.male"),
   NATAL(3, "charttype.natal"),
   EVENT(4, "charttype.event"),
   HORARY(5, "charttype.horary"),
   ELECTION(6, "charttype.election");

   private final int id;
   private final String nameForRB;

   ChartTypes(final int id, @NonNull final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public ChartTypes chartTypeForId(final int id) {
      for (ChartTypes chartType : ChartTypes.values()) {
         if (chartType.getId() == id) {
            return chartType;
         }
      }
      return ChartTypes.UNKNOWN;
   }

   public ChartTypes chartTypeForLocalName(@NonNull final String localName) {
      final Rosetta rosetta = Rosetta.getRosetta();
      for (ChartTypes chartType : ChartTypes.values()) {
         if (rosetta.getText(chartType.nameForRB).equals(localName)) {
            return chartType;
         }
      }
      return ChartTypes.UNKNOWN;
   }

   public ObservableList<String> getObservableList() {
      final Rosetta rosetta = Rosetta.getRosetta();
      final List<String> localnames = new ArrayList<>();
      for (ChartTypes chartType : ChartTypes.values()) {
         localnames.add(rosetta.getText(chartType.nameForRB));
      }
      return FXCollections.observableArrayList(localnames);
   }


}
