/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RosettaTest {

   @Test
   public void getRosetta() {
      Rosetta rosetta = Rosetta.getRosetta();
      assertNotNull(rosetta);
   }

//   @Test
//   public void reInitialize() {
//      Rosetta rosetta = Rosetta.getRosetta();
//      setPropForLocale("en");
//      rosetta.reInitialize();
//      assertEquals("Tropical", rosetta.getText("gen.lookup.eclipticprojections.tropical.name"));
//      setPropForLocale("du");
//      rosetta.reInitialize();
//      assertEquals("Tropisch", rosetta.getText("gen.lookup.eclipticprojections.tropical.name"));
//   }
//
//   @Test
//   public void getText() {
//      Rosetta rosetta = Rosetta.getRosetta();
//      setPropForLocale("en");
//      rosetta.reInitialize();
//      assertEquals("Equal from Asc", rosetta.getText("gen.lookup.houses.equalasc.name"));
//   }
//
//
//   private void setPropForLocale(final String langProp) {
//      try (OutputStream output = new FileOutputStream("./src/main/resources/i18n.properties")) {
//         Properties prop = new Properties();
//         prop.setProperty("lang", langProp);
//         prop.store(output, null);
//         System.out.println(prop);
//      } catch (IOException io) {
//         io.printStackTrace();
//      }
//   }


}