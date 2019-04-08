package com.rimbolapeh.contentarticle.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rimbolapeh.contentarticle.R;
import com.rimbolapeh.contentarticle.model.retrofit.ResponseLogin;
import com.rimbolapeh.contentarticle.network.InitRetrofit;
import com.rimbolapeh.contentarticle.network.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_register, btn_login;
    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                Toast.makeText(LoginActivity.this, "Anda memilih register", Toast.LENGTH_SHORT).show();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        RestApi restApi = InitRetrofit.getInstance();

        Call<ResponseLogin> loginCall = restApi.loginUser(

                et_email.getText().toString(),
                et_password.getText().toString()
        );

        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getResponse();

                    if (result.equals("success")) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else if (result.equals("failed")) {
                        Toast.makeText(LoginActivity.this, "username/password anda salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Periksa koneksi anda", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
