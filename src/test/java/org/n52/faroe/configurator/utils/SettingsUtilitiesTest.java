package org.n52.faroe.configurator.utils;

import java.util.HashSet;
import java.util.Set;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.n52.faroe.SettingDefinition;
import org.n52.faroe.SettingType;
import org.n52.faroe.SettingValue;
import org.n52.faroe.SettingsService;
import org.n52.faroe.SettingsServiceImpl;
import org.n52.faroe.json.JsonSettingValue;
import org.n52.faroe.json.JsonSettingValueFactory;
import org.n52.faroe.settings.StringSettingDefinition;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SettingsUtilitiesTest {

  private SettingsUtilities settingsUtilities;
  private SettingsService mockSettingsService;
  private JsonUtilities mockJsonUtilities;

  @Before
  public void setup() {
    settingsUtilities = new SettingsUtilities();
  }

  @Test
  public void testUpdateSettings() {
    mockSettingsService = EasyMock.createNiceMock(SettingsServiceImpl.class);
    mockJsonUtilities = EasyMock.createNiceMock(JsonUtilities.class);
    Set<SettingDefinition<?>> definitionSet = new HashSet<>();
    definitionSet.add(new StringSettingDefinition());
    SettingDefinition<?> definition = new StringSettingDefinition();
    SettingValue value = new JsonSettingValue(SettingType.STRING, "key", "value");
    EasyMock.expect(mockSettingsService.getSettingDefinitions()).andReturn(definitionSet);
    EasyMock.expect(mockSettingsService.getSettingFactory()).andReturn(new JsonSettingValueFactory());
    //EasyMock.expect(mockJsonUtilities.)
//    EasyMock.expect(mockSettingsService.getSettingFactory().newSettingValue(definition, "")).andReturn(value);
    EasyMock.replay(mockSettingsService);
    settingsUtilities.updateSettings("{\"test\":\"key\"}");
    EasyMock.verify();
  }

  @Test
  public void testGetSettings() {

  }
}