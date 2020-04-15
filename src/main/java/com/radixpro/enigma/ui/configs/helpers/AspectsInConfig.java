/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.ui.configs.helpers;

import com.radixpro.enigma.shared.Rosetta;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import com.radixpro.enigma.xchg.domain.AspectCategory;
import com.radixpro.enigma.xchg.domain.config.ConfiguredAspect;
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

   private final Rosetta rosetta;

   /**
    * Constructor is used by factory for ConfigDetails.
    *
    * @param rosetta instance of Rosetta(i18n).
    */
   public AspectsInConfig(@NonNull final Rosetta rosetta) {
      this.rosetta = rosetta;
   }

   /**
    * Create presentable properties for aspects.
    *
    * @param aspects Aspects as defined in the configuration.
    * @return The resulting presentable properties.
    */
   public List<PresentableProperty> constructProperties(@NonNull final List<ConfiguredAspect> aspects) {
      List<PresentableProperty> presentableProperties = new ArrayList<>();
      AspectCategory category;
      String nameText;
      val majorAspectsAsText = new StringBuilder();
      val minorAspectsAsText = new StringBuilder();
      val microAspectsAsText = new StringBuilder();
      val declinationAspectsAsText = new StringBuilder();
      for (ConfiguredAspect aspect : aspects) {
         category = aspect.getAspect().getAspectCategory();
         nameText = rosetta.getText(aspect.getAspect().getFullRbId()) + " ";
         switch (category.getId()) {
            case 0:
               majorAspectsAsText.append(nameText);
               break;
            case 1:
               minorAspectsAsText.append(nameText);
               break;
            case 2:
               microAspectsAsText.append(nameText);
               break;
            case 3:
               declinationAspectsAsText.append(nameText);
            default:
               LOG.error("Invalid category for aspect body while constructing details of configuration." +
                     "Received category with Id : " + category + ". Aspect was ignored.");
         }
      }
      if (majorAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText("aspectcat.major"), majorAspectsAsText.toString()));
      if (minorAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText("aspectcat.minor"), minorAspectsAsText.toString()));
      if (microAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText("aspectcat.micro"), microAspectsAsText.toString()));
      if (declinationAspectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            rosetta.getText("aspectcat.declination"), declinationAspectsAsText.toString()));
      return presentableProperties;
   }

}
