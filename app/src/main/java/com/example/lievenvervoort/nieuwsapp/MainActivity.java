package com.example.lievenvervoort.nieuwsapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lievenvervoort.nieuwsapp.model.Multimedia;
import com.example.lievenvervoort.nieuwsapp.model.link;
import com.example.lievenvervoort.nieuwsapp.model.movieUtils;

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

    }

    private void sendRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray responses = getResultSet(response,"results");

                        for (int i=0; i<responses.length(); i++){
                            movieUtils MovieUtils = new movieUtils();
                            Multimedia media = new Multimedia();
                            link link = new link();

                            try {
                                JSONObject jsonObject = responses.getJSONObject(i);
                                JSONObject multiMediaItem = jsonObject.getJSONObject("multimedia");
                                JSONObject linkItem = jsonObject.getJSONObject("link");


                                MovieUtils.setDisplay_title(jsonObject.getString("display_title"));
                                MovieUtils.setByline(jsonObject.getString("byline"));
                                MovieUtils.setMpaa_rating(jsonObject.getString("mpaa_rating"));
                                MovieUtils.setCritics_pick(jsonObject.getInt("critics_pick"));
                                MovieUtils.setHeadline(jsonObject.getString("headline"));
                                MovieUtils.setSummary_short(jsonObject.getString("summary_short"));
                                MovieUtils.setPublication_date(jsonObject.getString("publication_date"));
                                MovieUtils.setOpening_date(jsonObject.getString("opening_date"));
                                MovieUtils.setDate_updated(jsonObject.getString("date_updated"));
                                link.setType(linkItem.getString("type"));
                                link.setUrl(linkItem.getString("url"));
                                link.setSuggested_link_text(linkItem.getString("suggested_link_text"));
                                MovieUtils.setLink(link);
                                media.setType(multiMediaItem.getString("type"));
                                media.setHeight(multiMediaItem.getInt("height"));
                                media.setSrc(multiMediaItem.getString("src"));
                                media.setWidth(multiMediaItem.getInt("width"));
                                MovieUtils.setMultimedia(media);

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

    private JSONArray getResultSet(JSONObject JSONString, String value) {
        try {
            JSONArray resultSet = JSONString.getJSONArray(value);
            return resultSet;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
