package com.warriorrat.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText search;
    ListView list_News;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.search);
        list_News = (ListView) findViewById(R.id.list);

        list_News.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsStories item = (NewsStories) list_News.getItemAtPosition(position);
                String url = item.getWebUrl();
                Intent browser = new Intent(Intent.ACTION_VIEW);
                browser.setData(Uri.parse(url));
                startActivity(browser);
            }
        });
    }

    public void startSearch(View view) {
        String queryText = search.getText().toString();
        if (queryText.trim().equals("")) {
            // Cancel our search
            Toast.makeText(MainActivity.this, "NewsStories name should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if we are connected
        boolean isNetworkActive = isConnected();
        if (isNetworkActive == false) {
            Toast.makeText(MainActivity.this, "Not connected to the internet", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Async request
        NewsRequest requestStuff = new NewsRequest();
        requestStuff.execute(queryText);

    }

    public boolean isConnected() {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    // This code does stuff

    class NewsRequest extends AsyncTask<String, Object, ArrayList<NewsStories>> {

        @Override
        protected ArrayList<NewsStories> doInBackground(String... strings) {
            String apiUrlString = "http://content.guardianapis.com/search?api-key=test&q=" + strings[0];

            URL url = null;

            try {
                url = new URL(apiUrlString);
            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, "Search is invalid", Toast.LENGTH_SHORT).show();
                return null;
            }

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);

                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    Log.v(getClass().getName(), "Request failed. Response Code: " + responseCode);
                    connection.disconnect();
                    return null;
                }

                // Execute the query from the Guardian using the API
                // Receive and save the JSON response from the Guardian

                StringBuilder builder = new StringBuilder();
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = responseReader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = responseReader.readLine();
                }
                String responseString = builder.toString();

                // Create JSONObject from the response string
                JSONObject responseObject = new JSONObject(responseString);

                responseObject = responseObject.getJSONObject("response");

                // Parse JSONObject into NewsStories objects
                JSONArray items = responseObject.getJSONArray("results");

                // read each NewsStory of the array separately
                ArrayList<NewsStories> result = new ArrayList<>();

                for (int i = 0; i < items.length(); i++) {
                    JSONObject currentStory = items.getJSONObject(i);
                    String webTitle = currentStory.getString("webTitle");

                    // Parse JSONObject currentStory and get the string "title"
                    String webUrl = currentStory.getString("webUrl");

                    // Parse JSONObject currentStory for the sectionName string
                    String sectionName = currentStory.getString("sectionName");

                    NewsStories newsStories = new NewsStories(webTitle, webUrl, sectionName);
                    result.add(newsStories);
                }
                return result;

            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<NewsStories> newsStories) {
            TextView helpTextView = (TextView) findViewById(R.id.helpText);
            // in case we haven't received any newsStories
            if (newsStories == null || newsStories.size() == 0) {
                list_News.setVisibility(View.GONE);
                helpTextView.setVisibility(View.VISIBLE);
                helpTextView.setText("Your search didn't return any results");
                Log.i(getClass().getName(), "News is null");
                return;
            } else {
                helpTextView.setVisibility(View.GONE);
                list_News.setVisibility(View.VISIBLE);
                list_News.setAdapter(new NewsAdapter(MainActivity.this, newsStories));
                list_News.invalidateViews();
            }
        }
    }
}