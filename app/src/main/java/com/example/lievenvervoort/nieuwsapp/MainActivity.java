package com.example.lievenvervoort.nieuwsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    TextView mTitleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleView = findViewById(R.id.tv_title);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://developer.nytimes.com/proxy/https/api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=a0324d5f0e924bdb9065345498d16db6";

        JsonObjectRequest jsonObjectRequestRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mTitleView.setText(response.toString());
                    }
                }, new Response.ErrorListener(){
                    @Override
                public void onErrorResponse(VolleyError error){

                }
            }
        );



        /*Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mTitleView.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTitleView.setText("That didn't work!");
            }
        }*/
        queue.add(jsonObjectRequestRequest);
    }
}
