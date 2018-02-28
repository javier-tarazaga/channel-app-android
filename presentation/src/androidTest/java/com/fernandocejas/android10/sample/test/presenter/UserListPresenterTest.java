/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.test.presenter;

import android.content.Context;
import com.fernandocejas.android10.sample.domain.interactor.GetUserList;
import com.fernandocejas.android10.sample.presentation.mapper.UserModelDataMapper;
import com.fernandocejas.android10.sample.presentation.view.stream.StreamPresenter;
import com.fernandocejas.android10.sample.presentation.view.stream.StreamView;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserListPresenterTest {

  private StreamPresenter streamPresenter;

  @Mock private Context mockContext;
  @Mock private StreamView mockStreamView;
  @Mock private GetUserList mockGetUserList;
  @Mock private UserModelDataMapper mockUserModelDataMapper;

  @Before
  public void setUp() {
    streamPresenter = new StreamPresenter(mockGetUserList, mockUserModelDataMapper);
    streamPresenter.setView(mockStreamView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testUserListPresenterInitialize() {
    given(mockStreamView.context()).willReturn(mockContext);

    streamPresenter.initialize();

    verify(mockStreamView).hideRetry();
    verify(mockStreamView).showLoading();
    verify(mockGetUserList).execute(any(DisposableObserver.class), any(Void.class));
  }
}
