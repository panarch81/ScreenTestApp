package com.example.root.screentestapp;

/**
 * Created by root on 06-07-16.
 */
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by NIS1175m on 7/5/16.
 */
public class Utils {

    private static ObjectMapper m = new ObjectMapper();
    private static JsonFactory jf = new JsonFactory();

    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass)
            throws JsonMappingException, JsonParseException, IOException {
        return m.readValue(jsonAsString, pojoClass);
    }

    public static <T> Object fromJson(FileReader fr, Class<T> pojoClass)
            throws JsonParseException, IOException {
        return m.readValue(fr, pojoClass);
    }

    public static String toJson(Object pojo, boolean prettyPrint)
            throws JsonMappingException, JsonGenerationException, IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = jf.createJsonGenerator(sw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
        return sw.toString();
    }

    public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint)
            throws JsonMappingException, JsonGenerationException, IOException {
        JsonGenerator jg = jf.createJsonGenerator(fw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
    }

    public static String convertXMLNewsRssFeedStringToJson(String XMLNewsRssFeedString) {
        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(XMLNewsRssFeedString);
            jsonObj = jsonObj.getJSONObject("rss").getJSONObject("channel");
        } catch (JSONException e) {
            System.out.println("JSON exception" + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("jsonObj ="+jsonObj);

        return jsonObj.toString();
    }
}

