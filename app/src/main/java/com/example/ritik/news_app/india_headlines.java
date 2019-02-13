package com.example.ritik.news_app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
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

public class india_headlines extends Fragment {
    public String url="https://newsapi.org/v2/top-headlines?country=in&language=en&category=general&pageSize=35&apiKey=37fb26157b3b48dd80f9ef8a891e1374";

    ListView liste;
    ArrayList<data> newslist;
    newslist_adapter myadap;
    RequestQueue queue;
    StringRequest stringRequest;
    TextView emptyview;
    ProgressBar progressbar;
    SwipeRefreshLayout refresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frag_e, container, false);
        liste=view.findViewById(R.id.list3);
        emptyview=view.findViewById(R.id.emptyview);
        progressbar=view.findViewById(R.id.progressbar);
        refresh=view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("VIVZ", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        emptyview.setVisibility(View.GONE);
                        if(newslist!=null)
                        { newslist.clear();myadap.clear();}
                        load();
                        queue.add(stringRequest);
                    }
                }
        );
        liste.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                int topRowVerticalPosition = (liste== null || liste.getChildCount() == 0) ? 0 : liste.getChildAt(0).getTop();
                refresh.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);

            }
        });


        queue = Volley.newRequestQueue(getActivity());

// Request a string response from the provided URL.
        load();

        queue.add(stringRequest);


        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                data currentnews=myadap.getItem(i);
                String content;
//                String url=currentnews.geturl();
//                Uri news_uri= Uri.parse(url);
//                Intent intent=new Intent(Intent.ACTION_VIEW,news_uri);
//                startActivity(intent);

                content=currentnews.getcontent();
                Intent intent=new Intent(getActivity(),Description.class);
                intent.putExtra("content",content);
                intent.putExtra("description",currentnews.getdescription());
                intent.putExtra("title",currentnews.gettitle());
                intent.putExtra("imageurl",currentnews.geturl_img());
                intent.putExtra("url",currentnews.geturl());
                startActivity(intent);
            }
        });

        return view;
    }

    public void load()
    {
        stringRequest = new StringRequest(Request.Method.GET, url,
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
                                String img_url="";
                                if (currentarticle.has("urlToImage")){
                                    img_url=currentarticle.getString("urlToImage");}                                String url=currentarticle.getString("url");
                                String date=currentarticle.getString("publishedAt");
                                String title=currentarticle.getString("title");
                                String content=currentarticle.getString("content");
                                String description=currentarticle.getString("description");

                                newslist.add(new data(title,img_url,url,name,date,content,description));
                            }
                        } catch (JSONException e) {
                            Log.e("VIVZ", "Problem parsing the news JSON results", e);
                        }
                        myadap=new newslist_adapter(getActivity(),newslist);
                        liste.setAdapter(myadap);
                        progressbar.setVisibility(View.GONE);
                        refresh.setRefreshing(false);
                        emptyview.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse: ",""+error.getMessage());
                emptyview.setText("NO INTERNET CONNETION");
                emptyview.setVisibility(View.VISIBLE);
                refresh.setRefreshing(false);
                progressbar.setVisibility(View.GONE);
                //refresh.setVisibility(View.VISIBLE);
            }
        });
    }

}
