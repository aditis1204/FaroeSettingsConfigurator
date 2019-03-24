package org.n52.faroe.configurator.controller;

import javax.servlet.http.HttpServletRequest;
import org.n52.faroe.configurator.utils.SettingsUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.n52.faroe.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping({"/settings"})
public class SettingsController {

  @Autowired
  private SettingsUtilities settingsUtilities;

  private static final Logger LOG = LoggerFactory.getLogger(SettingsController.class);

  @PostMapping
  public ResponseEntity<Object> updateSettings(@RequestBody String newSettings) {
    LOG.info("Updating Settings");
    settingsUtilities.updateSettings(newSettings);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Object> getSettings() {
    LOG.info("Getting Settings");
    settingsUtilities.getSettings();
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
