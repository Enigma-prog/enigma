/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.config.Configuration;

import java.util.List;

/**
 * Validated result from querying database for Configurations.
 */
public class ConfigurationResult extends AbstractResult {

   private final List<Configuration> configurations;

   public ConfigurationResult(final List<Configuration> configurations, final DatabaseResults result) {
      super(result);
      this.configurations = configurations;
   }

   public List<Configuration> getConfigurations() {
      return configurations;
   }
}

