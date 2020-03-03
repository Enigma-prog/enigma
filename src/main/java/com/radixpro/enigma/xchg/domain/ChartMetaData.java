/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Getter
public class ChartMetaData implements Serializable {

   private final String name;
   private final String description;
   private final String source;
   private final ChartTypes chartType;
   private final Ratings rating;

   public ChartMetaData(@NonNull final String name, @NonNull final String description, @NonNull final String source,
                        @NonNull final ChartTypes chartType, @NonNull final Ratings rating) {
      this.name = name;
      this.description = description;
      this.source = source;
      this.chartType = chartType;
      this.rating = rating;
   }
}
