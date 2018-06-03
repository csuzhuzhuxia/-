package com.example.zhuzhuxia.crosscountryevents.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.login.LoginActivity;
import com.example.zhuzhuxia.crosscountryevents.model.userInfo;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar mTitlebar;

    private EditText mAccountEdt;
    private EditText mPasswordEdt;
    private EditText mConfirmPasswordEt;

    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findAllViews();
        setListeners();
        setTitleBar();

    }

    private void setTitleBar() {
        mTitlebar.setTitle("注册新用户");
        mTitlebar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mTitlebar);
    }

    private void findAllViews() {
        mTitlebar = (Toolbar) findViewById(R.id.titlebar);

        mAccountEdt = (EditText) findViewById(R.id.account);
        mPasswordEdt = (EditText) findViewById(R.id.password);
        mConfirmPasswordEt = (EditText) findViewById(R.id.confirm_password);
        mRegisterBtn = (Button) findViewById(R.id.register);
    }

    private void setListeners() {
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册
                register();
            }
        });
    }

    private void register() {
        String accountStr = mAccountEdt.getText().toString();
        String passwordStr = mPasswordEdt.getText().toString();
        String confirmPswStr = mConfirmPasswordEt.getText().toString();
        String email = mPasswordEdt.getText().toString();
        String telphone = mConfirmPasswordEt.getText().toString();

        if (TextUtils.isEmpty(accountStr) ||
                TextUtils.isEmpty(passwordStr) ||
                TextUtils.isEmpty(confirmPswStr)) {
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordStr.equals(confirmPswStr)) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }


        register_request(accountStr,passwordStr,email,telphone);

    }


    private void register_request(String accountStr,String passwordStr,String email,String telphone) {


        RegisterRequest.RegisterParam param = new RegisterRequest.RegisterParam();


        param.action = "Register";
        param.username = accountStr;
        param.password = passwordStr;
        param.email = email;
        param.telphone = telphone;

        //想服务器进行申请
        RegisterRequest request = new RegisterRequest();
        request.setOnResultListener(new baseRequest.OnResultListener<userInfo>() {
            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
//                register_request(name,pass);
            }

            @Override
            public void onSuccess(userInfo login) {

                Toast.makeText(RegisterActivity.this, "账号注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
//                intent.putExtra("roomId", roomInfo.roomId);
                startActivity(intent);
                finish();
            }
        });

        String requestUrl = request.getUrl(param);
        request.request(requestUrl);


    }





}
