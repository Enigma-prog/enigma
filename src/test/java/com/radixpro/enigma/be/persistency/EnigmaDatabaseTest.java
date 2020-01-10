/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import org.dizitart.no2.Nitrite;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class EnigmaDatabaseTest {

   private EnigmaDatabase enigmaDatabase;

   @Before
   public void setUp() {
      enigmaDatabase = new EnigmaDatabase();
   }

   @Test
   public void openDatabase() {
      Nitrite enigmaDb = enigmaDatabase.openDatabase();
      assertFalse(enigmaDb.isClosed());
      enigmaDb.close();                      // clean-up
   }

}