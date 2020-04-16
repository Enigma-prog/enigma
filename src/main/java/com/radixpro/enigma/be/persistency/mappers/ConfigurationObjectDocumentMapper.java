/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.xchg.domain.*;
import com.radixpro.enigma.xchg.domain.config.*;
import org.dizitart.no2.Document;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConfigurationObjectDocumentMapper {

   /**
    * Converts instance of Configuration to a Nitrite Document. Disabled check for ConstantConditions by IntelliJ
    * Analyzer. SonarLint does not see any problem here.
    *
    * @param config The instance to save in Nitrite.
    * @return Document for Nitrite.
    */
   public Document object2Document(final Configuration config) {
      //noinspection ConstantConditions
      checkNotNull(config);
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
   public Configuration document2Object(final Document doc) {
      checkNotNull(doc);
      long id = (long) doc.get("_id");
      long parentId = (long) doc.get("parentid");
      String name = (String) doc.get("name");
      String description = (String) doc.get("description");
      HouseSystems houseSystem = HouseSystems.UNKNOWN.getSystemForId((int) doc.get("housesystem"));
      Ayanamshas ayanamsha = Ayanamshas.UNKNOWN.getAyanamshaForId((int) doc.get("ayanamsha"));
      EclipticProjections eclipticProjection = EclipticProjections.UNKNOWN.getProjectionForId((int) doc.get("eclipticprojection"));
      ObserverPositions observerPosition = ObserverPositions.UNKNOWN.getObserverPositionForId((int) doc.get("observerposition"));
      AspectOrbStructure aspectOrbStructure = (AspectOrbStructure) doc.get("aspectorbstructure");
      double aspectBaseOrb = (double) doc.get("aspectbaseorb");
      boolean aspectDrawInOutGoing = (boolean) doc.get("aspectdrawinoutgoing");
      List celObjects = (ArrayList<ConfiguredCelObject>) doc.get("celestialobjects");
      AstronConfiguration astronConfig = new AstronConfiguration(houseSystem, ayanamsha, eclipticProjection, observerPosition, celObjects);
      List supportedAspects = (ArrayList<ConfiguredAspect>) doc.get("supportedaspects");
      AspectConfiguration aspectConfiguration = new AspectConfiguration(supportedAspects, aspectBaseOrb, aspectOrbStructure, aspectDrawInOutGoing);
      DelinConfiguration delinConfig = new DelinConfiguration(aspectConfiguration);
      return new Configuration(id, parentId, name, description, astronConfig, delinConfig);
   }

}
