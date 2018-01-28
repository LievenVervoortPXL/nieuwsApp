package com.example.lievenvervoort.nieuwsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lievenvervoort.nieuwsapp.model.movieUtils;

import java.util.List;

/**
 * Created by LievenVervoort on 26/01/2018.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {

    private Context mContext;
    private List<movieUtils> movies;

    public MoviesListAdapter(Context context, List movies){
        this.mContext = context;
        this.movies = movies;
    }

    @Override
    public MoviesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list_item, parent, false);
        return new MoviesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesListAdapter.MoviesListViewHolder holder, int position) {

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

    public class MoviesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Toast toast;
        TextView titleView;


        public MoviesListViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int clickedPosition = getAdapterPosition();

            if(toast != null){
                toast.cancel();
            }

            String toastMessage = "Item #" + clickedPosition + " clicked.";
            toast = Toast.makeText(mContext, toastMessage, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
