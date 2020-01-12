/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import com.radixpro.enigma.xchg.domain.AppProperties;
import org.dizitart.no2.Nitrite;

public class EnigmaDatabase {

   private final String dbFilePath;

   public EnigmaDatabase() {
      dbFilePath = new AppProperties().getProperties().getProperty("database");
   }

   public Nitrite openDatabase() {
      return Nitrite.builder().filePath(dbFilePath).openOrCreate();
   }

}
