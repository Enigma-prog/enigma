/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Metadata for a chart, name, description etc.
 */
@Getter
public class ChartMetaData implements Serializable {

   private final String name;
   private final String description;
   private final String source;
   private final ChartTypes chartType;
   private final Ratings rating;

   /**
    * Constructor defines all memebers.
    *
    * @param name        Name for the chart.
    * @param description Description oif the hart.
    * @param source      Source of the data.
    * @param chartType   Type of chart.
    * @param rating      Rodden rating.
    */
   public ChartMetaData(final String name, final String description, final String source, final ChartTypes chartType,
                        final Ratings rating) {
      this.name = checkNotNull(name);
      this.description = checkNotNull(description);
      this.source = checkNotNull(source);
      this.chartType = checkNotNull(chartType);
      this.rating = checkNotNull(rating);
   }
}
