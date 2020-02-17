/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeZonesTest {

   private TimeZones timeZone;

   @Before
   public void setUp() {
      timeZone = TimeZones.AMT;   // id 6, amt, -4.0
   }

   @Test
   public void getId() {
      assertEquals(6, timeZone.getId());
   }

   @Test
   public void getRbKeyForName() {
      assertEquals("timezone.amt", timeZone.getRbKeyForName());
   }

   @Test
   public void timeZoneForId() {
      assertEquals(TimeZones.AMT, timeZone.timeZoneForId(6));
   }

   @Test
   public void timeZoneForIdNotFound() {
      assertEquals(TimeZones.UT, timeZone.timeZoneForId(1000));
   }

   @Test
   public void timeZoneForName() {
      assertEquals(timeZone.ICT, timeZone.timeZoneForName("+07:00: ICT/Indochina Time"));
   }

   @Test
   public void timeZoneForNameNotFound() {
      assertEquals(timeZone.UT, timeZone.timeZoneForName("i do not exist"));
   }

   @Test
   public void getObservableList() {
      var observableList = timeZone.getObservableList();
      assertEquals(33, observableList.size());
   }
}