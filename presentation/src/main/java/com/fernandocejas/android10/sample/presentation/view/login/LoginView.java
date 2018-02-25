package com.fernandocejas.android10.sample.presentation.view.login;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a login.
 */
public interface LoginView {

  /**
   * Launch the Feedly Login view
   *
   * @param loginUrl The loginUrl used to login into Feedly
   */
  void launchLoginView(String loginUrl);
}
