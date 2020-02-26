/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.dizitart.no2.objects.Id;

@ToString
@Getter
public class Configuration {

   @Id
   private final long id;
   private final long parentId;
   private final String name;
   private final String description;
   private final AstronConfiguration astronConfiguration;
   private final DelinConfiguration delinConfiguration;


   public Configuration(final long id, final long parentId, @NonNull final String name,
                        @NonNull final String description, @NonNull final AstronConfiguration astronConfiguration,
                        @NonNull final DelinConfiguration delinConfiguration) {
      this.id = id;
      this.parentId = parentId;
      this.name = name;
      this.description = description;
      this.astronConfiguration = astronConfiguration;
      this.delinConfiguration = delinConfiguration;
   }

}
