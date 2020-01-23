/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.shared.Property;
import org.dizitart.no2.Document;

public class PropertyDocumentMapper {

   public Document object2Document(final Property prop) {
      return Document.createDocument("_id", prop.getId()).put("key", prop.getKey()).put("value", prop.getValue());
   }

   public Property document2Object(final Document document) {
      var id = (Long) document.get("_id");
      var key = (String) document.get("key");
      var value = (String) document.get("value");
      return new Property(id, key, value);
   }

}
