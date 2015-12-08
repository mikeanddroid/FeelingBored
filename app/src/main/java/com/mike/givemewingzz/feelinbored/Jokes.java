package com.mike.givemewingzz.feelinbored;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mike.givemewingzz.feelinbored.AppModel.JokeCategories;
import com.mike.givemewingzz.feelinbored.Services.FetchCategories;
import com.mike.givemewingzz.feelinbored.Services.FetchJokes;
import com.mike.givemewingzz.feelinbored.Utils.OttoHelper;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Jokes extends BaseJokeActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = Jokes.class.getSimpleName();

    @Bind(R.id.makeRandomJokeCall)
    public Button makeRandomCall;

    @Bind(R.id.makeJokeCategoryCall)
    public Button makeCategoryCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        // Initializing listeners for views.
        initFields();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initFields() {
        makeRandomCall.setOnClickListener(onClickListener);
        makeCategoryCall.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int id = v.getId();

            switch (id) {

                case R.id.makeRandomJokeCall:
                    FetchJokes.call(Jokes.this);
                    break;

                case R.id.makeJokeCategoryCall:
                    FetchCategories.call(Jokes.this);
                    break;

            }

        }
    };

    @Override
    protected void onPause() {
        OttoHelper.unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        OttoHelper.register(this);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe
    public void onRandomSuccess(FetchJokes.SuccessEvent successEvent) {

        displaySimpleConfirmSnackBar(makeCategoryCall, "Success on making Random Joke call.");

        Log.d(TAG + " Random Joke : ", successEvent.getSiRandomJokeWrapper().getValue().getJoke());

    }

    @Subscribe
    public void onCategorySuccess(FetchCategories.SuccessEvent successEvent) {

        displaySimpleConfirmSnackBar(makeCategoryCall, "Success on making Joke category call.");

        for (JokeCategories jokeCategories : successEvent.getJokeCategoriesRealmList()) {
            Log.d(TAG + " Joke : ", jokeCategories.getJokeCategory());
        }
    }

}
