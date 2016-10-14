package com.barranquero.logintextinputlayout;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

/**
 * This application uses the Model-View-Presenter philosophy
 * Class which shows a Login Activity in a RelativeLayout
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class LoginTextInputLayout_Activity extends AppCompatActivity implements ILoginMvp.View {

    private ILoginMvp.Presenter mLoginMvp;
    private EditText mEdtPassword;
    private EditText mEdtUser;
    private Button mBtnLogin;
    private TextInputLayout mTilUser;
    private TextInputLayout mTilPassword;
    private final String TAG = "Login_Activity";

    /**
     * Method which initialises and shows the Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_textinputlayout);

        mLoginMvp = new LoginTextInputLayout_Presenter(this);  // The Presenter has an Activity instance

        mEdtUser = (EditText)findViewById(R.id.edtUser);
        mEdtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTilUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdtPassword = (EditText)findViewById(R.id.edtPassword);

        mBtnLogin = (Button)findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View view) {
                String user = mEdtUser.getText().toString();
                String password = mEdtPassword.getText().toString();
                mLoginMvp.validateCredentials(user, password);
            }
        });

        mTilPassword = (TextInputLayout)findViewById(R.id.tilPassword);
        mTilUser = (TextInputLayout)findViewById(R.id.tilUser);

        // Checking the persistence of the Application object
        if (((LoginTextInputLayout_Application)this.getApplicationContext()).getUser() != null)
            Log.d(TAG, ((LoginTextInputLayout_Application)this.getApplicationContext()).getUser().toString());

        Log.d(TAG, "Initialised Activity");
    }

    /**
     * Method which shows an error to the user
     * @param error The string which explains the occurred error
     */
    @Override
    public void setMessageError(String error, int idView) {
        switch (idView) {
            case R.id.edtPassword:
                mTilPassword.setError(error);
                break;
            case R.id.edtUser:
                mTilUser.setError(error);
        }
        //Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Finalised Activity");
    }
}
