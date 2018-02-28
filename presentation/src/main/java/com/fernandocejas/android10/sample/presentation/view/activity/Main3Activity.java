package com.fernandocejas.android10.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.BaseActivity;
import com.fernandocejas.android10.sample.presentation.view.explore.ExploreFragment;
import com.fernandocejas.android10.sample.presentation.view.home.HomeFragment;
import com.fernandocejas.android10.sample.presentation.view.settings.SettingsFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Main application screen. This is the app entry point.
 */
public class Main3Activity extends BaseActivity {

  @BindView(R.id.bottom_nav_main) BottomNavigationViewEx bottomNavigationView;

  private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemReselectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.nav_home:
              replaceFragment(R.id.fragment_container, HomeFragment.newInstance());
              break;
            case R.id.nav_read_later:
              // replaceFragment(R.id.fragment_container, FeedsFragment.newInstance(feedModel));
              break;
            case R.id.nav_explore:
              replaceFragment(R.id.fragment_container, ExploreFragment.newInstance());
              break;
            case R.id.nav_drawer:
              replaceFragment(R.id.fragment_container, SettingsFragment.newInstance());
              break;
          }

          return true;
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main3);
    ButterKnife.bind(this);

    setupView();
  }

  private void setupView() {
    bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener);
    bottomNavigationView.setCurrentItem(0);
    bottomNavigationView.enableShiftingMode(false);
    bottomNavigationView.enableItemShiftingMode(false);
    bottomNavigationView.setTextVisibility(false);
  }
}
