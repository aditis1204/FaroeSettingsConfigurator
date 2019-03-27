package org.n52.faroe.configurator.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.n52.faroe.SettingDefinition;
import org.n52.faroe.SettingValue;
import org.n52.faroe.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SettingsUtilities {

  private static final Logger LOG = LoggerFactory.getLogger(SettingsUtilities.class);

  @Inject private SettingsService settingsService;

  public void updateSettings(String incomingSettings) {
    Map<SettingDefinition<?>, SettingValue<?>> newSettings = new HashMap<>();
    try {
      JsonObject settingsObject = JsonUtilities.stringToJsonObject(incomingSettings);
      for (SettingDefinition<?> definition : settingsService.getSettingDefinitions()) {
        SettingValue<?> newValue =
            settingsService
                .getSettingFactory()
                .newSettingValue(
                    definition,
                    JsonUtilities.getValueFromObject(settingsObject, definition.getKey()));
        newSettings.put(definition, newValue);
      }
      for (SettingValue<?> value : newSettings.values()) {
        settingsService.changeSetting(value);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  public String getSettings() {
    try {
      Map<SettingDefinition<?>, SettingValue<?>> settings = settingsService.getSettings();
      Gson gson = new GsonBuilder().disableHtmlEscaping().create();
      return gson.toJson(settings);
    } catch (Exception e) {
      LOG.error("Unable to fetch settings!");
      throw e;
    }
  }

  public void deleteSettings() {
    try {
      this.settingsService.deleteAll();
      // Remove Settings locally as well
      Map<SettingDefinition<?>, SettingValue<?>> settings = settingsService.getSettings();
      for (SettingDefinition<?> definition : settings.keySet()) {
        settingsService.deleteSetting(definition);
      }
    } catch (Exception e) {
      LOG.error("Unable to delete settings for configuration!");
      throw e;
    }
  }
}
