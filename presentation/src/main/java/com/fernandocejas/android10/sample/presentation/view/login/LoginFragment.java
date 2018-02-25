package com.fernandocejas.android10.sample.presentation.view.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.presenter.LoginPresenter;
import com.fernandocejas.android10.sample.presentation.view.BaseFragment;
import javax.inject.Inject;

public class LoginFragment extends BaseFragment implements LoginView {

  @Inject LoginPresenter loginPresenter;

  @BindView(R.id.btn_login) Button btn_login;

  private Unbinder viewUnbinder;

  public static LoginFragment newInstance() {
    return new LoginFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(LoginComponent.class).inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);

    viewUnbinder = ButterKnife.bind(this, fragmentView);

    return fragmentView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.loginPresenter.setView(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    viewUnbinder.unbind();
  }

  @OnClick(R.id.btn_login) public void onLoginButtonClicked(View view) {
    this.loginPresenter.onLoginButtonClicked();
  }

  @Override public void launchLoginView(String loginUrl) {
    Toast.makeText(getActivity(), "Hola", Toast.LENGTH_LONG).show();
  }
}
