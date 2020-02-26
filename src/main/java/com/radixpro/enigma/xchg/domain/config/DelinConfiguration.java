/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain.config;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Getter
public class DelinConfiguration implements Serializable {

   private final AspectConfiguration aspectConfiguration;

   public DelinConfiguration(@NonNull final AspectConfiguration aspectConfiguration) {
      this.aspectConfiguration = aspectConfiguration;
   }

}
