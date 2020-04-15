/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.testsupport;

import com.radixpro.enigma.shared.Property;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;

import java.util.List;

/**
 * Initialize Rosetta and make sure the database is filled with the required properties.
 */
public class RosettaPreparer {

   private PersistedPropertyApi api;

   public void setRosetta() {
      api = new PersistedPropertyApi();
      cleanDatabase();
      api.insert(new Property(1L, "lang", "en"));
   }

   private void cleanDatabase() {
      List<Property> propList = api.readAll();
      // clean up
      for (Property prop : propList) {
         api.delete(prop);
      }
   }
}
