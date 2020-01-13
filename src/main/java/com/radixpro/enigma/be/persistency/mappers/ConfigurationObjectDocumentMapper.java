/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.xchg.domain.*;
import org.dizitart.no2.Document;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationObjectDocumentMapper {

   public Document object2Document(final Configuration config) {
      List<Integer> celObjectIds = new ArrayList<>();
      List<CelestialObjects> celObjects = config.getConfigAstron().getCelestialObjects();
      for (CelestialObjects celObject : celObjects) {
         celObjectIds.add(celObject.getId());
      }
      List<int[]> supportedAspectIds = new ArrayList<>();
      List<AspectOrb> supportedAspects = config.getConfigDelin().getSupportedAspects();
      for (AspectOrb aspectOrb : supportedAspects) {
         supportedAspectIds.add(new int[]{aspectOrb.getAspect().getId(), aspectOrb.getOrbPercentage()});
      }
      return Document.createDocument("_id", config.getId())
            .put("parentid", config.getParentId())
            .put("name", config.getName())
            .put("description", config.getDescription())
            .put("housesystem", config.getConfigAstron().getHouseSystem().getId())
            .put("ayanamsha", config.getConfigAstron().getAyanamsha().getId())
            .put("eclipticprojection", config.getConfigAstron().getEclipticProjection().getId())
            .put("observerposition", config.getConfigAstron().getObserverPosition().getId())
            .put("celestialobjects", celObjectIds)
            .put("aspectbaseorb", config.getConfigDelin().getAspectBaseOrb())
            .put("supportedaspects", supportedAspectIds);
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
      var celObjectIds = (ArrayList<Integer>) doc.get("celestialobjects");
      List<CelestialObjects> celestialObjects = new ArrayList<>();
      for (int celObjectId : celObjectIds) {
         celestialObjects.add(CelestialObjects.UNKNOWN.getCelObjectForId(celObjectId));
      }
      var configAstron = new ConfigAstron(houseSystem, ayanamsha, eclipticProjection, observerPosition, celestialObjects);
      var suppAspectIds = (ArrayList<int[]>) doc.get("supportedaspects");
      List<AspectOrb> supportedAspects = new ArrayList<>();
      for (int[] values : suppAspectIds) {
         supportedAspects.add(new AspectOrb(Aspects.UNDECILE.getAspectForId(values[0]), values[1]));
      }
      var configDelin = new ConfigDelin((double) doc.get("aspectbaseorb"), supportedAspects);
      return new Configuration(id, parentId, name, description, configAstron, configDelin);
   }

}
