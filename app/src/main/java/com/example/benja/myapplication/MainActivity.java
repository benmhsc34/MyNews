package com.example.benja.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.benja.myapplication.Tabs.CustomStoriesTab;
import com.example.benja.myapplication.Tabs.PopularStoriesTab;
import com.example.benja.myapplication.Tabs.TopStoriesTab;
import com.example.benja.myapplication.Toolbar.AboutActivity;
import com.example.benja.myapplication.Toolbar.HelpActivity;
import com.example.benja.myapplication.Toolbar.NotificationActivity;
import com.example.benja.myapplication.Toolbar.SearchActivity;

import java.util.Objects;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle mDrawerToggle;

        ImageView searchIcon = findViewById(R.id.searchIcon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
                MainActivity.this.startActivity(myIntent);


            }
        });

        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_reorder_black_24dp);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onBackPressed() {
        // Close the menu so open and if the touch return is pushed
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            Intent myIntent = new Intent(MainActivity.this, NotificationActivity.class);
            MainActivity.this.startActivity(myIntent);
            return true;
        }
        if (id == R.id.action_help) {
            Intent myIntent = new Intent(MainActivity.this, HelpActivity.class);
            MainActivity.this.startActivity(myIntent);

            return true;
        }
        if (id == R.id.action_about) {
            Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(myIntent);

            return true;
        }
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {


            case R.id.nav_top:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.nav_pop:
                mViewPager.setCurrentItem(1);

                break;
            case R.id.nav_cus:
                mViewPager.setCurrentItem(2);

                break;
            case R.id.nav_share:
                Toast.makeText(this, "You share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contactUs:
                Toast.makeText(this, "You want to contact us x)", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new TopStoriesTab();

                case 1:
                    return new PopularStoriesTab();
                case 2:
                    return new CustomStoriesTab();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Top Stories";
                case 1:
                    return "Most Popular Stories";
                case 2:
                    return "Custom Stories";
            }
            return null;
        }
    }
}
