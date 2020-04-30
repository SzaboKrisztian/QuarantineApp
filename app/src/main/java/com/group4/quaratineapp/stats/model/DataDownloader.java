package com.group4.quaratineapp.stats.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataDownloader {

    // This interfaces is implemented by the activity. It provides two methods to signal that
    // the data has been loaded, or that an error occurred and it could not be loaded.
    private DataReadyCallback readyCallback;
    private List<Country> countryData; // The list that holds the data
    private static DataDownloader instance = new DataDownloader(); // Singleton pattern
    private Context context;

    private DataDownloader() {} // Singleton

    public static DataDownloader getInstance() {
        return instance;
    } // Singleton

    // The three setters below are used to pass the right references to the list, callback, and context

    public void setCountryData(List<Country> countryData) {
        this.countryData = countryData;
    }

    public void setReadyCallback(DataReadyCallback readyCallback) {
        this.readyCallback = readyCallback;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // This method starts the download process

    public void downloadData() {
        File file = getLastModified(context.getFilesDir());
        if (file != null && !checkIfOutdated(file.getName())) {
            JSONObject data = loadFromFile(file);
            Collection<Country> parsedData = parseJSON(data);
            if (parsedData != null) {
                countryData.clear();
                countryData.addAll(parsedData);
                readyCallback.dataReady();
            }
        }
        new CountryDataDownloader().execute();
    }

    private class CountryDataDownloader extends AsyncTask<Void, Void, List<Country>> {

        @Override
        protected List<Country> doInBackground(Void... params) {

            JSONObject data = null;

            // Request the data from the URL
            try {
                data = readJsonFromUrl("https://covid-api.com/api/reports");
            } catch (IOException | JSONException e) {
                // Data failed to download or parse
                readyCallback.error();
            }

            Collection<Country> parsedData = DataDownloader.parseJSON(data);
            if (parsedData != null) {
                // Put the data in the main collection and sort it
                countryData.clear();
                countryData.addAll(parsedData);
                countryData.sort((o1, o2) -> -Integer.compare(o1.getConfirmed(), o2.getConfirmed()));
            } else {
                // Parse error, couldn't extract Country objects (Valid but unexpected JSON format)
                readyCallback.error();
            }
            return null;
        }

        // This gets called on the UI thread when the AsyncTask finishes

        @Override
        protected void onPostExecute(List<Country> countries) {
            super.onPostExecute(countries);
            readyCallback.dataReady();
        }

        // Converts the contents of a Reader into a String

        private String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        // Sends a GET request to a URL, then turns the response into a JSONObject.
        // It also saves the contents to a local file along the way, for caching purposes.

        private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            try (InputStream is = new URL(url).openStream()) {
                // Load data from URL
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                // Parse data to String
                String jsonText = readAll(rd);
                // Parse String to JSONObject
                JSONObject json = new JSONObject(jsonText);
                // Extract the date contained in it, to use as a filename
                String fileName = json.getJSONArray("data")
                        .getJSONObject(0).getString("date");
                // Save the downloaded data to local storage
                saveToCache(fileName, jsonText);
                return json;
            }
        }

        // Saves string data to a file

        private void saveToCache(String filename, String contents) {
            try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
                fos.write(contents.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                Log.e("STATS", "Error saving stats to storage.");
            }
        }
    }

    // Extracts Country objects from JSONObject data

    public static Collection<Country> parseJSON(JSONObject data) {
        if (data == null) {
            return null;
        }
        // Use a map to consolidate the data on a per-country basis
        Map<String, Country> dataMap = new LinkedHashMap<>();
        try {
            // Iterate over the items in the array
            JSONArray arr = data.getJSONArray("data");
            for (int i = 0; i < arr.length(); i++) {
                // Extract the data points into a new Country object
                JSONObject item = arr.getJSONObject(i);
                Country country = new Country();
                country.setCode(item.getJSONObject("region").getString("iso"));
                country.setName(item.getJSONObject("region").getString("name"));
                country.setActive(item.getInt("active"));
                country.setConfirmed(item.getInt("confirmed"));
                country.setDead(item.getInt("deaths"));
                country.setRecovered(item.getInt("recovered"));

                // If a country with the same code already exists in the map,
                // add the current values to the existing ones
                if (dataMap.containsKey(country.getCode())) {
                    dataMap.get(country.getCode()).add(country);
                } else {
                    // Otherwise just put it inside the map
                    dataMap.put(country.getCode(), country);
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return dataMap.values();
    }

    // Loads and parses a JSONObject from a json text file

    private JSONObject loadFromFile(File file) {
        String contents = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                return null;
            } finally {
                contents = stringBuilder.toString();
            }
        } catch (IOException e) {
            return null;
        }
        if (!contents.isEmpty()) {
            try {
                return new JSONObject(contents);
            } catch (JSONException e) {
                return null;
            }
        }
        return null;
    }

    // Checks if the filename is more than a day old

    private boolean checkIfOutdated(String filename) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fileDate = LocalDate.parse(filename, dtf);
            return fileDate.isBefore(yesterday);
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    // Finds the newest file in a directory

    private File getLastModified(File directory)
    {
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }
}
