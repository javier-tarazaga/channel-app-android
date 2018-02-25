package com.fernandocejas.android10.sample.presentation.view.feeds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.BaseFragment;

public class FeedsFragment extends BaseFragment {

  public static FeedsFragment newInstance() {
    return new FeedsFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_feeds, container, false);
    
    return rootView;
  }
}
