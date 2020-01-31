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

public enum TimeZones {

   LMT(0, "lmt", 0.0),
   UT(1, "ut", 0.0),
   CET(2, "cet", 1.0),
   EET(3, "eet", 2.0),
   EAT(4, "eat", 3.0),
   IRST(5, "irst", 3.5),
   AMT(6, "amt", 4.0),
   AFT(7, "aft", 4.5),
   PKT(8, "pkt", 5.0),
   IST(9, "ist", 5.5),
   IOT(10, "iot", 6.0),
   MMT(11, "mmt", 6.5),
   ICT(12, "ict", 7.0),
   WST(13, "wst", 8.0),
   JST(14, "jst", 9.0),
   ACST(15, "acst", 9.5),
   AEST(16, "aest", 10.0),
   LHST(17, "lhst", 10.5),
   NCT(18, "nct", 11.0),
   NZST(19, "nzst", 12.0),
   SST(20, "sst", -11.0),
   HAST(21, "hast", -10.0),
   MART(22, "mart", -9.5),
   AKST(23, "akst", -9.0),
   PST(24, "pst", -8.0),
   MST(25, "mst", -7.0),
   CST(26, "cst", -6.0),
   EST(27, "est", -5.0),
   AST(28, "ast", -4.0),
   NST(29, "nst", -3.5),
   BRT(30, "brt", -3.0),
   GST(31, "gst", -2.0),
   AZOT(32, "azot", -1.0);

   private static final String RB_PREFIX = "gen.lookup.timezone.";
   private final int id;
   private final String nameForRB;
   private final double offset;

   TimeZones(final int id, final String nameForRB, final double offset) {
      this.id = id;
      this.nameForRB = nameForRB;
      this.offset = offset;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return RB_PREFIX + nameForRB;
   }

   private String getRbKeyForSpecificName(final String name) {
      return RB_PREFIX + name;
   }

   public TimeZones timeZoneForId(final int id) {
      for (TimeZones timeZone : TimeZones.values()) {
         if (timeZone.getId() == id) {
            return timeZone;
         }
      }
      return TimeZones.UT;
   }

   public TimeZones timeZoneForZoneName(final String zoneLocalName) {
      final Rosetta rosetta = Rosetta.getRosetta();
      for (TimeZones timeZone : TimeZones.values()) {
         if (rosetta.getText(getRbKeyForSpecificName(timeZone.nameForRB)).equals(zoneLocalName)) {
            return timeZone;
         }
      }
      return TimeZones.UT;
   }

   public ObservableList<String> getObservableList() {
      final Rosetta rosetta = Rosetta.getRosetta();
      final List<String> localnames = new ArrayList<>();
      for (TimeZones timeZone : TimeZones.values()) {
         localnames.add(rosetta.getText(getRbKeyForSpecificName(timeZone.nameForRB)));
      }
      return FXCollections.observableArrayList(localnames);
   }

}
