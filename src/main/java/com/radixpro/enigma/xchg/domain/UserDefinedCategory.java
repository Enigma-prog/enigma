/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.dizitart.no2.objects.Id;

public class UserDefinedCategory {

   @Id
   private final int id;   // Nitrite Id
   private final String text;

   /**
    * Default constructor required for persistency.
    */
   public UserDefinedCategory() {
      this.id = -1;
      this.text = "";
   }

   public UserDefinedCategory(final int id, final String text) {
      this.id = id;
      this.text = text;
   }

   public int getId() {
      return id;
   }

   public String getText() {
      return text;
   }
}
