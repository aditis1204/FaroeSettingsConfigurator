package org.n52.faroe.configurator.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtilities {

  public static JsonObject stringToJsonObject(String json) {
    JsonElement jsonElement = new JsonParser().parse(json);
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    return jsonObject;
  }

  public static String getValueFromObject(JsonObject jsonObject, String key) {
    String value = jsonObject.get(key).getAsString();
    return value;
  }
}
