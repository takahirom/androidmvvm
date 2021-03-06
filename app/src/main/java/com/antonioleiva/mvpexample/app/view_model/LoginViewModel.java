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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;

import com.antonioleiva.mvpexample.app.model.LoginInteractor;
import com.antonioleiva.mvpexample.app.view.LoginView;
import com.antonioleiva.mvpexample.app.model.OnLoginFinishedListener;

public class LoginViewModel implements OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public String username;
    public String password;

    public ObservableBoolean isProgressing = new ObservableBoolean();
    public ObservableBoolean isUsernameError = new ObservableBoolean();
    public ObservableBoolean isPasswordError = new ObservableBoolean();

    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor();
    }

    public void validateCredentials(String username, String password) {
        isProgressing.set(true);
        loginInteractor.login(username, password, this);
    }

    public void onUsernameError() {
        isUsernameError.set(true);

        isProgressing.set(false);
    }

    public void onPasswordError() {
        isPasswordError.set(true);
        isProgressing.set(false);
    }

    public void onSuccess() {
        loginView.navigateToHome();
    }

    public TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = s.toString();
        }
    };

    public TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            password = s.toString();
        }
    };


    public OnClickListener buttonOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            validateCredentials(username, password);
        }
    };
}
