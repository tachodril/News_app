package com.example.ritik.news_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Description extends AppCompatActivity {

    TextView vcontent,vtitle,vdescription;
    ImageView vimage;
    Button vopen_web;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setHomeAsUpIndicator(backArrow);

        vcontent=findViewById(R.id.content);
        vtitle=findViewById(R.id.title);
        vdescription=findViewById(R.id.description);
        vimage=findViewById(R.id.image);
        vopen_web=findViewById(R.id.open_web);
        Bundle bundle = getIntent().getExtras();
        String content_string=" ";
        content_string= bundle.getString("content");
        String description=" ";
        description= bundle.getString("description");
        String title = bundle.getString("title");
        String imageurl = bundle.getString("imageurl");
        url = bundle.getString("url");
        if(content_string.length()>13)
        content_string=content_string.substring(0,content_string.length()-13);
        vcontent.setText(content_string);
        vtitle.setText(title);
        vdescription.setText(description);
        Picasso.with(this).load(imageurl).placeholder(R.drawable.wait).fit().into(vimage);
        vopen_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri news_uri= Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,news_uri);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.desc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.myshare) {
            // do something here
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, url);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent,"Share this article via:"));
        }
        return super.onOptionsItemSelected(item);
    }
}
