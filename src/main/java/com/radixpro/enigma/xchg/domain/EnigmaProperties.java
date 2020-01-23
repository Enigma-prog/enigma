/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnigmaProperties {

   private static final String EXTENSION = ".properties";
   private static final String NOT_FOUND = "Cannot read properties.";
   private Properties properties;

   public EnigmaProperties(final String propType) {
      processProperties(propType);
   }

   private void processProperties(final String propType) {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      if (rootPath == null) throw new RuntimeException(NOT_FOUND);
      String appConfigPath = rootPath + propType + EXTENSION;
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
