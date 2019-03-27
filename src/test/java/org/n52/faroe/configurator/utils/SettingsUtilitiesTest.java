package org.n52.faroe.configurator.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.n52.faroe.SettingDefinition;
import org.n52.faroe.SettingType;
import org.n52.faroe.SettingValue;
import org.n52.faroe.SettingValueFactory;
import org.n52.faroe.SettingsService;
import org.n52.faroe.json.JsonSettingValue;
import org.n52.faroe.settings.BooleanSettingDefinition;

public class SettingsUtilitiesTest {

  @Mock private SettingsService settingsService;

  @InjectMocks @Resource private SettingsUtilities settingsUtilities;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testUpdateSettings() {
    Set<SettingDefinition<?>> mockSettingDefinitionsSet = new HashSet<>();
    SettingDefinition mockSettingDefinition = Mockito.mock(SettingDefinition.class);
    mockSettingDefinitionsSet.add(mockSettingDefinition);
    mockSettingDefinition.setKey("test-key");
    Mockito.when(mockSettingDefinition.getKey()).thenReturn("test-key");
    SettingValue settingValue = Mockito.mock(SettingValue.class);
    SettingValueFactory mockSettingValueFactory = Mockito.mock(SettingValueFactory.class);
    settingValue.setValue("oldValue");
    settingValue.setKey("test-key");
    Mockito.when(settingsService.getSettingDefinitions()).thenReturn(mockSettingDefinitionsSet);
    Mockito.when(settingsService.getSettingFactory()).thenReturn(mockSettingValueFactory);
    Mockito.when(mockSettingValueFactory.newSettingValue(mockSettingDefinition, "newValue"))
        .thenReturn(settingValue);
    Mockito.when(mockSettingDefinition.getKey()).thenReturn("test-key");
    Mockito.when(settingValue.getValue()).thenReturn("oldValue");
    settingsUtilities.updateSettings("{\"test-key\":\"newValue\"}");
    Mockito.verify(mockSettingValueFactory).newSettingValue(mockSettingDefinition, "newValue");
    Mockito.verify(mockSettingDefinition).getKey();
    Mockito.verify(settingsService).changeSetting(settingValue);
  }

  @Test
  public void testGetSettings() {
    Map<SettingDefinition<?>, SettingValue<?>> map = new HashMap<>();
    map.put(
        new BooleanSettingDefinition(),
        new JsonSettingValue<>(SettingType.BOOLEAN, "test-key", "test-value"));
    Mockito.when(settingsService.getSettings()).thenReturn(map);
    String settings = settingsUtilities.getSettings();
    Assert.assertEquals(
        "{\"BooleanSettingDefinition[key=null]\":{\"type\":\"BOOLEAN\",\"key\":\"test-key\",\"value\":\"test-value\"}}",
        settings);
  }

  @Test
  public void testDeleteSettings() {
    settingsUtilities.deleteSettings();
    Mockito.verify(settingsService).deleteAll();
  }
}
