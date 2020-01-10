/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;

/**
 * Validated result from querying database for a UserDefinedCategory.
 */
public class UserDefinedCategoryResult extends AbstractResult {

   private final UserDefinedCategory userDefinedCategory;

   public UserDefinedCategoryResult(final UserDefinedCategory cat, final DatabaseResults result) {
      super(result);
      this.userDefinedCategory = cat;
   }

   public UserDefinedCategory getUserDefinedCategory() {
      return userDefinedCategory;
   }
}
