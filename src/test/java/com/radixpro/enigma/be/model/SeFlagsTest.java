/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.model;

import com.radixpro.enigma.be.model.SeFlags;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeFlagsTest {

   private SeFlags seFlags;

   @Before
   public void setUp() {
      seFlags = SeFlags.EQUATORIAL;
   }

   @Test
   public void getSeValue() {
      assertEquals(2048L, seFlags.getSeValue());
   }
}
