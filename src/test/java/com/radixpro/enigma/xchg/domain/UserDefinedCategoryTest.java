/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDefinedCategoryTest {

   private final int id = 13;
   private final String text = "myText";
   private UserDefinedCategory category;

   @Before
   public void setUp() {
      category = new UserDefinedCategory(id, text);
   }

   @Test
   public void getId() {
      assertEquals(id, category.getId());
   }

   @Test
   public void getText() {
      assertEquals(text, category.getText());
   }
}