/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron.assist;

public enum AyanamshasToCalculate {
   FAGAN(0),
   LAHIRI(1),
   DELUCE(2),
   RAMAN(3),
   USHA_SHASHI(4),
   KRISHNAMURTI(5),
   DJWHAL_KHUL(6),
   YUKTESHWAR(7),
   BHASIN(8),
   KUGLER_1(9),
   KUGLER_2(10),
   KUGLER_3(11),
   HUBER(12),
   ETA_PISCIUM(13),
   ALDEBARAN_15TAU(14),
   HIPPARCHUS(15),
   SASSANIAN(16),
   GALACT_CTR_0SAG(17),
   J2000(18),
   J1900(19),
   B1950(20),
   SURYASIDDHANTA(21),
   SURYASIDDHANTA_MEAN_SUN(22),
   ARYABHATA(23),
   ARYABHATA_MEAN_SUN(24),
   SS_REVATI(25),
   SS_CITRA(26),
   TRUE_CITRA(27),
   TRUE_REVATI(28),
   TRUE_PUSHYA(29),
   GALACTIC_CTR_BRAND(30),
   GALACTIC_EQ_IAU1958(31),
   GALACTIC_EQ(32),
   GALACTIC_EQ_MID_MULA(33),
   SKYDRAM(34),
   TRUE_MULA(35),
   DHRUVA(36),
   ARYABHATA_522(37),
   BRITTON(38),
   GAQQLACTIC_CTR_0CAP(39);

   private final int seId;

   AyanamshasToCalculate(final int seId) {
      this.seId = seId;
   }

   public int getSeId() {
      return seId;
   }
}
