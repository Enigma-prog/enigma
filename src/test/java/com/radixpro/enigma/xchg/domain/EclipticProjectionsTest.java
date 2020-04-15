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

public class EclipticProjectionsTest {

   private EclipticProjections projection;

   @Before
   public void setUp() {
      new RosettaPreparer().setRosetta();
      projection = EclipticProjections.SIDEREAL;
   }

   @Test
   public void getId() {
      assertEquals(2, projection.getId());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("eclipticprojections.sidereal", projection.getNameForRB());
   }

   @Test
   public void getProjectionForId() {
      assertEquals(EclipticProjections.TROPICAL, projection.getProjectionForId(1));
   }

   @Test
   public void getProjectionForIdNotFound() {
      assertEquals(EclipticProjections.UNKNOWN, projection.getProjectionForId(1000));
   }

   @Test
   public void getObservableList() {
      assertEquals(3, projection.getObservableList().size());
   }
}