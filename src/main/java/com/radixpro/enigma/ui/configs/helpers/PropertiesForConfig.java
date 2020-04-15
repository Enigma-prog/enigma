/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs.helpers;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import com.radixpro.enigma.xchg.domain.config.Configuration;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PropertiesForConfig {

   private final Configuration config;
   private final CelObjectsInConfig celObjectsInConfig;
   private final AspectsInConfig aspectsInConfig;
   private final Rosetta rosetta;

   public PropertiesForConfig(final Configuration config, final CelObjectsInConfig celObjectsInConfig,
                              final AspectsInConfig aspectsInConfig, Rosetta rosetta) {
      this.config = checkNotNull(config);
      this.celObjectsInConfig = checkNotNull(celObjectsInConfig);
      this.aspectsInConfig = checkNotNull(aspectsInConfig);
      this.rosetta = checkNotNull(rosetta);
   }

   public List<PresentableProperty> getProperties() {
      return createProperties();
   }

   private List<PresentableProperty> createProperties() {
      List<PresentableProperty> properties = new ArrayList<>();
      // TODO use rbNames for properties
      properties.add(new PresentableProperty("Name", config.getName()));
      properties.add(new PresentableProperty("Description", config.getDescription()));
      properties.add(new PresentableProperty("Housesystem", rosetta.getText(config.getAstronConfiguration().getHouseSystem().getNameForRB())));
      properties.add(new PresentableProperty("Observer position", rosetta.getText(config.getAstronConfiguration().getObserverPosition().getNameForRB())));
      properties.add(new PresentableProperty("Ecliptic projection", rosetta.getText(config.getAstronConfiguration().getEclipticProjection().getNameForRB())));
      properties.add(new PresentableProperty("Ayanamsha", rosetta.getText(config.getAstronConfiguration().getAyanamsha().getNameForRB())));
      properties.add(new PresentableProperty("Celestial objects and points", ""));
      properties.addAll(celObjectsInConfig.constructProperties(config.getAstronConfiguration().getCelObjects()));

      // add aspects
      properties.add(new PresentableProperty("Aspects", ""));
      properties.add(new PresentableProperty("Aspect orb structure", config.getDelinConfiguration().getAspectConfiguration().getOrbStructure().name()));
      properties.add(new PresentableProperty("Aspect base orb", Double.toString(config.getDelinConfiguration().getAspectConfiguration().getBaseOrb())));
      properties.add(new PresentableProperty("Draw ingoing/outgoing", config.getDelinConfiguration().getAspectConfiguration().isDrawInOutGoing() ? "Yes" : "No"));
      properties.addAll(aspectsInConfig.constructProperties(config.getDelinConfiguration().getAspectConfiguration().getAspects()));
      return properties;
   }
}
