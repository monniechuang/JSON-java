package org.json;
import java.io.Reader;
import java.io.StringReader;
import java.util.function.Function;

import org.json.JSONException;
import org.json.JSONPointer;
import org.json.JSONObject;
import org.json.XML;

public class M2Test {
    public static void main(String[] args) {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
            "<contact>\n"+
            "  <nick>Crista </nick>\n"+
            "  <name>Crista Lopes</name>\n" +
            "  <address>\n" +
            "    <street>Ave of Nowhere</street>\n" +
            "    <zipcode>92614</zipcode>\n" +
            "  </address>\n" +
            "</contact>";
        
        System.out.println("----------Milestone2----------");

        try {
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), new JSONPointer("/contact/address"));
            System.out.println(jobj); 
        } catch (JSONException e) {
            System.out.println(e);
        }

        try {
            JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n");
            System.out.println("Given replacement: " + replacement);
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), new JSONPointer("/contact/address/street"), replacement);
            System.out.println(jobj); 
        } catch (JSONException e) {
            System.out.println(e);
        }

        System.out.println("----------Milestone3----------");

        try {
            // Define the key transformer function to prefix keys with "swe262_"
            Function<String, String> keyTransformer = key -> "swe262_" + key;
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), keyTransformer);
            System.out.println(jobj); 
        } catch (JSONException e) {
            System.out.println(e);
        }

        try {
            // Define the key transformer function to reverse the keys
            Function<String, String> keyTransformer = key -> new StringBuilder(key).reverse().toString();
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), keyTransformer);
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }

        try {
            // Define the key transformer function to convert keys to uppercase
            Function<String, String> keyTransformer = String::toUpperCase;
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), keyTransformer);
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }
}