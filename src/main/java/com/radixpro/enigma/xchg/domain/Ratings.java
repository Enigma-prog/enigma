/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.shared.Rosetta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum Ratings implements Serializable {
   ZZ(0, "zz"),
   AA(1, "aa"),
   A(2, "a"),
   B(3, "b"),
   C(4, "c"),
   DD(5, "dd"),
   X(6, "x"),
   XX(7, "xx");

   private static final String RB_PREFIX = "gen.lookup.ratings.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
   private final int id;
   private final String nameForRB;

   Ratings(final int id, final String nameForRB) {
      this.id = id;
      this.nameForRB = nameForRB;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB + RB_NAME_POSTFIX;
   }

   public String getRbKeyForDescription() {
      return RB_PREFIX + nameForRB + RB_DESCRIPTION_POSTFIX;
   }

   private String getRbKeyForSpecificName(final String name) {
      return RB_PREFIX + name + RB_NAME_POSTFIX;
   }

   public Ratings getRatingForId(final int id) {
      for (Ratings rating : Ratings.values()) {
         if (rating.getId() == id) {
            return rating;
         }
      }
      return Ratings.ZZ;
   }

   public Ratings chartTypeForRatingName(final String ratingName) {
      final Rosetta rosetta = Rosetta.getRosetta();
      for (Ratings rating : Ratings.values()) {
         if (rosetta.getText(getRbKeyForSpecificName(rating.nameForRB)).equals(ratingName)) {
            return rating;
         }
      }
      return Ratings.ZZ;
   }


   public ObservableList<String> getObservableList() {
      final Rosetta rosetta = Rosetta.getRosetta();
      final List<String> ratingNames = new ArrayList<>();
      for (Ratings rating : Ratings.values()) {
         ratingNames.add(rosetta.getText(getRbKeyForSpecificName(rating.nameForRB)));
      }
      return FXCollections.observableArrayList(ratingNames);
   }


}