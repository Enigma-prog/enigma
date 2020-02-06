/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import org.dizitart.no2.objects.Id;

public class Configuration {

   @Id
   private final long id;
   private final long parentId;
   private final String name;
   private final String description;
   private final AstronConfiguration astronConfiguration;
   private final DelinConfiguration delinConfiguration;


   public Configuration(final long id, final long parentId, final String name, final String description,
                        final AstronConfiguration astronConfiguration, final DelinConfiguration delinConfiguration) {
      this.id = id;
      this.parentId = parentId;
      this.name = name;
      this.description = description;
      this.astronConfiguration = astronConfiguration;
      this.delinConfiguration = delinConfiguration;
   }

   public long getId() {
      return id;
   }

   public long getParentId() {
      return parentId;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public AstronConfiguration getAstronConfiguration() {
      return astronConfiguration;
   }

   public DelinConfiguration getDelinConfiguration() {
      return delinConfiguration;
   }
}
