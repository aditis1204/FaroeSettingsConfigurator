package org.n52.faroe.configurator.utils;

import com.google.gson.JsonObject;
import org.junit.Test;
import org.locationtech.jts.util.Assert;

public class JsonUtilitiesTest {

    @Test
    public void testStringToJsonObject() {
        String testString = "{\"test-key\":\"newValue\"}";
        JsonObject testJsonObject = JsonUtilities.stringToJsonObject(testString);
        JsonObject resultJsonObject = new JsonObject();
        resultJsonObject.addProperty("test-key", "newValue");
        Assert.equals(resultJsonObject, testJsonObject);
    }

    @Test
    public void testGetValueFromObject () {
        JsonObject testJsonObject = new JsonObject();
        testJsonObject.addProperty("test-key", "newValue");
        Assert.equals("newValue", JsonUtilities.getValueFromObject(testJsonObject, "test-key"));
    }
}
