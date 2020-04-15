/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dizitart.no2.objects.Id;

import static com.google.common.base.Preconditions.checkNotNull;

@ToString
@Getter
@Setter
public class Configuration {

   @Id
   private long id;
   private long parentId;
   private String name;
   private String description;
   private AstronConfiguration astronConfiguration;
   private DelinConfiguration delinConfiguration;


   public Configuration(final long id, final long parentId, final String name, final String description,
                        final AstronConfiguration astronConfiguration, final DelinConfiguration delinConfiguration) {
      this.id = id;
      this.parentId = parentId;
      this.name = checkNotNull(name);
      this.description = checkNotNull(description);
      this.astronConfiguration = checkNotNull(astronConfiguration);
      this.delinConfiguration = checkNotNull(delinConfiguration);
   }



}
