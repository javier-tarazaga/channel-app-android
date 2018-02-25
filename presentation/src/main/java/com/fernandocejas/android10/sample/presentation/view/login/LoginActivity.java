package com.fernandocejas.android10.sample.presentation.view.login;

import android.os.Bundle;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.view.BaseActivity;

public class LoginActivity extends BaseActivity implements HasComponent<LoginComponent> {

  private LoginComponent loginComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_layout);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      addFragment(R.id.fragment_container, LoginFragment.newInstance());
    }
  }

  private void initializeInjector() {
    this.loginComponent = DaggerLoginComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public LoginComponent getComponent() {
    return this.loginComponent;
  }
}
