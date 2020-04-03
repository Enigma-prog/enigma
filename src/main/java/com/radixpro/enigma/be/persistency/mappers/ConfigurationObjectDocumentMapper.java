/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.*;
import lombok.NonNull;
import lombok.val;
import org.dizitart.no2.Document;

import java.util.ArrayList;

public class ConfigurationObjectDocumentMapper {

   /**
    * Converts instance of Configuration to a Nitrite Document. Disabled check for ConstantConditions by IntelliJ
    * Analyzer. SonarLint does not see any problem here.
    *
    * @param config The instance to save in Nitrite.
    * @return Document for Nitrite.
    */
   public Document object2Document(@NonNull final Configuration config) {
      //noinspection ConstantConditions
      return Document.createDocument("_id", config.getId())
            .put("parentid", config.getParentId())
            .put("name", config.getName())
            .put("description", config.getDescription())
            .put("housesystem", config.getAstronConfiguration().getHouseSystem().getId())
            .put("ayanamsha", config.getAstronConfiguration().getAyanamsha().getId())
            .put("eclipticprojection", config.getAstronConfiguration().getEclipticProjection().getId())
            .put("observerposition", config.getAstronConfiguration().getObserverPosition().getId())
            .put("celestialobjects", config.getAstronConfiguration().getCelObjects())
            .put("aspectbaseorb", config.getDelinConfiguration().getAspectConfiguration().getBaseOrb())
            .put("aspectorbstructure", config.getDelinConfiguration().getAspectConfiguration().getOrbStructure())
            .put("aspectdrawinoutgoing", config.getDelinConfiguration().getAspectConfiguration().isDrawInOutGoing())
            .put("supportedaspects", config.getDelinConfiguration().getAspectConfiguration().getAspects());
   }

   /**
    * Converts JSON document from Nitrite to an instance of Configuration.
    *
    * @param doc The JSON document.
    * @return The resulting instance of Configuration.
    */
   @SuppressWarnings("unchecked")
   public Configuration document2Object(@NonNull final Document doc) {
      val id = (long) doc.get("_id");
      val parentId = (long) doc.get("parentid");
      val name = (String) doc.get("name");
      val description = (String) doc.get("description");
      val houseSystem = HouseSystems.UNKNOWN.getSystemForId((int) doc.get("housesystem"));
      val ayanamsha = Ayanamshas.UNKNOWN.getAyanamshaForId((int) doc.get("ayanamsha"));
      val eclipticProjection = EclipticProjections.UNKNOWN.getProjectionForId((int) doc.get("eclipticprojection"));
      val observerPosition = ObserverPositions.UNKNOWN.getObserverPositionForId((int) doc.get("observerposition"));
      val aspectOrbStructure = (AspectOrbStructure) doc.get("aspectorbstructure");
      val aspectBaseOrb = (double) doc.get("aspectbaseorb");
      val aspectDrawInOutGoing = (boolean) doc.get("aspectdrawinoutgoing");
      val celObjects = (ArrayList<ConfiguredCelObject>) doc.get("celestialobjects");
      val astronConfig = new AstronConfiguration(houseSystem, ayanamsha, eclipticProjection, observerPosition, celObjects);
      val supportedAspects = (ArrayList<ConfiguredAspect>) doc.get("supportedaspects");
      val aspectConfiguration = new AspectConfiguration(supportedAspects, aspectBaseOrb, aspectOrbStructure, aspectDrawInOutGoing);
      val delinConfig = new DelinConfiguration(aspectConfiguration);
      return new Configuration(id, parentId, name, description, astronConfig, delinConfig);
   }

}
