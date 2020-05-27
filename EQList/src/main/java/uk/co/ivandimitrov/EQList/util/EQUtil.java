package uk.co.ivandimitrov.EQList.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.ivandimitrov.EQList.Earthquake;

public class EQUtil {

    /**
     * Queries the USGS API with the provided parameters and returns a list of
     * Earthquake objects.
     * 
     * @param maxMag  The min magnitude in the resulting list.
     * @param maxMag  The max magnitude in the resulting list.
     * @param minDate The min date in the resulting list.
     * @param maxDate The max date in the resulting list.
     * @return returns a List<Earthquake> that can be iterated over and displayed.
     */
    public static List<Earthquake> getList(String minMag, String maxMag, String minDate, String maxDate) {
        try {
            JSONObject json = readJsonFromUrl(
                    "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&limit=1000&starttime=" + minDate
                            + "&endtime=" + maxDate + "&minmagnitude=" + minMag + "&maxmagnitude=" + maxMag);
            return extractData(json);
        } catch (IOException | JSONException e) {

            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // This method goes through the JSONObject that has been received by the USGS
    // API and extracts the needed parameters for this demo.
    private static List<Earthquake> extractData(JSONObject json) {
        JSONArray arr = json.getJSONArray("features");
        List<Earthquake> eq = new ArrayList<>();
        for (Object obj : arr) {
            if (obj instanceof JSONObject) {
                JSONObject props = ((JSONObject) obj).getJSONObject("properties");
                String title = props.getString("title");
                double mag = props.getDouble("mag");
                String location = props.getString("place");
                LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(props.getLong("time")),
                        ZoneId.systemDefault());
                JSONArray coordinates = ((JSONObject) obj).getJSONObject("geometry").getJSONArray("coordinates");
                double lat = coordinates.getDouble(1);
                double lon = coordinates.getDouble(0);
                String dms = GeoUtil.convertDdToDMS(lat, lon);
                eq.add(new Earthquake(title, mag, location, time, dms, lat, lon));
            }
        }
        return eq;
    }

    // Reads the data from the provided URL and stores it in a JSONObject.
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
            String jsonText = rd.lines().collect(Collectors.joining());
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}