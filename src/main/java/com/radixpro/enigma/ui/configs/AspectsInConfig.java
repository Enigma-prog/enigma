/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import com.radixpro.enigma.xchg.domain.config.ConfiguredAspect;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview of aspects in a configuration.
 */
public class AspectsInConfig {

   private static final Logger LOG = Logger.getLogger(AspectsInConfig.class);
   @Getter
   private List<PresentableProperty> presentableProperties;

   public AspectsInConfig(@NonNull final List<ConfiguredAspect> aspects) {
      presentableProperties = new ArrayList<>();
      constructProperties(aspects);
   }

   private void constructProperties(@NonNull final List<ConfiguredAspect> aspects) {
      val rosetta = Rosetta.getRosetta();
      int category;
      String nameText;
      val majorAspectsAsText = new StringBuilder();
      val minorAspectsAsText = new StringBuilder();
      val microAspectsAsText = new StringBuilder();
      // TODO add equatorial aspects as category
      for (ConfiguredAspect aspect : aspects) {
         category = aspect.getAspect().getImportance();
         nameText = rosetta.getText(aspect.getAspect().getFullRbId()) + " ";
         switch (category) {
            case 1:
               majorAspectsAsText.append(nameText);
               break;
            case 2:
               minorAspectsAsText.append(nameText);
               break;
            case 3:
               microAspectsAsText.append(nameText);
               break;
            default:
               LOG.error("Invalid category for aspect body while constructing details of configuration." +
                     "Received category with Id : " + category + ". Aspect was ignored.");
         }
      }
      if (majorAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            "Major aspects", majorAspectsAsText.toString()));
      if (minorAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            "Minor aspects", minorAspectsAsText.toString()));
      if (microAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            "Micro aspects", microAspectsAsText.toString()));

   }


}
