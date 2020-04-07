/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import com.radixpro.enigma.xchg.domain.CelObjectCategory;
import com.radixpro.enigma.xchg.domain.config.ConfiguredCelObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview of celestial objects in a configuration.
 */
public class CelObjectsInConfig {

   private static final Logger LOG = Logger.getLogger(CelObjectsInConfig.class);
   @Getter
   private List<PresentableProperty> presentableProperties;

   public CelObjectsInConfig(@NonNull final List<ConfiguredCelObject> celObjects) {
      presentableProperties = new ArrayList<>();
      constructProperties(celObjects);
   }

   private void constructProperties(@NonNull final List<ConfiguredCelObject> celObjects) {
      val rosetta = Rosetta.getRosetta();
      val classicCelObjectsAsText = new StringBuilder();
      val modernCelObjectsAsText = new StringBuilder();
      val extraplutCelObjectsAsText = new StringBuilder();
      val asteroidCelObjectsAsText = new StringBuilder();
      val centaurCelObjectsAsText = new StringBuilder();
      val intersectionsCelObjectsAsText = new StringBuilder();
      val hypothetsCelObjectsAsText = new StringBuilder();
      int category;
      String nameText;
      for (ConfiguredCelObject celObject : celObjects) {
         category = celObject.getCelObject().getCategory().getId();
         nameText = rosetta.getText(celObject.getCelObject().getNameForRB()) + " ";
         switch (category) {
            case 1:
               classicCelObjectsAsText.append(nameText);
               break;
            case 2:
               modernCelObjectsAsText.append(nameText);
               break;
            case 3:
               extraplutCelObjectsAsText.append(nameText);
               break;
            case 4:
               asteroidCelObjectsAsText.append(nameText);
               break;
            case 5:
               centaurCelObjectsAsText.append(nameText);
               break;
            case 6:
               intersectionsCelObjectsAsText.append(nameText);
               break;
            case 7:
               hypothetsCelObjectsAsText.append(nameText);
               break;
            default:
               LOG.error("Invalid category for celestial body while constructing details of configuration." +
                     "Received category with Id : " + category + ". Celestial object was ignored.");
         }
      }
      if (classicCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.CLASSICS.getNameForRB()), classicCelObjectsAsText.toString()));
      if (modernCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.MODERN.getNameForRB()), modernCelObjectsAsText.toString()));
      if (extraplutCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.EXTRA_PLUT.getNameForRB()), extraplutCelObjectsAsText.toString()));
      if (asteroidCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.ASTEROIDS.getNameForRB()), asteroidCelObjectsAsText.toString()));
      if (centaurCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.CENTAURS.getNameForRB()), centaurCelObjectsAsText.toString()));
      if (intersectionsCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.INTERSECTIONS.getNameForRB()), intersectionsCelObjectsAsText.toString()));
      if (hypothetsCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText(CelObjectCategory.HYPOTHETS.getNameForRB()), hypothetsCelObjectsAsText.toString()));
   }

}
