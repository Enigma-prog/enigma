/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.shared.Property;
import lombok.val;
import org.dizitart.no2.Document;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class PropertyDocumentMapper {

   public Document object2Document(final Property prop) {
      checkNotNull(prop);
      return Objects.requireNonNull(Document.createDocument("_id", prop.getId()).put("key", prop.getKey())).put("value", prop.getValue());
   }

   public Property document2Object(final Document document) {
      checkNotNull(document);
      val id = (Long) document.get("_id");
      val key = (String) document.get("key");
      val value = (String) document.get("value");
      return new Property(id, key, value);
   }

}
