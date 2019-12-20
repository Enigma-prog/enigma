/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.astron;

import com.radixpro.enigma.be.astron.calcmodel.SePositionResultCelBodies;
import com.radixpro.enigma.be.astron.calcmodel.SePositionResultHouses;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeFrontendTest {

   private final double delta = 0.00000001;
   private final double jdUt = 1234567.89;
   private final int bodyId = 4;
   private final int flags = 1;
   private final double geoLat = 52.0;
   private final double geoLong = 7.0;
   private final char system = 'p';
   private final int nrOfCusps = 12;

   @Test
   public void getFrontend() {
      SeFrontend seFrontend = SeFrontend.getFrontend();
      assertNotNull(seFrontend);
   }

   @Test
   public void getPositionsForCelBody() {
      SePositionResultCelBodies result = SeFrontend.getFrontend().getPositionsForCelBody(jdUt, bodyId, flags);
      assertEquals(148.08135699695939, result.getAllPositions()[0], delta);
   }

   @Test
   public void getPositionsForHouses() {
      SePositionResultHouses result = SeFrontend.getFrontend().getPositionsForHouses(jdUt, flags, geoLat, geoLong,
            system, nrOfCusps);
      assertEquals(59.97963584631173, result.getCusps()[3], delta);
      assertEquals(258.18944437108246, result.getAscMc()[2], delta);
   }
}