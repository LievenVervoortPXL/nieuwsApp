package com.example.lievenvervoort.nieuwsapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private MoviesListAdapter mAdapter;
    //private RecyclerView mMovieList;
    //private Cursor mCursor;
    private static String url="https://developer.nytimes.com/proxy/https/api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=a0324d5f0e924bdb9065345498d16db6";
    //private JSONObject result;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue rq;
    List<movieUtils> movieUtilsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.title_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        movieUtilsList = new ArrayList<>();

        sendRequest();


       /* mMovieList = (RecyclerView) this.findViewById(R.id.title_list);

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
        mMovieList.setAdapter(mAdapter);*/
    }

    private void sendRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray responses = getResultSet(response);
                        for (int i=0; i<responses.length(); i++){
                            movieUtils MovieUtils = new movieUtils();

                            try {
                                JSONObject jsonObject = responses.getJSONObject(i);

                                MovieUtils.setDisplay_title(jsonObject.getString("display_title"));

                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                            movieUtilsList.add(MovieUtils);
                        }

                        mAdapter = new MoviesListAdapter(MainActivity.this, movieUtilsList);

                        recyclerView.setAdapter(mAdapter);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Volley error: ", error.toString());
                    }
                });

        rq.add(jsonObjectRequest);
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
