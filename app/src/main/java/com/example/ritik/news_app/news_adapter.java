package com.example.ritik.news_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class news_adapter extends RecyclerView.Adapter<news_adapter.MyViewHolder> {
    private ArrayList<data> mData;
    private LayoutInflater mInflater;
    //private static ItemClickListener mClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public news_adapter(Context context,ArrayList<data> dataset) {
        mData=dataset;
        mInflater = LayoutInflater.from(context);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        // each data item is just a string in this case
        public TextView mtitle,mname,mdate,mtime;
        public ImageView mimg;
        public MyViewHolder(View v) {
            super(v);
            mtitle=v.findViewById(R.id.title);
            mname=v.findViewById(R.id.name);
            mdate=v.findViewById(R.id.date);
            mtime=v.findViewById(R.id.time);
            mimg=v.findViewById(R.id.image);
            //itemView.setOnClickListener(this);
        }
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public news_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        data currentdata=mData.get(position);
        //set image source using image url and picasso

        //Picasso.with(getContext()).load(currentdata.geturl_img()).placeholder(R.drawable.wait).fit().into(holder.mimg);


        //set time


        String date;
        String time;
        String date_full=currentdata.getdate();

        String[] parts=date_full.split("T");
        date=parts[0];
        time=parts[1];

        //to remove the last character 'Z' from the string time
        String time2=time.substring(0, time.length() - 1);

        String date_now=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if(date.equals(date_now))
            date="Today";
        else
            date="Yesterday";

        //after time and date


        holder.mtitle.setText(currentdata.gettitle());
        holder.mname.setText(currentdata.getName());
       holder.mdate.setText(date);
        holder.mtime.setText(time2);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
}