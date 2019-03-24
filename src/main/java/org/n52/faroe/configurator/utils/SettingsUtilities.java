package org.n52.faroe.configurator.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import org.n52.faroe.SettingDefinition;
import org.n52.faroe.SettingValue;
import org.n52.faroe.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettingsUtilities {

  @Autowired
  private SettingsService settingsService;
  private static final Logger LOG = LoggerFactory.getLogger(SettingsUtilities.class);

  public void updateSettings(String incomingSettings) {
    Map<SettingDefinition<?>, SettingValue<?>> newSettings = new HashMap<>();
    try {
      JsonObject settingsObject = JsonUtilities.stringToJsonObject(incomingSettings);
      for (SettingDefinition<?> definition : settingsService.getSettingDefinitions()) {
        SettingValue<?> newValue = settingsService.getSettingFactory().newSettingValue(definition,
            JsonUtilities.getValueFromObject(settingsObject, definition.getKey()));
        newSettings.put(definition, newValue);
      }
      for (SettingValue<?> value: newSettings.values()) {
        settingsService.changeSetting(value);
      }
    } catch (Exception e) {
      LOG.error("Unable to update settings!", e);
      throw e;
    }
  }

  public String getSettings () {
    try {
      Map<SettingDefinition<?>, SettingValue<?>> settings = settingsService.getSettings();
      return new Gson().toJson(settings);
    } catch (Exception e) {
      LOG.error("Unable to fetch settings!", e);
      throw e;
    }
  }

}
