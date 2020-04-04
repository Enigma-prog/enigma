/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel;

import lombok.Getter;

/**
 * Enables the use of a property and its value in a TableView.
 */
@Getter
public class PresentableProperty {

   private final String name;
   private final String value;

   /**
    * Constructor defines all members.
    *
    * @param name  Name of the property.
    * @param value Value of the property.
    */
   public PresentableProperty(final String name, final String value) {
      this.name = name;
      this.value = value;
   }
}
