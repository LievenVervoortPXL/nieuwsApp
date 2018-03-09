package com.example.lievenvervoort.nieuwsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView movie = findViewById(R.id.tv_movie);
        TextView url = findViewById(R.id.tv_more);
        TextView description = findViewById(R.id.tv_description);
        final ImageView imageView = findViewById(R.id.iv_image);

        Intent intent = getIntent();
        movie.setText(intent.getStringExtra("movie"));
        url.setText("Read the full article");
        description.setText(intent.getStringExtra("description"));

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Uri uri = Uri.parse(intent.getStringExtra("more"));
                Intent openWeb = new Intent(Intent.ACTION_VIEW, uri);
                if(openWeb.resolveActivity(getPackageManager()) != null){
                    startActivity(openWeb);
                }
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        ImageRequest imageRequest = new ImageRequest(
                intent.getStringExtra("image"),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(imageRequest);
    }
}
