/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.shared.presentationmodel.valuetypes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecimalValueTest {

   private DecimalValue decimalValue;
   private double value1 = 12.386759267;
   private double value2 = 0.002;
   private String expectedValue1 = "12.38675926";
   private String expectedValue2 = " 0.00200000";

   @Test
   public void getFormattedPosition() {
      decimalValue = new DecimalValue(value1);
      assertEquals(expectedValue1, decimalValue.getFormattedPosition());
   }

   @Test
   public void getFormattedPositionWithLeadingSpace() {
      decimalValue = new DecimalValue(value2);
      assertEquals(expectedValue2, decimalValue.getFormattedPosition());
   }

}