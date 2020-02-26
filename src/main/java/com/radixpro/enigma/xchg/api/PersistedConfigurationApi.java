/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.daos.ConfigurationDao;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.xchg.domain.config.Configuration;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PersistedConfigurationApi {

   private final ConfigurationDao dao;

   public PersistedConfigurationApi() {
      dao = new ConfigurationDao();
   }

   public void insert(@NonNull final Configuration configuration) {
      try {
         dao.insert(configuration);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void update(@NonNull final Configuration configuration) {
      try {
         dao.update(configuration);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void delete(@NonNull final Configuration configuration) {
      try {
         dao.delete(configuration);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public List<Configuration> read(final int id) {
      List<Configuration> configs = new ArrayList<>();
      try {
         configs = dao.read(id);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return configs;
   }

   public List<Configuration> search(@NonNull final String searchName) {
      List<Configuration> configs = new ArrayList<>();
      try {
         configs = dao.search(searchName);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return configs;
   }

   public List<Configuration> readAll() {
      List<Configuration> configs = new ArrayList<>();
      try {
         configs = dao.readAll();
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return configs;
   }

   public long getMaxId() {
      long maxId = -1L;
      try {
         maxId = dao.getMaxId();
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return maxId;
   }

}

