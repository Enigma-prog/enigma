/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.be.persistency.daos.PropertyDao;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.be.persistency.results.PropertyListResult;
import com.radixpro.enigma.be.persistency.results.PropertyResult;
import com.radixpro.enigma.shared.Property;

import java.util.ArrayList;
import java.util.List;

public class PersistedPropertyApi {

   private final static String ERROR = "ERROR";
   private final static String OK = "OK";
   private final PropertyDao dao;

   public PersistedPropertyApi() {
      dao = new PropertyDao();
   }

   public String insert(final Property property) {
      final DatabaseResults result = dao.insert(property);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public String update(final Property prop) {
      final DatabaseResults result = dao.update(prop);
      if (result == DatabaseResults.NOT_FOUND) {
         return "NOTFOUND";
      }
      if (result == DatabaseResults.UNKNOWN_ERROR) {
         return ERROR;
      }
      return OK;
   }

   public String delete(final Property prop) {
      final DatabaseResults result = dao.delete(prop);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public Property read(final String key) {
      PropertyResult result = dao.read(key);
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return null;
      } else return result.getProperty();
   }

   public List<Property> readAll() {
      PropertyListResult result = dao.readAll();
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return new ArrayList<>();
      } else return result.getProperties();
   }

}
