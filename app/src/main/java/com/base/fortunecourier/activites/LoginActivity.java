package com.base.fortunecourier.activites;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.fortunecourier.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity
{
    EditText ed_user_name, ed_password;
    Button btn_login, btn_login_with_facebook;
    TextView tv_forgot_password;
    LinearLayout signUp_layout;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.base.fortunecourier", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken token = loginResult.getAccessToken();
                final String fbToken = token.getToken();
                Bundle myBundle = new Bundle();
                myBundle.putString("fields", "id,email,name,picture,friends{id,name,picture}");
                new GraphRequest(loginResult.getAccessToken(), "/me", myBundle, HttpMethod.GET, new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                                        /* handle the result */
                        if (response.getError() != null) {
                            // handle error
                            System.out.println("ERROR");
                        } else {
                            System.out.println("Success");
                            Log.d("Success", "==============Success=============" + response);
                            JSONObject object = response.getJSONObject();
                            Log.d("Success", "==============object=============" + object);
                            try {
                                if (object != null) {
                                    final String email = object.getString("email");
                                    final String id = object.getString("id");
                                    final String name = object.getString("name");
                                    final String profile_pic = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                    Log.v("LoginActivity", id + ".......email......" + email + ".......name....." + name + "profile_pic" + profile_pic);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                ).executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        initializeView();
    }

    private void initializeView() {
        ed_user_name = (EditText) findViewById(R.id.ed_user_name);
        ed_password = (EditText) findViewById(R.id.ed_password);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login_with_facebook = (Button) findViewById(R.id.btn_login_with_facebook);

        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        signUp_layout = (LinearLayout) findViewById(R.id.signUp_layout);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUp_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        btn_login_with_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;
        String userEmail = ed_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(userEmail)) {
            ed_user_name.setError("Required.");
            valid = false;
        } else if (!userEmail.matches(emailPattern)) {
            valid = false;
            ed_user_name.setError("Invalid email address.");
            // Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        } else {
            ed_user_name.setError(null);
        }

        String userPassword = ed_password.getText().toString();
        if (TextUtils.isEmpty(userPassword)) {
            ed_password.setError("Required.");
            valid = false;
        } else {
            ed_password.setError(null);
        }
        return valid;
    }


    public void ShowDialog() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.forget_password);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btn_submit = (Button) dialog.findViewById(R.id.btn_submit);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final EditText ed_email = (EditText) dialog.findViewById(R.id.ed_email);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String userEmail = ed_email.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    valid = false;
                    ed_email.setError("Required.");
                } else if (!userEmail.matches(emailPattern)) {
                    valid = false;
                    ed_email.setError("Invalid email address.");
                    //  Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                } else {
                    ed_email.setError(null);
                }
                if (valid) {
                    dialog.dismiss();
                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
