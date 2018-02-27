package com.fernandocejas.android10.sample.presentation.view.main;

import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.interactors.GetCategoryList;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.CategoryModel;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.mapper.CategoryModelDataMapper;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class MainPresenter implements Presenter {

  private final GetCategoryList getCategoryList;
  private final CategoryModelDataMapper categoryModelDataMapper;

  private MainView mainView;

  @Inject public MainPresenter(GetCategoryList getCategoryList, CategoryModelDataMapper categoryModelDataMapper) {
    this.getCategoryList = getCategoryList;
    this.categoryModelDataMapper = categoryModelDataMapper;
  }

  public void setView(MainView mainView) {
    this.mainView = mainView;
  }

  @Override public void resume() {

  }

  @Override public void pause() {

  }

  @Override public void destroy() {
    this.getCategoryList.dispose();
    this.mainView = null;
  }

  void initialize() {
    loadCategoryList();
  }

  private void loadCategoryList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getCategoryList.execute(new CategoriesObserver(), null);
  }

  private void showViewLoading() {
    this.mainView.showLoading();
  }

  private void hideViewLoading() {
    this.mainView.hideLoading();
  }

  private void showViewRetry() {
    this.mainView.showLoading();
  }

  private void hideViewRetry() {
    this.mainView.hideRetry();
  }

  private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
    //TODO
  }

  private void showCategorieListInView(List<Category> categoryCollection) {
    final Collection<CategoryModel> categoryModelsCollection =
        this.categoryModelDataMapper.transform(categoryCollection);
    this.mainView.renderCategoryList(categoryModelsCollection);
  }

  private final class CategoriesObserver extends DefaultObserver<List<Category>> {

    @Override public void onComplete() {
      MainPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      MainPresenter.this.hideViewLoading();
      MainPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      MainPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Category> categoryList) {
      MainPresenter.this.showCategorieListInView(categoryList);
    }
  }
}
