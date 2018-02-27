package com.fernandocejas.android10.sample.presentation.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.view.BaseActivity;
import com.fernandocejas.android10.sample.presentation.view.explore.ExploreFragment;
import com.fernandocejas.android10.sample.presentation.view.feeds.FeedsFragment;
import com.fernandocejas.android10.sample.presentation.view.home.HomeFragment;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.CategoryAdapter;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.CategoryModel;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.FeedModel;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.OnFeedItemClickListener;
import com.fernandocejas.android10.sample.presentation.view.settings.SettingsFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent>, MainView,
    NavigationView.OnNavigationItemSelectedListener {

  @Inject MainPresenter mainPresenter;

  @BindView(R.id.bottom_nav_main) BottomNavigationViewEx bottomNavigationView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  private MainComponent mainComponent;
  private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemReselectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.nav_home:
              replaceFragment(R.id.fragment_container, HomeFragment.newInstance());
              break;
            case R.id.nav_read_later:
              replaceFragment(R.id.fragment_container, FeedsFragment.newInstance());
              break;
            case R.id.nav_explore:
              replaceFragment(R.id.fragment_container, ExploreFragment.newInstance());
              break;
            case R.id.nav_drawer:
              drawerLayout.openDrawer(GravityCompat.END);
              break;
          }

          return true;
        }
      };


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initializeInjector();

    ButterKnife.bind(this);

    this.mainPresenter.setView(this);
    setupView();

    if (savedInstanceState == null) {
      this.loadCategoryList();
    }
  }

  @Override protected void onResume() {
    super.onResume();
    this.mainPresenter.resume();
  }

  @Override protected void onPause() {
    super.onPause();
    this.mainPresenter.pause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    this.mainPresenter.destroy();
  }

  @Override public void onBackPressed() {
    if (this.drawerLayout.isDrawerOpen(GravityCompat.END)) {
      this.drawerLayout.closeDrawer(GravityCompat.END);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
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

    switch (id) {
      case R.id.nav_camera:
        replaceFragment(R.id.fragment_container, SettingsFragment.newInstance());
        break;
      case R.id.nav_gallery:
        break;
      case R.id.nav_slideshow:
        break;
      case R.id.nav_manage:
        break;
      case R.id.nav_share:
        break;
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.END);
    return true;
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showRetry() {

  }

  @Override public void hideRetry() {

  }

  @Override public void showError(String message) {

  }

  @Override public Context context() {
    return this;
  }

  @Override public void renderCategoryList(List<CategoryModel> categoryModelsList) {
    if (categoryModelsList != null) {
      CategoryAdapter adapter = new CategoryAdapter(categoryModelsList);
      adapter.setOnArticleItemClickListener(new OnFeedItemClickListener() {
        @Override public boolean onFeedItemClick(FeedModel feedModel) {
          Toast.makeText(MainActivity.this, feedModel.getTitle(), Toast.LENGTH_LONG).show();
          return false;
        }
      });

      this.recyclerView.setAdapter(adapter);
    }
  }

  private void initializeInjector() {
    this.mainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();

    this.mainComponent.inject(this);
  }

  private void setupView() {
    setupDrawer();
    setupNavigation();
    setupBottomNavigationView();
  }

  private void setupDrawer() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
  }

  private void setupNavigation() {
    //NavigationView navigationView = findViewById(R.id.nav_view);
    //navigationView.setNavigationItemSelectedListener(this);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);

    // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
    // Specifically when you call notifyItemChanged() it does a fade animation for the changing
    // of the data in the ViewHolder. If you would like to disable this you can use the following:
    RecyclerView.ItemAnimator animator = this.recyclerView.getItemAnimator();
    if (animator instanceof DefaultItemAnimator) {
      ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
    }

    this.recyclerView.setLayoutManager(layoutManager);
  }

  private void setupBottomNavigationView() {
    this.bottomNavigationView.setOnNavigationItemSelectedListener(this.onNavigationItemReselectedListener);
    this.bottomNavigationView.setCurrentItem(0);
    this.bottomNavigationView.enableShiftingMode(false);
    this.bottomNavigationView.enableItemShiftingMode(false);
    this.bottomNavigationView.setTextVisibility(false);
  }

  private void loadCategoryList() {
    this.mainPresenter.initialize();
  }

  @Override public MainComponent getComponent() {
    return this.mainComponent;
  }
}
