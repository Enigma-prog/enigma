/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import com.radixpro.enigma.xchg.domain.Ayanamshas;
import com.radixpro.enigma.xchg.domain.HouseSystems;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

public class Configuration implements Serializable {

   /*
   TConfigDto = class
strict private
  FId, FParentId: Integer;
  FAstron: TConfigAstronDto;
  FDelin: TConfigDelinDto;
  FCelObjects: TIntArray;
  FAspects: TAspectsConfigDtoArray;
  function IsRoot: boolean;
public
  constructor Create(PId, PParentId: Integer; PAstron: TConfigAstronDto; PDelin: TConfigDelinDto;
                     PCelObjects: TIntArray; PAspects: TAspectsConfigDtoArray);
    */

   @Id
   private int id;
   private int parentId;


   private HouseSystems houseSystem;
   private Ayanamshas ayanamshas;

   private int eclProjection;
   private int observerPos;
   private int aspectOrbType;
   private int aspectOrbStruc;
   private double aspectBaseOrb;
   private int[] celObjects;
   // TODO add aspects: id and orb-percentage

   public Configuration() {

   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getParentId() {
      return parentId;
   }

   public void setParentId(int parentId) {
      this.parentId = parentId;
   }

//   public int getHouseSystem() {
//      return houseSystem;
//   }
//
//   public void setHouseSystem(int houseSystem) {
//      this.houseSystem = houseSystem;
//   }
//
//   public int getAyanamsha() {
//      return ayanamsha;
//   }
//
//   public void setAyanamsha(int ayanamsha) {
//      this.ayanamsha = ayanamsha;
//   }

   public int getEclProjection() {
      return eclProjection;
   }

   public void setEclProjection(int eclProjection) {
      this.eclProjection = eclProjection;
   }

   public int getObserverPos() {
      return observerPos;
   }

   public void setObserverPos(int observerPos) {
      this.observerPos = observerPos;
   }

   public int getAspectOrbType() {
      return aspectOrbType;
   }

   public void setAspectOrbType(int aspectOrbType) {
      this.aspectOrbType = aspectOrbType;
   }

   public int getAspectOrbStruc() {
      return aspectOrbStruc;
   }

   public void setAspectOrbStruc(int aspectOrbStruc) {
      this.aspectOrbStruc = aspectOrbStruc;
   }

   public double getAspectBaseOrb() {
      return aspectBaseOrb;
   }

   public void setAspectBaseOrb(double aspectBaseOrb) {
      this.aspectBaseOrb = aspectBaseOrb;
   }

   public int[] getCelObjects() {
      return celObjects;
   }

   public void setCelObjects(int[] celObjects) {
      this.celObjects = celObjects;
   }

}
