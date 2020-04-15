/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.testsupport.RosettaPreparer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CelestialObjectsTest {

   private CelestialObjects celBody;

   @Before
   public void setUp() {
      new RosettaPreparer().setRosetta();
      celBody = CelestialObjects.JUPITER;
   }

   @Test
   public void getId() {
      assertEquals(7, celBody.getId());
   }

   @Test
   public void getSeId() {
      assertEquals(5, celBody.getSeId());
   }

   @Test
   public void getCategory() {
      assertEquals(CelObjectCategory.CLASSICS, celBody.getCategory());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("celobject.jupiter", celBody.getNameForRB());
   }

   @Test
   public void total() {
      assertEquals(15, CelestialObjects.values().length);
   }

   @Test
   public void getCelObjectForId() {
      assertEquals(CelestialObjects.JUPITER, CelestialObjects.UNKNOWN.getCelObjectForId(7));
   }

   @Test
   public void getCelObjectForIdNotFound() {
      assertEquals(CelestialObjects.UNKNOWN, CelestialObjects.UNKNOWN.getCelObjectForId(7000));
   }

   @Test
   public void getObservableList() {
      assertEquals(15, celBody.getObservableList().size());
   }
}