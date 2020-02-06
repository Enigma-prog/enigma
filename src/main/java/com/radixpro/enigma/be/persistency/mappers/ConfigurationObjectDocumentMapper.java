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

public class ConfigurationObjectDocumentMapper {

   public Document object2Document(final Configuration config) {
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

   public Configuration document2Object(final Document doc) {
      long id = (long) doc.get("_id");
      long parentId = (long) doc.get("parentid");
      var name = (String) doc.get("name");
      var description = (String) doc.get("description");
      var houseSystem = HouseSystems.UNKNOWN.getSystemForId((int) doc.get("housesystem"));
      var ayanamsha = Ayanamshas.UNKNOWN.getAyanamshaForId((int) doc.get("ayanamsha"));
      var eclipticProjection = EclipticProjections.UNKNOWN.getProjectionForId((int) doc.get("eclipticprojection"));
      var observerPosition = ObserverPositions.UNKNOWN.getObserverPositionForId((int) doc.get("observerposition"));
      AspectOrbStructure aspectOrbStructure = (AspectOrbStructure) doc.get("aspectorbstructure");  // todo use id
      double aspectBaseOrb = (double) doc.get("aspectbaseorb");
      boolean aspectDrawInOutGoing = (boolean) doc.get("aspectdrawinoutgoing");
      var celObjects = (ArrayList<ConfiguredCelObject>) doc.get("celestialobjects");
      List<ConfiguredCelObject> celestialObjects = new ArrayList<>();
      var astronConfig = new AstronConfiguration(houseSystem, ayanamsha, eclipticProjection, observerPosition, celestialObjects);
      var supportedAspects = (ArrayList<ConfiguredAspect>) doc.get("supportedaspects");
      var aspectConfiguration = new AspectConfiguration(supportedAspects, aspectBaseOrb, aspectOrbStructure, aspectDrawInOutGoing);
      var delinConfig = new DelinConfiguration(aspectConfiguration);
      return new Configuration(id, parentId, name, description, astronConfig, delinConfig);
   }

}
