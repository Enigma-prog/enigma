/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;

public class PersistableChartData {

   private SimpleDateTime simpleDateTime;
   private Location location;
   private String name;
   private String description;
   private String rating;
   private int id;
   private int[] categories;

   public SimpleDateTime getSimpleDateTime() {
      return simpleDateTime;
   }

   public void setSimpleDateTime(SimpleDateTime simpleDateTime) {
      this.simpleDateTime = simpleDateTime;
   }

   public Location getLocation() {
      return location;
   }

   public void setLocation(Location location) {
      this.location = location;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getRating() {
      return rating;
   }

   public void setRating(String rating) {
      this.rating = rating;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int[] getCategories() {
      return categories;
   }

   public void setCategories(int[] categories) {
      this.categories = categories;
   }
}
