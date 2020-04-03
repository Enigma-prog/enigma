/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import com.radixpro.enigma.xchg.domain.EnigmaProperties;
import org.dizitart.no2.Nitrite;

import java.io.File;

/**
 * Port to the Nitrite database.
 */
public class EnigmaDatabase {

   private static final String PROPERTIES = "app";
   private static final String PROP_DB = "database";
   private final String dbFilePath;

   /**
    * Constructor defines the location of the database.
    */
   public EnigmaDatabase() {
      dbFilePath = new EnigmaProperties(PROPERTIES).getProperties().getProperty(PROP_DB);
   }

   /**
    * Opens the database and returns an instance of Nitrite: a handler to access tyhe database.
    *
    * @return
    */
   public Nitrite openDatabase() {
      return Nitrite.builder().filePath(dbFilePath).openOrCreate();
   }

   /**
    * Checks if the database exists.
    *
    * @return True if the database exists, otherwise false.
    */
   public boolean databaseFound() {
      File dbFile = new File(dbFilePath);
      return (dbFile.exists() && !dbFile.isDirectory());
   }
}
