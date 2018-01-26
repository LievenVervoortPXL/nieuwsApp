package com.example.lievenvervoort.nieuwsapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LievenVervoort on 26/01/2018.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public MoviesListAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public MoviesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.title_list_item, parent, false);
        return new MoviesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesListAdapter.MoviesListViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;

        String title = null;
        title = mCursor.getString(mCursor.getColumnIndex("display_title"));

        holder.titleView.setText(title);
        holder.titleView.setVisibility(View.VISIBLE);
        holder.titleView.setTag(mCursor.getString(mCursor.getColumnIndex("display_title")));
    }

    @Override
    public int getItemCount() {
        if(mCursor == null)
            return 0;

        return mCursor.getCount();
    }

    class MoviesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Toast toast;
        TextView titleView;


        public MoviesListViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.tv_title);

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
