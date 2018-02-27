package com.fernandocejas.android10.sample.presentation.view.main;

import com.fernandocejas.android10.sample.presentation.view.LoadDataView;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.CategoryModel;
import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the main view of the app.
 */
public interface MainView extends LoadDataView {
  void renderCategoryList(Collection<CategoryModel> categoryModelsCollection);
}
