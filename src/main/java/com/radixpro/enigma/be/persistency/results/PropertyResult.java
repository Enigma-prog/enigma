/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.shared.Property;

public class PropertyResult extends AbstractResult {

   private final Property property;

   /**
    * Validated result that contains a property.
    */
   public PropertyResult(final Property property, final DatabaseResults result) {
      super(result);
      this.property = property;
   }

   public Property getProperty() {
      return property;
   }
}
