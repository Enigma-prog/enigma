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

import static com.google.common.base.Preconditions.checkNotNull;

public enum EclipticProjections {
   UNKNOWN(0, "eclipticprojections.unknown"),
   TROPICAL(1, "eclipticprojections.tropical"),
   SIDEREAL(2, "eclipticprojections.sidereal");

   private final int id;
   private final String nameForRB;

   EclipticProjections(final int id, final String nameForRB) {
      this.id = id;
      this.nameForRB = checkNotNull(nameForRB);
   }

   public EclipticProjections getProjectionForId(final int id) {
      for (EclipticProjections eclipticProjection : EclipticProjections.values()) {
         if (eclipticProjection.getId() == id) {
            return eclipticProjection;
         }
      }
      return EclipticProjections.UNKNOWN;
   }

   /**
    * Create an observable list with names of ecliptical projections that can be used in the UI, e.g. in a SelectBox.
    *
    * @return The constructed observable list.
    */
   public ObservableList<String> getObservableList() {
      final Rosetta rosetta = Rosetta.getRosetta();
      final List<String> eclipticalProjNames = new ArrayList<>();
      for (EclipticProjections eclipticProjection : EclipticProjections.values()) {
         eclipticalProjNames.add(rosetta.getText(eclipticProjection.nameForRB));
      }
      return FXCollections.observableArrayList(eclipticalProjNames);
   }

   public int getId() {
      return id;
   }

   public String getNameForRB() {
      return nameForRB;
   }
}
