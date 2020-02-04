/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RosettaTest {

   private PersistedPropertyApi api;

   @Before
   public void setUp() {
      api = new PersistedPropertyApi();
      cleanDatabase();
      api.insert(new Property(1L, "lang", "du"));
   }

   @After
   public void tearDown() throws Exception {
      cleanDatabase();
   }

   @Test
   public void getRosetta() {
      var rosetta = Rosetta.getRosetta();
      assertNotNull(rosetta);
   }

   @Test
   public void setLanguageAndGetText() {
      var rosetta = Rosetta.getRosetta();
      rosetta.setLanguage("en");
      assertEquals("Tropical", rosetta.getText("eclipticprojections.tropical"));
      rosetta.setLanguage("du");
      assertEquals("Tropisch", rosetta.getText("eclipticprojections.tropical"));
   }

   @Test
   public void setLanguageUnsupportedLang() {
      var rosetta = Rosetta.getRosetta();
      rosetta.setLanguage("en");
      assertEquals("Tropical", rosetta.getText("eclipticprojections.tropical"));
      rosetta.setLanguage("es");
      assertEquals("Tropical", rosetta.getText("eclipticprojections.tropical"));
   }

   @Test
   public void getLocale() {
      var rosetta = Rosetta.getRosetta();
      rosetta.setLanguage("en");
      assertEquals("EN", rosetta.getLocale().getCountry());
      rosetta.setLanguage("du");
      assertEquals("DU", rosetta.getLocale().getCountry());
   }


   private void cleanDatabase() {
      List<Property> propList = api.readAll();
      // clean up
      for (Property prop : propList) {
         api.delete(prop);
      }
   }


}