package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Create the object of TextView Class
    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvAffectedCountries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        / link those objects with their respective id’s that we have given in .XML file
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);
        fetchData();
    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
// Handle the JSON object and handle it inside try and catch

                try {
                    // Creating object of JSONObject

                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Unexpected Error has Occured!!!", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}