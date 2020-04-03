/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

/**
 * Persistable category for a chart, the content is defined by the user.
 */
@Getter
@ToString
public class UserDefinedCategory implements Serializable {

   @Id
   private final long id;   // Nitrite Id
   private final String text;

   /**
    * Constructor defines all members.
    *
    * @param id   The id for the category
    * @param text The text for the category (defined by the user).
    */
   public UserDefinedCategory(final long id, @NonNull final String text) {
      this.id = id;
      this.text = text;
   }

}
