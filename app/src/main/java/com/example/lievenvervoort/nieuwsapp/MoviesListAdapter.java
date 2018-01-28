package com.example.lievenvervoort.nieuwsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemView.setTag(movies.get(position));

        movieUtils MovieUtils = movies.get(position);

        holder.titleView.setText(MovieUtils.getDisplay_title());
    }

    @Override
    public int getItemCount() {
        if(movies == null)
            return 0;

        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Toast toast;
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
