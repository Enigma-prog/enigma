/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.results;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;

import java.util.List;

/**
 * Validated result from querying database for a list of UserDefinedCategory.
 */
public class UserDefinedCategoryListResult extends AbstractResult {

   private final List<UserDefinedCategory> categories;

   public UserDefinedCategoryListResult(final List<UserDefinedCategory> categories, final DatabaseResults result) {
      super(result);
      this.categories = categories;
   }

   public List<UserDefinedCategory> getCategories() {
      return categories;
   }
}
