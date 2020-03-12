/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.exceptions.DatabaseException;
import com.radixpro.enigma.be.persistency.daos.ChartDataDao;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.xchg.domain.ChartData;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PersistedChartDataApi {

   private final ChartDataDao dao;

   public PersistedChartDataApi() {
      dao = new ChartDataDao();
   }

   public void insert(@NonNull final ChartData chartData) {
      try {
         dao.insert(chartData);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void update(@NonNull final ChartData chartData) {
      try {
         dao.update(chartData);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public void delete(@NonNull final ChartData chartData) {
      try {
         dao.delete(chartData);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
   }

   public List<ChartData> read(final long id) {
      List<ChartData> chartDataResult = new ArrayList<>();
      try {
         chartDataResult = dao.read(id);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return chartDataResult;
   }

   public List<ChartData> readAll() {
      List<ChartData> chartDataResult = new ArrayList<>();
      try {
         chartDataResult = dao.readAll();
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return chartDataResult;
   }

   public List<ChartData> search(@NonNull final String searchName) {
      List<ChartData> chartDataResult = new ArrayList<>();
      try {
         chartDataResult = dao.search(searchName);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return chartDataResult;
   }

   public List<ChartData> searchWildCard(@NonNull final String searchArgument) {
      List<ChartData> chartDataResult = new ArrayList<>();
      try {
         chartDataResult = dao.searchWildCard(searchArgument);
      } catch (DatabaseException de) {
         new FailFastHandler().terminate(de.getMessage());
      }
      return chartDataResult;
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
