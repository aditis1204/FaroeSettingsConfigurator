package org.n52.faroe.configurator.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This is a utilities class which makes handling JSON easier.
 */
public class JsonUtilities {

  /**
   * This method convert the string to a JSON object
   * @param json
   * @return
   */
  public static JsonObject stringToJsonObject(String json) {
    JsonElement jsonElement = new JsonParser().parse(json);
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    return jsonObject;
  }

  /**
   * Fetches the value for the given key from the given JSON object.
   * @param jsonObject
   * @param key
   * @return
   */
  public static String getValueFromObject(JsonObject jsonObject, String key) {
    String value = jsonObject.get(key).getAsString();
    return value;
  }
}
