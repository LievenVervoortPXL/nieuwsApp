package com.example.lievenvervoort.nieuwsapp;

import android.net.Uri;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lieven on 21/01/2018.
 */

public class requestHelper {

    final static String BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    final static String TOKEN="a0324d5f0e924bdb9065345498d16db6";

    private Uri builtUri = Uri.parse(BASE_URL).buildUpon()
            .appendQueryParameter("api-key",TOKEN)
            .build();

    private String data = null;
    private URL url = null;


    private String getResponse(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            return scanner.toString();
        }finally {
            urlConnection.disconnect();
        }
    }



    public String getAll(){
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            data = getResponse(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
