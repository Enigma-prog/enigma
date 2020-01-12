/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.be.persistency.daos.UserDefinedCategoryDao;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.be.persistency.results.UserDefinedCategoryListResult;
import com.radixpro.enigma.be.persistency.results.UserDefinedCategoryResult;
import com.radixpro.enigma.xchg.domain.UserDefinedCategory;

import java.util.ArrayList;
import java.util.List;

public class PersistedUserDefinedCategoryApi {

   private final static String ERROR = "ERROR";
   private final static String OK = "OK";
   private final UserDefinedCategoryDao dao;

   public PersistedUserDefinedCategoryApi() {
      dao = new UserDefinedCategoryDao();
   }

   public String insert(final UserDefinedCategory category) {
      final DatabaseResults result = dao.insert(category);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public String update(final UserDefinedCategory category) {
      final DatabaseResults result = dao.update(category);
      if (result == DatabaseResults.NOT_FOUND) {
         return "NOTFOUND";
      }
      if (result == DatabaseResults.UNKNOWN_ERROR) {
         return ERROR;
      }
      return OK;
   }

   public String delete(final UserDefinedCategory category) {
      final DatabaseResults result = dao.delete(category);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public UserDefinedCategory read(final int id) {
      UserDefinedCategoryResult result = dao.read(id);
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return null;
      } else return result.getUserDefinedCategory();
   }

   public List<UserDefinedCategory> readAll() {
      UserDefinedCategoryListResult result = dao.readAll();
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return new ArrayList<>();
      } else return result.getCategories();
   }

}
