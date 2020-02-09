/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.versions;

import com.radixpro.enigma.shared.Property;
import com.radixpro.enigma.xchg.api.PersistedConfigurationApi;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Updater {

   private static final Logger LOG = Logger.getLogger(VersionController.class);
   private final PersistedPropertyApi propApi;

   public Updater(final PersistedPropertyApi propApi) {
      this.propApi = propApi;
   }

   public void performFullUpdate() {
      updateStep20201();
      LOG.info("Full initialization completed.");
   }


   private void updateStep20201() {  // initial version
      LOG.info("Updating to version 2020.1");
      propApi.insert(new Property(1L, "version", "2020.1"));
      propApi.insert(new Property(2L, "lang", "en"));
      propApi.insert(new Property(3L, "config", "1"));
      LOG.info("Inserted properties for 2020.1");

      final List<ConfiguredCelObject> celObjects = new ArrayList<>();
      celObjects.add(new ConfiguredCelObject(CelestialObjects.SUN, "a", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.MOON, "b", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.MERCURY, "c", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.VENUS, "d", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.MARS, "f", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.JUPITER, "g", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.SATURN, "h", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.URANUS, "i", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.NEPTUNE, "j", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.PLUTO, "k", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.CHEIRON, "w", 100.0, true));
      celObjects.add(new ConfiguredCelObject(CelestialObjects.MEAN_NODE, "{", 100.0, true));
      AstronConfiguration astronConfiguration = new AstronConfiguration(HouseSystems.PLACIDUS, Ayanamshas.NONE,
            EclipticProjections.TROPICAL, ObserverPositions.GEOCENTRIRC, celObjects);

      final List<ConfiguredAspect> aspects = new ArrayList<>();
      aspects.add(new ConfiguredAspect(Aspects.CONJUNCTION, 100, "B", true));
      aspects.add(new ConfiguredAspect(Aspects.OPPOSITION, 100, "C", true));
      aspects.add(new ConfiguredAspect(Aspects.TRIANGLE, 85, "D", true));
      aspects.add(new ConfiguredAspect(Aspects.SQUARE, 85, "E", true));
      aspects.add(new ConfiguredAspect(Aspects.SEXTILE, 60, "F", true));
      aspects.add(new ConfiguredAspect(Aspects.PARALLEL, 15, "O", false));
      aspects.add(new ConfiguredAspect(Aspects.CONTRAPARALLEL, 15, "P", false));
      aspects.add(new ConfiguredAspect(Aspects.INCONJUNCT, 25, "H", true));

      AspectConfiguration aspectConfiguration = new AspectConfiguration(aspects, 8.0, AspectOrbStructure.ASPECT, false);
      DelinConfiguration delinConfiguration = new DelinConfiguration(aspectConfiguration);

      Configuration standardWestern = new Configuration(1, 0, "Western standard", "Standard for western astrology",
            astronConfiguration, delinConfiguration);

      new PersistedConfigurationApi().insert(standardWestern);
      LOG.info("Inserted configurations for 2020.1");
   }


}
