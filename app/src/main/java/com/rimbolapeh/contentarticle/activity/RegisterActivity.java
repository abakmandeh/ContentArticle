package com.rimbolapeh.contentarticle.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rimbolapeh.contentarticle.R;
import com.rimbolapeh.contentarticle.model.retrofit.ResponseRegister;
import com.rimbolapeh.contentarticle.network.InitRetrofit;
import com.rimbolapeh.contentarticle.network.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register, btn_login;
    EditText et_username, et_email, et_password, et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);

        et_username = (EditText) findViewById(R.id.et_username);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_phone = (EditText) findViewById(R.id.et_phone);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void register(){

        RestApi restApi = InitRetrofit.getInstance();

        Call<ResponseRegister> registerCall = restApi.registerUser(

                et_username.getText().toString(),
                et_email.getText().toString(),
                et_phone.getText().toString(),
                et_password.getText().toString()
        );

        registerCall.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()){
                    String result = response.body().getResponse();

                    if (result.equals("success")){
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else if (result.equals("failed")){
                        Toast.makeText(RegisterActivity.this, "username / password anda salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Perikasa koneksi anda", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
