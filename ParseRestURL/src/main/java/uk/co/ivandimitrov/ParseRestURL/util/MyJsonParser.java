package uk.co.ivandimitrov.ParseRestURL.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MyJsonParser {
    /**
     * Takes a String REST url and returns a formatted JSON from that url.
     * 
     * @param url The REST source url
     * @return A formatted JSON
     */
    public static String parse(String url) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));) {
            StringBuilder rawJson = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                rawJson.append(line);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(rawJson.toString());
            return format(gson.toJson(je));
        } catch (MalformedURLException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    // Formats the text so that it looks good on a webpage
    private static String format(String s) {
        return s.replaceAll("\n", " <br/> ").replaceAll(" ", " &#8239 ");
    }

}