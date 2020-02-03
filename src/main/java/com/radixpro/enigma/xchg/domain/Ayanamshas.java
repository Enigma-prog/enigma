/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

public enum Ayanamshas {
   UNKNOWN(-2, -1, "ayanamshas.unknown.name", "ayanamshas.unknown.descr"),
   NONE(-1, -1, "ayanamshas.none.name", "ayanamshas.none.descr"),
   FAGAN(0, 0, "ayanamshas.fagan.name", "ayanamshas.fagan.descr"),
   LAHIRI(1, 1, "ayanamshas.lahiri.name", "ayanamshas.lahiri.descr"),
   DELUCE(2, 2, "ayanamshas.deluce.name", "ayanamshas.deluce.descr"),
   RAMAN(3, 3, "ayanamshas.raman.name", "ayanamshas.raman.descr"),
   USHA_SHASHI(4, 4, "ayanamshas.ushashashi.name", "ayanamshas.ushashashi.descr"),
   KRISHNAMURTI(5, 5, "ayanamshas.krishnamurti.name", "ayanamshas.krishnamurti.descr"),
   DJWHAL_KHUL(6, 6, "ayanamshas.djwhalkhul.name", "ayanamshas.djwhalkhul.descr"),
   YUKTESHWAR(7, 7, "ayanamshas.yukteshwar.name", "ayanamshas.yukteshwar.descr"),
   BHASIN(8, 8, "ayanamshas.bhasin.name", "ayanamshas.bhasin.descr"),
   KUGLER_1(9, 9, "ayanamshas.kugler1.name", "ayanamshas.kugler1.descr"),
   KUGLER_2(10, 10, "ayanamshas.kugler2.name", "ayanamshas.kugler2.descr"),
   KUGLER_3(11, 11, "ayanamshas.kugler3.name", "ayanamshas.kugler3.descr"),
   HUBER(12, 12, "ayanamshas.huber.name", "ayanamshas.huber.descr"),
   ETA_PISCIUM(13, 13, "ayanamshas.etapiscium.name", "ayanamshas.etapiscium.descr"),
   ALDEBARAN_15TAU(14, 14, "ayanamshas.aldebaran.name", "ayanamshas.aldebaran.descr"),
   HIPPARCHUS(15, 15, "ayanamshas.hipparchus.name", "ayanamshas.hipparchus.descr"),
   SASSANIAN(16, 16, "ayanamshas.sassanian.name", "ayanamshas.sassanian.descr"),
   GALACT_CTR_0SAG(17, 17, "ayanamshas.galcent0sag.name", "ayanamshas.galcent0sag.descr"),
   J2000(18, 18, "ayanamshas.j2000.name", "ayanamshas.j2000.descr"),
   J1900(19, 19, "ayanamshas.j1900.name", "ayanamshas.j1900.descr"),
   B1950(20, 20, "ayanamshas.b1950.name", "ayanamshas.b1950.descr"),
   SURYASIDDHANTA(21, 21, "ayanamshas.suryasiddhanta.name", "ayanamshas.suryasiddhanta.descr"),
   SURYASIDDHANTA_MEAN_SUN(22, 22, "ayanamshas.suryasiddhantamean.name", "ayanamshas.suryasiddhantamean.descr"),
   ARYABHATA(23, 23, "ayanamshas.aryabhata.name", "ayanamshas.aryabhata.descr"),
   ARYABHATA_MEAN_SUN(24, 24, "ayanamshas.aryabhatamean.name", "ayanamshas.aryabhatamean.descr"),
   SS_REVATI(25, 25, "ayanamshas.ssrevati.name", "ayanamshas.ssrevati.descr"),
   SS_CITRA(26, 26, "ayanamshas.sscitra.name", "ayanamshas.sscitra.descr"),
   TRUE_CITRA(27, 27, "ayanamshas.truecitra.name", "ayanamshas.truecitra.descr"),
   TRUE_REVATI(28, 28, "ayanamshas.truerevati.name", "ayanamshas.truerevati.descr"),
   TRUE_PUSHYA(29, 29, "ayanamshas.truepushya.name", "ayanamshas.truepushya.descr"),
   GALACTIC_CTR_BRAND(30, 30, "ayanamshas.galcenterbrand.name", "ayanamshas.galcenterbrand.descr"),
   GALACTIC_EQ_IAU1958(31, 31, "ayanamshas.galcenteriau1958.name", "ayanamshas.galcenteriau1958.descr"),
   GALACTIC_EQ(32, 32, "ayanamshas.galequator.name", "ayanamshas.galequator.descr"),
   GALACTIC_EQ_MID_MULA(33, 33, "ayanamshas.galequatormidmula.name", "ayanamshas.galequatormidmula.descr"),
   SKYDRAM(34, 34, "ayanamshas.skydram.name", "ayanamshas.skydram.descr"),
   TRUE_MULA(35, 35, "ayanamshas.truemula.name", "ayanamshas.truemula.descr"),
   DHRUVA(36, 36, "ayanamshas.dhruva.name", "ayanamshas.dhruva.descr"),
   ARYABHATA_522(37, 37, "ayanamshas.aryabhata522.name", "ayanamshas.aryabhata522.descr"),
   BRITTON(38, 38, "ayanamshas.britton.name", "ayanamshas.britton.descr"),
   GAQQLACTIC_CTR_0CAP(39, 39, "ayanamshas.galcenter0cap.name", "ayanamshas.galcenter0cap.descr");

   private static final String RB_PREFIX = "ayanamshas.";
   private static final String RB_NAME_POSTFIX = ".name";
   private static final String RB_DESCRIPTION_POSTFIX = ".description";
   private final String nameForRB;
   private final String descrForRB;
   private final int seId;
   private final int id;

   Ayanamshas(final int id, final int seId, final String nameForRB, final String descrForRb) {
      this.id = id;
      this.seId = seId;
      this.nameForRB = nameForRB;
      this.descrForRB = descrForRb;
   }

   public int getSeId() {
      return seId;
   }

   public int getId() {
      return id;
   }

   public String getRbKeyForName() {
      return nameForRB;
   }

   public String getRbKeyForDescription() {
      return descrForRB;
   }

   public Ayanamshas getAyanamshaForId(int id) {
      for (Ayanamshas ayanamsha : Ayanamshas.values()) {
         if (ayanamsha.getId() == id) {
            return ayanamsha;
         }
      }
      return Ayanamshas.UNKNOWN;
   }

}
