/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.persistency.daos.ConfigurationDao;
import com.radixpro.enigma.be.persistency.results.ConfigurationResult;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.xchg.domain.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class PersistedConfigurationApi {

   private final static String ERROR = "ERROR";
   private final static String OK = "OK";
   private final ConfigurationDao dao;

   public PersistedConfigurationApi() {
      dao = new ConfigurationDao();
   }

   public String insert(final Configuration configuration) {
      final DatabaseResults result = dao.insert(configuration);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public String update(final Configuration configuration) {
      final DatabaseResults result = dao.update(configuration);
      if (result == DatabaseResults.NOT_FOUND) {
         return "NOTFOUND";
      }
      if (result == DatabaseResults.UNKNOWN_ERROR) {
         return ERROR;
      }
      return OK;
   }

   public String delete(final Configuration configuration) {
      final DatabaseResults result = dao.delete(configuration);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public List<Configuration> read(final int id) {
      ConfigurationResult result = dao.read(id);
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return null;
      }
      return result.getConfigurations();
   }

   public List<Configuration> search(final String searchName) {
      ConfigurationResult result = dao.search(searchName);
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return null;
      }
      return result.getConfigurations();
   }

   public List<Configuration> readAll() {
      ConfigurationResult result = dao.readAll();
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return new ArrayList<>();
      }
      return result.getConfigurations();
   }

   public long getMaxId() {
      long maxId = dao.getMaxId();
      if (maxId < 0) {
         // todo: throw exception
         return -1;
      }
      return maxId;
   }


}

