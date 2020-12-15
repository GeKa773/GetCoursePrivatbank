package com.mobile.internet.getinformation.getcourse.jsonobjeck.getcourseprivatbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Course> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetCurrency().execute();
    }

    private class GetCurrency extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String nowDate = format.format(date);
            String result = getContent("https://api.privatbank.ua/p24api/exchange_rates?json&date=" + nowDate);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            arrayList = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray array = jsonObject.getJSONArray("exchangeRate");

                for (int i = 1; i < array.length(); i++) {
                    JSONObject some = (JSONObject) array.get(i);
                    arrayList.add(new Course(
                            some.getDouble("saleRateNB"),
                            some.getString("currency")
                    ));
                    System.out.println(some.getDouble("saleRateNB")+">>>>>>>>>>>>>>>>>>>>>");
                    System.out.println(some.getString("currency")+">>>>>>>>>>>>>>>>>>>>>");
                }
                arrayList.add(new Course(23.2,"gt"));
                arrayList.add(new Course(23.2,"gt"));
                arrayList.add(new Course(23.2,"gt"));
                arrayList.add(new Course(23.2,"gt"));
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                adapter = new CourseAdapter(arrayList);
                recyclerView.setAdapter(adapter);
                if (getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_PORTRAIT) {
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String getContent(String path) {
            try {
                URL url = new URL(path);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setConnectTimeout(20000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String result = "";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result += line + "\n";
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}