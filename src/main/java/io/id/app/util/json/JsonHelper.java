package io.id.app.util.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.id.app.util.log.BaseLogger;

public class JsonHelper {
    private static final BaseLogger log = new BaseLogger(JsonHelper.class);
    private static final ObjectMapper OBJ_MAPPER = CustomObjectMapper.getInstance().getObjectMapper();

    private JsonHelper() {
        // Empty Constructor
    }

    public static String toJson(Object obj) {
        try {
            return OBJ_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("toJson", e);
        }
        return "";
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJ_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("fromJson", e);
        }

        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception ex) {
            log.error("fromJson", "Could not invoke Default Constructor", ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + clazz.getName() + ";");
            T[] objects = OBJ_MAPPER.readValue(json, arrayClass);
            return Arrays.asList(objects);
        } catch (ClassNotFoundException | IOException e) {
            log.error("fromJsonArray", e);
        }

        return new ArrayList<>();
    }
}
