package com.example.ritik.news_app;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class frag_a extends Fragment{


    ListView liste;
    ArrayList<data> newslist;
    RequestQueue queue;
    StringRequest stringRequest;
    TextView emptyview;
    ProgressBar progressbar;
    RecyclerView mylist;
    LinearLayoutManager mLayoutManager;
    news_adapter myadapter;
    private static final String URL=
            "https://newsapi.org/v2/everything?q=bitcoin&from=2019-01-08&sortBy=publishedAt&apiKey=37fb26157b3b48dd80f9ef8a891e1374";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frag_a, container, false);
        mylist = view.findViewById(R.id.list_a);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mylist.setLayoutManager(mLayoutManager);

        queue = Volley.newRequestQueue(getActivity());
// Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("onResponse: ",response);

                        newslist = new ArrayList<>();
                        try {
                            JSONObject root=new JSONObject(response);
                            JSONArray array=root.getJSONArray("articles");
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject currentarticle=array.getJSONObject(i);
                                JSONObject sources=currentarticle.getJSONObject("source");
                                String name=sources.getString("name");
                                String img_url=currentarticle.getString("urlToImage");
                                String url=currentarticle.getString("url");
                                String date=currentarticle.getString("publishedAt");
                                String title=currentarticle.getString("title");
                                String content=currentarticle.getString("content");
                                String description=currentarticle.getString("description");


                                newslist.add(new data(title,img_url,url,name,date,content,description));
                            }
                        } catch (JSONException e) {
                            Log.e("VIVZ", "Problem parsing the news JSON results", e);
                        }
                        myadapter=new news_adapter(getActivity(),newslist);
                        liste.setAdapter((ListAdapter) myadapter);
//                        progressbar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse: ",error.getMessage());
//                emptyview.setText("NO INTERNET CONNETION");
//                emptyview.setVisibility(View.VISIBLE);
//                progressbar.setVisibility(View.GONE);
            }
        });

        queue.add(stringRequest);

        return view;
    }
}
