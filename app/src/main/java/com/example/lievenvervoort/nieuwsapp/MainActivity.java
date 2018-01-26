package com.example.lievenvervoort.nieuwsapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    private MoviesListAdapter mAdapter;
    private RecyclerView mMovieList;
    private Cursor mCursor;
    private static String url="https://developer.nytimes.com/proxy/https/api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=a0324d5f0e924bdb9065345498d16db6";
    private JSONArray result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = (RecyclerView) this.findViewById(R.id.title_list);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
            url,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    result = getResultSet(response);
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

                }
            }
        );
        queue.add(jsonObjectRequest);

        mCursor = getJSONCursor(result.toString());
        mAdapter = new MoviesListAdapter(this,mCursor);
        mMovieList.setLayoutManager(new LinearLayoutManager(this));
        mMovieList.setAdapter(mAdapter);
    }

    private Cursor getJSONCursor(String response){
        try{
            JSONArray array = new JSONArray(response);
            return new JSONArrayCursor(array);
        } catch(JSONException exception)
        {
            exception.getMessage();
        }
        return null;
    }

    private JSONArray getResultSet(JSONObject JSONString) {
        try {
            JSONArray resultSet = JSONString.getJSONArray("results");
            return resultSet;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
