package com.fernandocejas.android10.sample.presentation.view.main.drawer;

import android.os.Parcel;
import android.os.Parcelable;

public class FeedModel implements Parcelable {

  private final String id;
  private final String title;
  private String iconUrl;

  public FeedModel(String id, String title) {
    this.id = id;
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  protected FeedModel(Parcel in) {
    id = in.readString();
    title = in.readString();
    iconUrl = in.readString();
  }

  public static final Creator<FeedModel> CREATOR = new Creator<FeedModel>() {
    @Override public FeedModel createFromParcel(Parcel in) {
      return new FeedModel(in);
    }

    @Override public FeedModel[] newArray(int size) {
      return new FeedModel[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(title);
    parcel.writeString(iconUrl);
  }
}

