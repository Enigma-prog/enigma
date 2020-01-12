/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.dizitart.no2.objects.Id;

import java.io.Serializable;

public class UserDefinedCategory implements Serializable {

   @Id
   private final long id;   // Nitrite Id
   private final String text;

   public UserDefinedCategory(final long id, final String text) {
      this.id = id;
      this.text = text;
   }

   public long getId() {
      return id;
   }

   public String getText() {
      return text;
   }
}
