package com.example.przemek.astroweather;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Przemek on 28.05.2018.
 */

public class WeatherDownloader extends AsyncTask<String, Void, JSONObject> {

    protected JSONObject getJson(String cityName){
        String endpoint = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22%C5%81%C3%B3d%C5%BA%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        JSONObject jsonObject = null;
        try {
            URL url = new URL(endpoint);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            if(connection.getResponseCode() != 200){
                return null;
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String jsonRaw = read(bufferedReader);
            jsonObject = (new JSONObject(jsonRaw)).getJSONObject("query");

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private static String read(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        return getJson(strings[0]);
    }
}
