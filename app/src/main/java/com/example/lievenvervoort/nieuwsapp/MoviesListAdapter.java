package com.example.lievenvervoort.nieuwsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.lievenvervoort.nieuwsapp.model.movieUtils;

import java.util.List;

/**
 * Created by LievenVervoort on 26/01/2018.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private Context mContext;
    private List<movieUtils> movies;

    public MoviesListAdapter(Context context, List movies){
        this.mContext = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setTag(movies.get(position));
        final movieUtils MovieUtils = movies.get(position);
        holder.titleView.setText(MovieUtils.getDisplay_title());

        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DetailActivity.class);
                intent.putExtra("movie", movies.get(position).getDisplay_title());
                intent.putExtra("description",movies.get(position).getSummary_short());
                intent.putExtra("more",movies.get(position).getLink().getUrl());
                intent.putExtra("image",movies.get(position).getMultimedia().getSrc());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(movies == null)
            return 0;

        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieUtils movieUtils = (movieUtils) view.getTag();

                    Toast.makeText(view.getContext(), movieUtils.getDisplay_title(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
