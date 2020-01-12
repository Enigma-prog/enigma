/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.daos;

import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import org.dizitart.no2.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDefinedCategoryObjectDocumentMapperTest {

   private final long id = 5;
   private final String text = "Five";
   private UserDefinedCategoryObjectDocumentMapper mapper;

   @Before
   public void setUp() {
      mapper = new UserDefinedCategoryObjectDocumentMapper();
   }

   @Test
   public void object2Document() {
      Document doc = mapper.object2Document(createObject());
      assertEquals(id, (long) doc.get("_id"));
      assertEquals(text, doc.get("text"));
   }

   @Test
   public void document2Object() {
      UserDefinedCategory cat = mapper.document2Object(createDocument());
      assertEquals(id, cat.getId());
      assertEquals(text, cat.getText());
   }

   private Document createDocument() {
      return Document.createDocument("_id", id).put("text", text);
   }

   private UserDefinedCategory createObject() {
      return new UserDefinedCategory(id, text);
   }

}