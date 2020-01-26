/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.versions;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.shared.EnigmaDictionary;
import com.radixpro.enigma.shared.Property;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import org.apache.log4j.Logger;

public class VersionController {

   private final static Logger LOG = Logger.getLogger(VersionController.class);
   private final EnigmaDatabase dataBase;
   private final Updater updater;

   public VersionController(final EnigmaDatabase dataBase, final Updater updater) {
      this.dataBase = dataBase;
      this.updater = updater;
   }

   public void checkAndUpdate() {
      performCheckedInitialisation();
   }

   private boolean doesDatabaseExist() {
      return dataBase.databaseFound();
   }

   private void performFullInitialisation() {
      updater.performFullUpdate();

   }

   private void performCheckedInitialisation() {
      PersistedPropertyApi api = new PersistedPropertyApi();
      Property versionProp = api.read("version");
      String dbVersion = versionProp.getValue();
      String codeVersion = EnigmaDictionary.VERSION;
      LOG.info("Current version of code : " + codeVersion + " . Current version of database : " + dbVersion);
      if (codeVersion.compareTo(dbVersion) > 0) {  // code has a higher version than the database
         // TODO perform checks per version
      }


   }

}
