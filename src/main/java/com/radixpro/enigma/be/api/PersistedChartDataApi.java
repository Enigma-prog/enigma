/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.api;

import com.radixpro.enigma.be.persistency.daos.ChartDataDao;
import com.radixpro.enigma.be.persistency.results.ChartDataListResult;
import com.radixpro.enigma.be.persistency.results.ChartDataResult;
import com.radixpro.enigma.be.persistency.results.DatabaseResults;
import com.radixpro.enigma.xchg.domain.ChartData;

import java.util.ArrayList;
import java.util.List;

public class PersistedChartDataApi {

   private final static String ERROR = "ERROR";
   private final static String OK = "OK";
   private final ChartDataDao dao;

   public PersistedChartDataApi() {
      dao = new ChartDataDao();
   }

   public String insert(final ChartData chartData) {
      final DatabaseResults result = dao.insert(chartData);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public String update(final ChartData chartData) {
      final DatabaseResults result = dao.update(chartData);
      if (result == DatabaseResults.NOT_FOUND) {
         return "NOTFOUND";
      }
      if (result == DatabaseResults.UNKNOWN_ERROR) {
         return ERROR;
      }
      return OK;
   }

   public String delete(final ChartData chartData) {
      final DatabaseResults result = dao.delete(chartData);
      if (result != DatabaseResults.OK) {
         // TODO handle result, use db error messages in RB
         return ERROR;
      }
      return OK;
   }

   public ChartData read(final int id) {
      ChartDataResult result = dao.read(id);
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return null;
      } else return result.getChartData();
   }

   public List<ChartData> readAll() {
      ChartDataListResult result = dao.readAll();
      if (result.getDatabaseResult() != DatabaseResults.OK) {
         // todo: throw exception
         return new ArrayList<>();
      } else return result.getChartDataList();
   }


}
