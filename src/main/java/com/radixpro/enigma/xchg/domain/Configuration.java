/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.dizitart.no2.objects.Id;

public class Configuration {

   @Id
   private final long id;
   private final long parentId;
   private final String name;
   private final String description;
   private final ConfigAstron configAstron;
   private final ConfigDelin configDelin;


   public Configuration(final long id, final long parentId, final String name, final String description,
                        final ConfigAstron configAstron, final ConfigDelin configDelin) {
      this.id = id;
      this.parentId = parentId;
      this.name = name;
      this.description = description;
      this.configAstron = configAstron;
      this.configDelin = configDelin;
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

   public ConfigAstron getConfigAstron() {
      return configAstron;
   }

   public ConfigDelin getConfigDelin() {
      return configDelin;
   }
}
