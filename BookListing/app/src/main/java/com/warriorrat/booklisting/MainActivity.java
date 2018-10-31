package com.warriorrat.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText search;
    ListView list_books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.search);
        list_books = (ListView) findViewById(R.id.list);
    }

    public void startSearch(View view) {
        String queryText = search.getText().toString();
        if (queryText.trim().equals("")) {
            // Cancel our search
            Toast.makeText(MainActivity.this, "Book name should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if we are connected
        boolean isNetworkActive = isConnected();
        if (isNetworkActive == false) {
            Toast.makeText(MainActivity.this, "Not connected to the internet", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Async request
        GoogleBooksRequest requestStuff = new GoogleBooksRequest();
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

    class GoogleBooksRequest extends AsyncTask<String, Object, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            String apiUrlString = "https://www.googleapis.com/books/v1/volumes?q=" + strings[0];

            URL url = null;

            try {
                url = new URL(apiUrlString);
            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, "Search is invalid", Toast.LENGTH_SHORT).show();
                return null;
            }

            HttpsURLConnection connection = null;

            try {
                connection = (HttpsURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);

                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    Log.v(getClass().getName(), "Request failed. Response Code: " + responseCode);
                    connection.disconnect();
                    return null;
                }

                // Execute Query Google Books using the API
                // Receive and save the JSON response from Google Books

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

                // Parse JSONObject into Book objects
                JSONArray items = responseObject.getJSONArray("items");

                // read each book of the array separately
                ArrayList<Book> result = new ArrayList<>();

                for (int i = 0; i < items.length(); i++) {
                    JSONObject currentBook = items.getJSONObject(i);
                    JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                    // Parse JSONObject volumeInfo and get the string "title"
                    String title = volumeInfo.getString("title");

                    // Parse JSONObject volumeInfo for the author array and get the first author
                    String writer;

                    try {
                        writer = volumeInfo.getJSONArray("authors").getString(0);
                    } catch (JSONException e) {
                        writer = "";
                    }

                    Book book = new Book(writer, title);
                    result.add(book);
                }
                return result;

            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            TextView helpTextView = (TextView) findViewById(R.id.helpText);
            // in case we haven't received any books
            if (books == null || books.size() == 0) {
                list_books.setVisibility(View.GONE);
                helpTextView.setVisibility(View.VISIBLE);
                helpTextView.setText("Your search didn't return any results");
                Log.i(getClass().getName(), "Books is null");
                return;
            } else {
                helpTextView.setVisibility(View.GONE);
                list_books.setVisibility(View.VISIBLE);
                list_books.setAdapter(new BookAdapter(MainActivity.this, books));
                list_books.invalidateViews();
            }
        }
    }
}