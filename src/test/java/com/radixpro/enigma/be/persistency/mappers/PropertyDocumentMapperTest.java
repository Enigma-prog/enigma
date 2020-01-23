/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.shared.Property;
import org.dizitart.no2.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyDocumentMapperTest {

   private final long id = 2L;
   private final String key = "thisKey";
   private final String value = "thisValue";
   private PropertyDocumentMapper mapper;

   @Before
   public void setUp() throws Exception {
      mapper = new PropertyDocumentMapper();
   }

   @Test
   public void object2Document() {
      Document doc = mapper.object2Document(createObject());
      assertEquals(id, doc.get("_id"));
      assertEquals(key, (String) doc.get("key"));
      assertEquals(value, (String) doc.get("value"));
   }

   @Test
   public void document2Object() {
      Property prop = mapper.document2Object(createDocument());
      assertEquals(id, prop.getId());
      assertEquals(key, prop.getKey());
      assertEquals(value, prop.getValue());
   }

   private Document createDocument() {
      return Document.createDocument("_id", id).put("key", key).put("value", value);
   }

   private Property createObject() {
      return new Property(id, key, value);
   }

}