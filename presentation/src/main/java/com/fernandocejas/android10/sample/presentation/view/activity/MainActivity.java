package com.fernandocejas.android10.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.BaseActivity;
import com.fernandocejas.android10.sample.presentation.view.explore.ExploreFragment;
import com.fernandocejas.android10.sample.presentation.view.feeds.FeedsFragment;
import com.fernandocejas.android10.sample.presentation.view.home.HomeFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.bottom_nav_main) BottomNavigationViewEx bottomNavigationView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

  private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemReselectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.nav_home:
              replaceFragment(R.id.fragment_container, HomeFragment.newInstance());
              break;
            case R.id.nav_feeds:
              replaceFragment(R.id.fragment_container, FeedsFragment.newInstance());
              break;
            case R.id.nav_explore:
              replaceFragment(R.id.fragment_container, ExploreFragment.newInstance());
              break;
            case R.id.nav_settings:
              drawerLayout.openDrawer(GravityCompat.END);
              break;
          }

          return true;
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setupView();
  }

  @Override public void onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
      drawerLayout.closeDrawer(GravityCompat.END);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main2, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.END);
    return true;
  }

  private void setupView() {
    setupToolbar();
    setupNavigation();
    bottomNavigationView();
  }

  private void setupToolbar() {
    //Toolbar toolbar = findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
  }

  private void setupNavigation() {
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void bottomNavigationView() {
    bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener);
    bottomNavigationView.setCurrentItem(0);
    bottomNavigationView.enableShiftingMode(false);
    bottomNavigationView.enableItemShiftingMode(false);
    bottomNavigationView.setTextVisibility(false);
  }
}
