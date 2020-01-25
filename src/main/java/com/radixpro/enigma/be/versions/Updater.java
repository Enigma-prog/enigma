/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.versions;

import com.radixpro.enigma.shared.Property;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;

public class Updater {

   private final PersistedPropertyApi propApi;

   public Updater(final PersistedPropertyApi propApi) {
      this.propApi = propApi;
   }

   public void performFullUpdate() {
      updateStep2020_1();
   }


   private void updateStep2020_1() {  // initial version
      propApi.insert(new Property(1L, "version", "2020.1"));
      propApi.insert(new Property(2L, "lang", "en"));
      // TODO insert standard configurations

   }


}
