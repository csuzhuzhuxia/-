package com.example.zhuzhuxia.crosscountryevents.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.MainActivity;
import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.model.userInfo;
import com.example.zhuzhuxia.crosscountryevents.myInfo.EditProfileActivity;
import com.example.zhuzhuxia.crosscountryevents.register.RegisterActivity;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

public class LoginActivity extends AppCompatActivity {

    private EditText mAccountEdt;
    private EditText mPasswordEdt;
    private Button mLoginBtn;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findAllViews();
        setListeners();
    }

    private void findAllViews() {
        mAccountEdt = (EditText) findViewById(R.id.account);
        mPasswordEdt = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.login);
        mRegisterBtn = (Button) findViewById(R.id.register);
    }

    private void setListeners() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//登录操作
                login();
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//注册的操作
                register();
            }
        });
    }

    private void register() {
        //注册新用户，跳转到注册页面。
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {

        String accountStr = mAccountEdt.getText().toString().trim();
        final String passwordStr = mPasswordEdt.getText().toString().trim();

        loginRequest.LoginParam param = new loginRequest.LoginParam();
        if(TextUtils.isEmpty(accountStr)||TextUtils.isEmpty(passwordStr)){
            Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        param.action = "Login";
        param.username = accountStr;
        param.password = passwordStr;

        //想服务器进行申请
        loginRequest request = new loginRequest();
        request.setOnResultListener(new baseRequest.OnResultListener<userInfo>() {
            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(LoginActivity.this, "登陆失败:" + msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(userInfo userInfo) {

                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                if(userInfo.is_first_login.equals(userInfo.First_Login)){
                    intent.setClass(LoginActivity.this, EditProfileActivity.class);
                }else{
                    intent.setClass(LoginActivity.this, MainActivity.class);
                }
                CrossCountryApplication.getApplication().setSelfProfile(userInfo);
                CrossCountryApplication.getApplication().getSelfProfile().password = passwordStr;
//                Toast.makeText(LoginActivity.this,CrossCountryApplication.getApplication().getSelfProfile().password,Toast.LENGTH_SHORT).show();
//                intent.putExtra("roomId", roomInfo.roomId);
                startActivity(intent);

                finish();
            }
        });

        String requestUrl = request.getUrl(param);
        request.request(requestUrl);

    }
}
