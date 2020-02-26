/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.persistency.EnigmaDatabase;
import com.radixpro.enigma.be.versions.Updater;
import com.radixpro.enigma.be.versions.VersionController;
import lombok.val;

/**
 * Checks for the use of the correct version and updates the current version if required.
 */
public class VersionApi {

   private final VersionController controller;

   public VersionApi() {
      val propApi = new PersistedPropertyApi();
      val updater = new Updater(propApi);
      val database = new EnigmaDatabase();
      this.controller = new VersionController(database, updater);
   }

   public void checkAndUpdate() {
      controller.checkAndUpdate();
   }

}
