/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

/**
 * Parent for classes that contain the result of a persistency action.
 */
public abstract class AbstractResult {

   private final DatabaseResults databaseResult;

   public AbstractResult(final DatabaseResults result) {
      this.databaseResult = result;
   }

   public DatabaseResults getDatabaseResult() {
      return databaseResult;
   }
}
