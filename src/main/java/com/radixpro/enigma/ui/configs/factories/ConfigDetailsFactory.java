/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs.factories;

import com.radixpro.enigma.ui.configs.AspectsInConfig;
import com.radixpro.enigma.ui.configs.CelObjectsInConfig;
import com.radixpro.enigma.ui.configs.ConfigDetails;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import lombok.NonNull;

/**
 * Factory for ConfigDetails.
 */
public class ConfigDetailsFactory {

   /**
    * Build instance of ConfigDetails.
    *
    * @param config the configuration.
    * @return the instantiated ConfigDetails.
    */
   public ConfigDetails createConfigDetails(@NonNull final Configuration config) {
      return new ConfigDetails(config, new CelObjectsInConfig(), new AspectsInConfig());
   }
}
