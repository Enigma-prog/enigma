/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.daos.UserDefinedCategoryDao;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.xchg.domain.UserDefinedCategory;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PersistedUserDefinedCategoryApi {
   private final UserDefinedCategoryDao dao;

   public PersistedUserDefinedCategoryApi() {
      dao = new UserDefinedCategoryDao();
   }

   public void insert(@NonNull final UserDefinedCategory category) {
      try {
         dao.insert(category);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void update(@NonNull final UserDefinedCategory category) {
      try {
         dao.update(category);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void delete(@NonNull final UserDefinedCategory category) {
      try {
         dao.delete(category);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public List<UserDefinedCategory> read(final int id) {
      List<UserDefinedCategory> categories = new ArrayList<>();
      try {
         categories = dao.read(id);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return categories;
   }

   public List<UserDefinedCategory> search(@NonNull final String searchText) {
      List<UserDefinedCategory> categories = new ArrayList<>();
      try {
         categories = dao.search(searchText);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return categories;
   }


   public List<UserDefinedCategory> readAll() {
      List<UserDefinedCategory> categories = new ArrayList<>();
      try {
         categories = dao.readAll();
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return categories;
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
