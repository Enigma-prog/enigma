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

public class VersionController {

   private final EnigmaDatabase dataBase;
   private final Updater updater;

   public VersionController(final EnigmaDatabase dataBase, final Updater updater) {
      this.dataBase = dataBase;
      this.updater = updater;
   }

   public void checkAndUpdate() {
      performFullInitialisation();
//      if (!doesDatabaseExist()) c;
//      else performCheckedInitialisation();
//      performCheckedInitialisation();
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
      if (codeVersion.compareTo(dbVersion) > 0) {  // code has a higher version than the database
         // TODO perform checks per version
      }


   }

}
