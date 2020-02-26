/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.shared.Property;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test for persistency of Property.
 */
public class PersistencyPropertyApiIt {

   private PersistedPropertyApi api;
   private Property prop1;
   private Property prop2;

   @Before
   public void setUp() {
      api = new PersistedPropertyApi();
      prop1 = new Property(1L, "one", "first");
      prop2 = new Property(2L, "two", "second");
      List<Property> propList = api.readAll();
      // clean up
      for (Property prop : propList) {
         api.delete(prop);
      }
   }

   @Test
   public void insert() {
      try {
         api.insert(prop1);
         assertTrue("No exception occurred", true);
      } catch (Exception e) {
         fail("Exception when inserting Property.");
      }
   }

   @Test
   public void update() {
      api.insert(prop1);
      var updateProp = new Property(1L, "one", "Erste");
      api.update(updateProp);
      Property resultPair = api.read("one").get(0);
      assertEquals("Erste", resultPair.getValue());
   }

   @Test
   public void read() {
      api.insert(prop1);
      api.insert(prop2);
      Property prop = api.read("one").get(0);
      assertEquals("first", prop.getValue());
   }

   @Test
   public void readAll() {
      api.insert(prop1);
      api.insert(prop2);
      List<Property> propList = api.readAll();
      assertEquals(2, propList.size());
   }

}
