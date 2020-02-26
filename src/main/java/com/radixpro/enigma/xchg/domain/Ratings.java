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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public enum Ratings implements Serializable {
   ZZ(0, "ratings.zz"),
   AA(1, "ratings.aa"),
   A(2, "ratings.a"),
   B(3, "ratings.b"),
   C(4, "ratings.c"),
   DD(5, "ratings.dd"),
   X(6, "ratings.x"),
   XX(7, "ratings.xx");

   private final int id;
   private final String nameForRB;

   Ratings(final int id, @NonNull final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public Ratings getRatingForId(final int id) {
      for (Ratings rating : Ratings.values()) {
         if (rating.getId() == id) {
            return rating;
         }
      }
      return Ratings.ZZ;
   }

   public Ratings ratingForName(@NonNull final String ratingName) {
      final Rosetta rosetta = Rosetta.getRosetta();
      for (Ratings rating : Ratings.values()) {
         if (rosetta.getText(rating.nameForRB).equals(ratingName)) {
            return rating;
         }
      }
      return Ratings.ZZ;
   }


   public ObservableList<String> getObservableList() {
      final Rosetta rosetta = Rosetta.getRosetta();
      final List<String> ratingNames = new ArrayList<>();
      for (Ratings rating : Ratings.values()) {
         ratingNames.add(rosetta.getText(rating.nameForRB));
      }
      return FXCollections.observableArrayList(ratingNames);
   }


}