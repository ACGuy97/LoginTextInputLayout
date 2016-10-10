package com.barranquero.logintextinputlayout;


import android.content.Context;
import android.text.TextUtils;

import com.barranquero.logintextinputlayout.model.User;

/**
 * Class that controls the view and implements the Login rules
 *  - At least one upper case and one lower case character
 *  - At least one digit
 *  - At least 8 characters long
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class LoginTextInputLayout_Presenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginTextInputLayout_Presenter(ILoginMvp.View view) {
        this.view = view;
    }

    /**
     * Method which checks whether the password the user has entered complies with the rules and saves the username and password
     * @param user The username entered in the username field
     * @param password The password entered in the password field
     */
    @Override
    public void validateCredentials(String user, String password) {
        if (TextUtils.isEmpty(user))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty_error), R.id.edtUser);
        else if (TextUtils.isEmpty(password))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty_error), R.id.edtPassword);
        else {
            if (!(password.matches("(.*)\\d(.*)")))
                view.setMessageError(((Context)view).getResources().getString(R.string.password_digit), R.id.edtPassword);
            if (!(password.matches("(.*)\\p{Lower}(.*)") && password.matches("(.*)\\p{Upper}(.*)")))
                view.setMessageError(((Context)view).getResources().getString(R.string.password_case), R.id.edtPassword);
            if (password.length() < 8)
                view.setMessageError(((Context)view).getResources().getString(R.string.password_length), R.id.edtPassword);
            else {
                // We save the user in the Application class
                ((LoginTextInputLayout_Application)((Context)view).getApplicationContext()).setUser(new User(user,password));
            }
        }
    }
}
