/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.shared;

import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * i18N manager, takes care of Resource Bundles and Locale's.<br>
 * Implemented as a singleton.
 */
public class Rosetta {

   private static final Logger LOG = Logger.getLogger(Rosetta.class);
   private static final String RB_LOCATION = "rb" + File.separator + "texts";
   private static final String RB_HELP_LOCATION = "rb" + File.separator + "help";
   private static final String DUTCH = "du";
   private static final String ENGLISH = "en";
   private static final String PROP_LANG = "lang";
   private static Rosetta instance = null;
   private ResourceBundle resourceBundle;
   private ResourceBundle helpResourceBundle;
   private Locale locale;

   private Rosetta() {
      // prevent instantiation
   }

   /**
    * Retrieve instance of singleton Rosetta.
    *
    * @return instance of Rosetta.
    */
   public static Rosetta getRosetta() {
      if (instance == null) {
         instance = new Rosetta();
         instance.reInitialize();
      }
      return instance;
   }

   /**
    * Sets new language
    *
    * @param language use "en" for English or "du" for Dutch (case-sensitive).
    */
   public void setLanguage(final String language) {
      final String selLang = checkNotNull(language);
      if (selLang.equals(ENGLISH) || selLang.equals(DUTCH)) {
         PersistedPropertyApi propApi = new PersistedPropertyApi();
         Property currentProp = propApi.read(PROP_LANG).get(0);
         Property langProp = new Property(currentProp.getId(), PROP_LANG, selLang);
         propApi.update(langProp);
         reInitialize();
      } else {
         LOG.error("Unsupported language encountered: " + language);
      }
   }

   private void reInitialize() {
      initi18N();
      defineResourceBundles();
   }

   private void initi18N() {
      PersistedPropertyApi propApi = new PersistedPropertyApi();
      Property currentProp = propApi.read(PROP_LANG).get(0);
      String language = currentProp.getValue();
      if (language.equals(DUTCH)) locale = new Locale(DUTCH, DUTCH.toUpperCase());
      else locale = new Locale(ENGLISH, ENGLISH.toUpperCase());
   }

   private void defineResourceBundles() {
      resourceBundle = ResourceBundle.getBundle(RB_LOCATION, locale);
      helpResourceBundle = ResourceBundle.getBundle(RB_HELP_LOCATION, locale);
   }

   public String getText(final String key) {
      return resourceBundle.getString(checkNotNull(key));
   }

   public String getHelpText(final String key) {
      return helpResourceBundle.getString(checkNotNull(key));
   }

   public Locale getLocale() {
      return locale;
   }
}
