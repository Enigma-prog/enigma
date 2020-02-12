/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.Serializable;
import java.util.List;

public class ChartMetaData implements Serializable {

   // todo add entered location
   private final String name;
   private final String description;
   private final String source;
   private final String sex;  // todo remove sex, should be paprt of charttype
   private final List<Integer> categories;  // todo move categories to another object, should be added to existing charts.
   private final ChartTypes chartType;
   private final Ratings rating;

   public ChartMetaData(final String name, final String description, final String source, final String sex,
                        final List<Integer> categories, final ChartTypes chartType, final Ratings rating) {
      this.name = name;
      this.description = description;
      this.source = source;
      this.sex = sex;
      this.categories = categories;
      this.chartType = chartType;
      this.rating = rating;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public String getSource() {
      return source;
   }

   public String getSex() {
      return sex;
   }

   public List<Integer> getCategories() {
      return categories;
   }

   public ChartTypes getChartType() {
      return chartType;
   }

   public Ratings getRating() {
      return rating;
   }
}
