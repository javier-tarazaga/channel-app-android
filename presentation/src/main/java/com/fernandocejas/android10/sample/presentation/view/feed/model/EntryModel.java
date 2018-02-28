/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.presentation.view.feed.model;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * Class that represents an entry in the presentation layer.
 */
public class EntryModel implements Serializable {

  private final String id;

  private String title;
  private String summary;
  private String imageUrl;
  private String contentInHtml;

  public EntryModel(String id) {
    this.id = id;
  }

  @NonNull public String getId() {
    return id;
  }

  @NonNull public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @NonNull public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  @NonNull public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @NonNull public String getContentInHtml() {
    return contentInHtml;
  }

  public void setContentInHtml(String contentInHtml) {
    this.contentInHtml = contentInHtml;
  }
}
