/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.Configuration;

/**
 * Validated result from querying database for Configurations.
 */
public class ConfigurationResult extends AbstractResult {

   private final Configuration config;

   public ConfigurationResult(final Configuration config, final DatabaseResults result) {
      super(result);
      this.config = config;
   }

   public Configuration getConfig() {
      return config;
   }
}
