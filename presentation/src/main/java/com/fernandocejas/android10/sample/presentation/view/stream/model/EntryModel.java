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
package com.fernandocejas.android10.sample.presentation.view.stream.model;

import java.util.List;

/**
 * Class that represents an entry in the presentation layer.
 */
public class EntryModel {

  private final String id;

  private String title;
  private String summary;
  private List<EntryModel.ThumbnailModel> thumbnailList;

  public EntryModel(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public List<EntryModel.ThumbnailModel> getThumbnailList() {
    return thumbnailList;
  }

  public void setThumbnailList(List<EntryModel.ThumbnailModel> thumbnailList) {
    this.thumbnailList = thumbnailList;
  }

  public static class ThumbnailModel {
    private final String url;

    public ThumbnailModel(String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
    }
  }
}
