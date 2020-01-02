/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.domain;

interface LookupAble {

   String RB_NAME_POSTFIX = ".name";
   String RB_DESCRIPTION_POSTFIX = ".description";

   int getId();

   String getRbKeyForName();

   String getRbKeyForDescription();


}
