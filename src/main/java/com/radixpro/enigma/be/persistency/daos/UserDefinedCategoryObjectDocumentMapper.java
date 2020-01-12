/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.dizitart.no2.Document;

public class UserDefinedCategoryObjectDocumentMapper {

   public Document object2Document(final UserDefinedCategory category) {
      return Document.createDocument("_id", category.getId()).put("text", category.getText());
   }

   public UserDefinedCategory document2Object(final Document document) {
      long id = (long) document.get("_id");
      var text = (String) document.get("text");
      return new UserDefinedCategory(id, text);
   }


}
