/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseResultsTest {

   @Test
   public void total() {
      assertEquals(4, DatabaseResults.values().length);
   }

}