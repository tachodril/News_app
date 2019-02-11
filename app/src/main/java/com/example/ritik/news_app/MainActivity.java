package com.example.ritik.news_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mdrawer;
    private NavigationView nav_view;
    private ActionBarDrawerToggle mtoggle;
    TabLayout tabLayout;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000' family='Sans-serif'>" + getString(R.string.app_name) + "</font>"));

        viewpager=findViewById(R.id.viewpager);
        FragmentManager frag_manager=getSupportFragmentManager();
        mypager_adapter adapter=new mypager_adapter(frag_manager);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(8);
        tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewpager);


        nav_view=findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        mdrawer=findViewById(R.id.drawer);
        mtoggle=new ActionBarDrawerToggle(this,mdrawer,R.string.open,R.string.close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(mtoggle.onOptionsItemSelected(item))
            return true;
        else if (id == R.id.about_us) {
            // do something here
            Snackbar snackbar = Snackbar
                    .make(mdrawer, "Email: ritikraj@gmail.com", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.headlines: {
                //do somthing
                viewpager.setCurrentItem(0);
                break;
            }
            case R.id.politics: {
                //do somthing
                viewpager.setCurrentItem(1);
                break;
            }
            case R.id.world: {
                //do somthing
                viewpager.setCurrentItem(2);
                break;
            }
            case R.id.entertainment: {
                //do somthing
                viewpager.setCurrentItem(3);
                break;
            }
            case R.id.sports: {
                //do somthing
                viewpager.setCurrentItem(4);
                break;
            }
            case R.id.business: {
                //do somthing
                viewpager.setCurrentItem(5);
                break;
            }
            case R.id.technology: {
                //do somthing
                viewpager.setCurrentItem(6);
                break;
            }
            case R.id.health: {
                //do somthing
                viewpager.setCurrentItem(7);
                break;
            }
            case R.id.bitcoin: {
                //do somthing
                Intent intent=new Intent(this,more_sections_activity.class);
                intent.putExtra("title","bitcoin");
                startActivity(intent);
                break;
            }
            case R.id.science: {
                //do somthing
                Intent intent=new Intent(this,more_sections_activity.class);
                intent.putExtra("title","science");
                startActivity(intent);
                break;
            }
            case R.id.food: {
                //do somthing
                Intent intent=new Intent(this,more_sections_activity.class);
                intent.putExtra("title","food");
                startActivity(intent);
                break;
            }
            case R.id.movies: {
                //do somthing
                Intent intent=new Intent(this,more_sections_activity.class);
                intent.putExtra("title","movies");
                startActivity(intent);
                break;
            }
            case R.id.fashion: {
                //do somthing
                Intent intent=new Intent(this,more_sections_activity.class);
                intent.putExtra("title","fashion");
                startActivity(intent);
                break;
            }
            case R.id.books: {
                //do somthing
                Intent intent=new Intent(this,more_sections_activity.class);
                intent.putExtra("title","books");
                startActivity(intent);
                break;
            }
            case R.id.favorites: {
                //do somthing
                viewpager.setCurrentItem(6);
                break;
            }
            case R.id.languages: {
                //do somthing
                break;
            }
            case R.id.settings: {
                //do somthing
                break;
            }
            case R.id.help: {
                //do somthing
                Snackbar snackbar = Snackbar
                        .make(mdrawer, "Email: ritikraj@gmail.com", Snackbar.LENGTH_LONG);
                snackbar.show();
                break;
            }
        }
        //close navigation drawer
        mdrawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
class mypager_adapter extends FragmentPagerAdapter{

    public mypager_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0) return new india_headlines();
        else if(i==1) return new politics();
        else if(i==2) return new world();
        else if(i==3) return new entertainment();
        else if(i==4) return new sports();
        else if(i==5) return new buiseness();
        else if(i==6) return new technology();
        else if(i==7) return new health();
        return null;
    }

    @Override
    public int getCount() {
        return 8;
    }
    public CharSequence getPageTitle(int i) {
        String str=new String();
        if(i==0)
            str="TOP STORIES";
        else if(i==1)
            str="POLITICS";
        else if(i==2)
            str="WORLD";
        else if(i==3)
            str="ENTERTAINMENT";
        else if(i==4)
            str="SPORTS";
        else if(i==5)
            str="BUISENESS";
        else if(i==6)
            str="TECHNOLOGY";
        else
            str="HEALTH";
        return str;
    }
}