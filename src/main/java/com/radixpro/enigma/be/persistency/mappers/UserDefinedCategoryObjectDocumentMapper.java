/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.dizitart.no2.Document;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserDefinedCategoryObjectDocumentMapper {

   public Document object2Document(final UserDefinedCategory category) {
      checkNotNull(category);
      return Document.createDocument("_id", category.getId()).put("text", category.getText());
   }

   public UserDefinedCategory document2Object(final Document document) {
      checkNotNull(document);
      long id = (long) document.get("_id");
      String text = (String) document.get("text");
      return new UserDefinedCategory(id, text);
   }


}
