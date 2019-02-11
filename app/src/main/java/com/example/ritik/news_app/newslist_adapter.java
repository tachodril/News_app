package com.example.ritik.news_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class newslist_adapter extends ArrayAdapter<data>
{
    public newslist_adapter(@NonNull Context context, ArrayList<data> resource){
        super(context,0,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview=convertView;
        if(listitemview==null){
            listitemview= LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }
        final data currentdata=getItem(position);

        ImageView share=listitemview.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, currentdata.geturl());
                sendIntent.setType("text/plain");
                getContext().startActivity(Intent.createChooser(sendIntent,"Share this article via:"));

            }
        });



        ImageView img=listitemview.findViewById(R.id.image);
        //set image source using image url and picasso

        Picasso.with(getContext()).load(currentdata.geturl_img()).placeholder(R.drawable.wait).fit().into(img);


        //next after image set
        TextView mtitle,mname,mdate,mtime;

        //set time


        String date;
        String time;
        String date_full=currentdata.getdate();

        String[] parts=date_full.split("T");
        date=parts[0];
        time=parts[1];

        //to remove the last character 'Z' from the string time
        String time2=time.substring(0, time.length() - 1);
        time2=time2.substring(0,5);

        String date_now=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if(date.equals(date_now))
            date="Today";
        else
            date="Yesterday";

        //after time and date
        mtitle=listitemview.findViewById(R.id.title);
        mname=listitemview.findViewById(R.id.name);
        mdate=listitemview.findViewById(R.id.date);
        mtime=listitemview.findViewById(R.id.time);

        mtitle.setText(currentdata.gettitle());
        mname.setText(currentdata.getName());
        mdate.setText(date);
        mtime.setText(time2);
        return listitemview;
    }

}
