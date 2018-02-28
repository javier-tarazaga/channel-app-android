package com.fernandocejas.android10.sample.presentation.view.feeds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.BaseFragment;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.FeedModel;
import com.fernandocejas.android10.sample.utils.Preconditions;

public class FeedsFragment extends BaseFragment {

  private static final String PARAM_FEED_MODEL = "param_user_id";

  public static FeedsFragment newInstance(FeedModel feedModel) {
    final FeedsFragment feedsFragment = new FeedsFragment();
    final Bundle arguments = new Bundle();
    arguments.putParcelable(PARAM_FEED_MODEL, feedModel);
    feedsFragment.setArguments(arguments);

    return feedsFragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_feeds, container, false);

    TextView textView = rootView.findViewById(R.id.subscription_id);

    FeedModel feedModel = currentFeedModel();
    Preconditions.checkNotNull(feedModel, "The SubscriptionModel cannot be null");
    textView.setText(feedModel.getTitle());

    return rootView;
  }

  /**
   * Get current {@link FeedModel} from fragments arguments.
   */
  private FeedModel currentFeedModel() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
    return arguments.getParcelable(PARAM_FEED_MODEL);
  }
}
