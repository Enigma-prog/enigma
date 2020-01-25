/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import com.radixpro.enigma.xchg.domain.EnigmaProperties;
import org.dizitart.no2.Nitrite;

import java.io.File;

public class EnigmaDatabase {

   private static final String PROPERTIES = "app";
   private static final String PROP_DB = "database";
   private final String dbFilePath;

   public EnigmaDatabase() {
      dbFilePath = new EnigmaProperties(PROPERTIES).getProperties().getProperty(PROP_DB);
   }

   public Nitrite openDatabase() {
      return Nitrite.builder().filePath(dbFilePath).openOrCreate();
   }

   public boolean databaseFound() {
      File dbFile = new File(dbFilePath);
      return (dbFile.exists() && !dbFile.isDirectory());
   }
}
