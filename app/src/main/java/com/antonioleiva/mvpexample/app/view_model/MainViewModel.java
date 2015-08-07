/*
 *
 *  * Copyright (C) 2015 takahirom
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.antonioleiva.mvpexample.app.view_model;

import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.AdapterView;

import com.antonioleiva.mvpexample.app.model.FindItemsInteractor;
import com.antonioleiva.mvpexample.app.model.OnFindItemsFinishedListener;
import com.antonioleiva.mvpexample.app.view.MainView;

import java.util.List;

public class MainViewModel implements OnFindItemsFinishedListener {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;
    public ObservableBoolean isProgressing = new ObservableBoolean();

    public MainViewModel(MainView mainView) {
        this.mainView = mainView;
        findItemsInteractor = new FindItemsInteractor();
    }

    public void onResume() {
        isProgressing.set(true);
        findItemsInteractor.findItems(this);
    }

    @Override
    public void onFinished(List<String> items) {
        mainView.setItems(items);
        isProgressing.set(false);
    }

    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    };
}
