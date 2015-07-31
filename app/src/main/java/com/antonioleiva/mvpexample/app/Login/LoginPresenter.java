/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
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

package com.antonioleiva.mvpexample.app.Login;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class LoginPresenter implements OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public String username;
    public String password;
    public ObservableBoolean isProgressing = new ObservableBoolean();
    public ObservableBoolean isUsernameError = new ObservableBoolean();
    public ObservableBoolean isPasswordError = new ObservableBoolean();

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
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
