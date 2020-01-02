/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum Ayanamshas implements LookupAble {
   NONE(-1, -1, "none"),
   FAGAN(0, 0, "fagan"),
   LAHIRI(1, 1, "lahiri"),
   DELUCE(2, 2, "deluce"),
   RAMAN(3, 3, "raman"),
   USHA_SHASHI(4, 4, "ushashashi"),
   KRISHNAMURTI(5, 5, "krishnamurti"),
   DJWHAL_KHUL(6, 6, "djwhalkhul"),
   YUKTESHWAR(7, 7, "yukteshwar"),
   BHASIN(8, 8, "bhasin"),
   KUGLER_1(9, 9, "kugler1"),
   KUGLER_2(10, 10, "kugler2"),
   KUGLER_3(11, 11, "kugler3"),
   HUBER(12, 12, "huber"),
   ETA_PISCIUM(13, 13, "etapiscium"),
   ALDEBARAN_15TAU(14, 14, "aldebaran"),
   HIPPARCHUS(15, 15, "hipparchus"),
   SASSANIAN(16, 16, "sassanian"),
   GALACT_CTR_0SAG(17, 17, "galcent0sag"),
   J2000(18, 18, "j2000"),
   J1900(19, 19, "j1900"),
   B1950(20, 20, "b1950"),
   SURYASIDDHANTA(21, 21, "suryasiddhanta"),
   SURYASIDDHANTA_MEAN_SUN(22, 22, "suryasiddhantamean"),
   ARYABHATA(23, 23, "aryabhata"),
   ARYABHATA_MEAN_SUN(24, 24, "aryabhata"),
   SS_REVATI(25, 25, "ssrevati"),
   SS_CITRA(26, 26, "sscitra"),
   TRUE_CITRA(27, 27, "truecitra"),
   TRUE_REVATI(28, 28, "truerevati"),
   TRUE_PUSHYA(29, 29, "truepushya"),
   GALACTIC_CTR_BRAND(30, 30, "galcenterbrand"),
   GALACTIC_EQ_IAU1958(31, 31, "galcenteriau1958"),
   GALACTIC_EQ(32, 32, "galequator"),
   GALACTIC_EQ_MID_MULA(33, 33, "galequatormidmula"),
   SKYDRAM(34, 34, "skydram"),
   TRUE_MULA(35, 35, "truemula"),
   DHRUVA(36, 36, "dhruva"),
   ARYABHATA_522(37, 37, "aryabhata522"),
   BRITTON(38, 38, "britton"),
   GAQQLACTIC_CTR_0CAP(39, 39, "galcenter0cap");

   private static final String RB_PREFIX = "gen.lookup.ayanamshas.";
   private final String nameForRB;
   private final int seId;
   private final int id;

   Ayanamshas(final int id, final int seId, final String nameForRB) {
      this.id = id;
      this.seId = seId;
      this.nameForRB = nameForRB;
   }

   public int getSeId() {
      return seId;
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
}
