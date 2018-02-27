package com.fernandocejas.android10.sample.presentation.view.drawer;

import android.os.Parcel;
import android.os.Parcelable;

public class Feed implements Parcelable {

  private String name;
  private boolean isFavorite;
  private int iconResId;

  public Feed(String name, boolean isFavorite, int iconResId) {
    this.name = name;
    this.isFavorite = isFavorite;
    this.iconResId = iconResId;
  }

  protected Feed(Parcel in) {
    name = in.readString();
  }

  public String getName() {
    return name;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public int getIconResId() {
    return iconResId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Feed)) return false;

    Feed feed = (Feed) o;

    if (isFavorite() != feed.isFavorite()) return false;
    return getName() != null ? getName().equals(feed.getName()) : feed.getName() == null;

  }

  @Override
  public int hashCode() {
    int result = getName() != null ? getName().hashCode() : 0;
    result = 31 * result + (isFavorite() ? 1 : 0);
    return result;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Feed> CREATOR = new Creator<Feed>() {
    @Override
    public Feed createFromParcel(Parcel in) {
      return new Feed(in);
    }

    @Override
    public Feed[] newArray(int size) {
      return new Feed[size];
    }
  };
}

