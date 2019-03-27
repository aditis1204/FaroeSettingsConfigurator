package org.n52.faroe.configurator.controller;

import javax.inject.Inject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.n52.faroe.configurator.utils.SettingsUtilities;
import org.n52.janmayen.ConfigLocationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is responsible for creation of endpoints to modify settings more easily.
 */
@RestController
@RequestMapping({"/settings"})
public class SettingsController {

  private static final Logger LOG = LoggerFactory.getLogger(SettingsController.class);

  @Inject
  private SettingsUtilities settingsUtilities;

  @PostMapping
  public ResponseEntity<Object> updateSettings(@RequestBody String newSettings) {
    LOG.info("Updating Settings");
    try {
      settingsUtilities.updateSettings(newSettings);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      LOG.error("Update Request Failed");
      LOG.error(ExceptionUtils.getFullStackTrace(e));
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public ResponseEntity<Object> getSettings() {
    LOG.info("Getting Settings");
    try {
      return new ResponseEntity<>(settingsUtilities.getSettings(), HttpStatus.OK);
    } catch (Exception e) {
      LOG.error("Couldn't fetch settings");
      LOG.error(ExceptionUtils.getFullStackTrace(e));
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteSettings() {
    LOG.info("Deleting Settings");
    try {
      settingsUtilities.deleteSettings();
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      LOG.error("Couldn't delete all settings!");
      LOG.error(ExceptionUtils.getFullStackTrace(e));
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
