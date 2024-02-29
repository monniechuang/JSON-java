package org.json;
import java.io.Reader;
import java.io.StringReader;
import java.util.function.Function;

import org.json.JSONException;
import org.json.JSONPointer;
import org.json.JSONObject;
import org.json.XML;
import java.util.List;
import java.util.stream.Collectors;

public class MilestoneTest {
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

        System.out.println("----------Milestone4----------");

        try {
            // Convert XML to JSONObject
            String xmlString2 = "<Books><book><title>AAA</title><author>ASmith</author></book><book><title>BBB</title><author>BSmith</author></book></Books>";
            JSONObject obj = XML.toJSONObject(xmlString2);
        
            // Iterate through JSONObject and print node information
            obj.toStream().forEach(node -> {
                System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue() + ", Path: " + node.getPath());
            });
            
            // Extract all book titles
            List<String> titles = obj.toStream()
                .filter(node -> node.getKey().equals("title"))
                .map(node -> node.getValue().toString())
                .collect(Collectors.toList());
        
            // Print extracted titles
            System.out.println("Book titles: " + titles);
        
            // Filter nodes where author name contains "Smith" and replace "Smith" with "MSWE", then print
            obj.toStream().filter(node -> node.getKey() != null && node.getKey().equals("author"))
                .forEach(node -> {
                    // Replace "Smith" with "MSWE" and print
                    System.out.println("Node path: " + node.getPath() + ", Updated Value: " + node.getValue().toString().replace("Smith", "MSWE"));
                });
        
        } catch (JSONException e) {
            System.out.println(e);
        }
        
    }
}