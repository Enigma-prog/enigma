/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.validation;

import com.radixpro.enigma.xchg.api.PersistedChartDataApi;
import com.radixpro.enigma.xchg.domain.ChartData;

import java.util.List;

public class ValidatedChartName extends ValidatedInput {

   private String nameText;

   public ValidatedChartName(final String input) {
      super(input);
      validate();
   }

   @Override
   protected void validate() {
      validated = true;
      nameText = input.trim();
      if (nameText.length() < 1) validated = false;
      else {
         var api = new PersistedChartDataApi();
         List<ChartData> existingChart = api.search(nameText);
         if (existingChart.size() > 0) validated = false;
      }
   }

   public String getNameText() {
      return nameText;
   }
}
