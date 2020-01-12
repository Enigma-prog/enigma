/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.dizitart.no2.objects.Id;

import java.io.Serializable;


public class Configuration implements Serializable {

   @Id
   private final long id;
   private final int parentId;
   private final ConfigAstron configAstron;
   private final ConfigDelin configDelin;


   public Configuration(final long id, final int parentId, final ConfigAstron configAstron, final ConfigDelin cOnfigDelin) {
      this.id = id;
      this.parentId = parentId;
      this.configAstron = configAstron;
      this.configDelin = cOnfigDelin;
   }

   public long getId() {
      return id;
   }

   public int getParentId() {
      return parentId;
   }

   public ConfigAstron getConfigAstron() {
      return configAstron;
   }

   public ConfigDelin getConfigDelin() {
      return configDelin;
   }
}
