package com.barranquero.logintextinputlayout;

import android.app.Application;

import com.barranquero.logintextinputlayout.model.User;

/**
 * Class which stores a User in the Application class
 */
public class LoginTextInputLayout_Application extends Application {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
