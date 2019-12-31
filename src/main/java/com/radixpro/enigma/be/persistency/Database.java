/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class Database {
   private static final String FILEPATH = "./db/enigma.db";

   public Database() {
      ObjectRepository<PersistableConfiguration> repo;
      try (Nitrite enigmadb = Nitrite.builder().compressed().filePath(FILEPATH).openOrCreate()) {
         repo = enigmadb.getRepository("key", PersistableConfiguration.class);
      }
      PersistableConfiguration config = new PersistableConfiguration();
      config.setAspectBaseOrb(8.0);
      config.setAspectOrbStruc(0);
      config.setAspectOrbType(1);
      config.setAyanamsha(0);
      config.setCelObjects(new int[]{6, 7, 8, 9});
      config.setEclProjection(0);
      config.setHouseSystem(4);
      config.setId(1);
      config.setObserverPos(0);
      config.setParentId(0);
      repo.insert(config);

      PersistableConfiguration config2 = repo.find(eq("id", 1)).firstOrDefault();
      System.out.println(config2.getAspectBaseOrb());
   }


}
