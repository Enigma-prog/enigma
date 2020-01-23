/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.shared.Property;

import java.util.List;

/**
 * Validated result from querying database for a list of Properties.
 */
public class PropertyListResult extends AbstractResult {

   private final List<Property> properties;

   public PropertyListResult(final List<Property> properties, final DatabaseResults result) {
      super(result);
      this.properties = properties;
   }

   public List<Property> getProperties() {
      return properties;
   }
}


