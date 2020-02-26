/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.daos.PropertyDao;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.shared.Property;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PersistedPropertyApi {

   private final PropertyDao dao;

   public PersistedPropertyApi() {
      dao = new PropertyDao();
   }

   public void insert(@NonNull final Property property) {
      try {
         dao.insert(property);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void update(@NonNull final Property prop) {
      try {
         dao.update(prop);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void delete(@NonNull final Property prop) {
      try {
         dao.delete(prop);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public List<Property> read(@NonNull final String key) {
      List<Property> propList = new ArrayList<>();
      try {
         propList = dao.read(key);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return propList;
   }

   public List<Property> readAll() {
      List<Property> propList = new ArrayList<>();
      try {
         propList = dao.readAll();
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return propList;
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
