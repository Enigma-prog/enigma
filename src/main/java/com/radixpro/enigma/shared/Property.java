/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

public class Property {

   private final long id;
   private final String key;
   private final String value;

   public Property(final long id, final String key, final String value) {
      this.id = id;
      this.key = key;
      this.value = value;
   }

   public long getId() {
      return id;
   }

   public String getKey() {
      return key;
   }

   public String getValue() {
      return value;
   }

}
