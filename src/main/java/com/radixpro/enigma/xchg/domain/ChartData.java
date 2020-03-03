/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.dizitart.no2.objects.Id;

@ToString
@Getter
public class ChartData {

   @Id
   private final long id;
   private final FullDateTime fullDateTime;
   private final Location location;
   private final ChartMetaData chartMetaData;

   public ChartData(final long id, @NonNull final FullDateTime fullDateTime,
                    @NonNull final Location location, @NonNull final ChartMetaData chartMetaData) {
      this.id = id;
      this.fullDateTime = fullDateTime;
      this.location = location;
      this.chartMetaData = chartMetaData;
   }

}
