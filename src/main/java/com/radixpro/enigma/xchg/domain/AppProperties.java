/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

   private Properties properties;

   public AppProperties() {
      processProperties();
   }

   private void processProperties() {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      if (rootPath == null) throw new RuntimeException("Cannot read properties.");
      String appConfigPath = rootPath + "app.properties";
      properties = new Properties();
      try {
         properties.load(new FileInputStream(appConfigPath));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public Properties getProperties() {
      return properties;
   }
}
