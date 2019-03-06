package com.bs.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bs.bashuworld.Main2Activity;
import com.bs.bashuworld.MainActivity;
import com.bs.bashuworld.R;
import com.bs.common.BashuApplication;
import com.bs.dao.SysUserDao;
import com.bs.entity.ServerResult;

public class LoginActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //单击登录界面中的注册按钮跳到注册界面
        Button registerButton = (Button)
                findViewById(R.id.login_registerButton);
        Button loginButton = (Button)
                findViewById(R.id.login_loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this
                        , RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(LoginActivity.this,"我是飞猪",Toast.LENGTH_SHORT).show();*/
                EditText usernameEditText = (EditText) findViewById(R.id.login_usernameEditText);
                EditText passwordEditText = (EditText) findViewById(R.id.login_paswordEditText);
                username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }else {
                    String url = BashuApplication.serverIP+"/CROWN-BS-SYS-V1/user/loginphone.do?"+
                            "user="+username+
                            "&password="+password;
                    StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            System.out.print(s+"login result");
                            System.out.print(s.toString()+"login result");
                            ServerResult result = (ServerResult) JSON.parseObject(s, ServerResult.class);
                            if (result.getState() == 1) {
                                SysUserDao sysUserDao = new SysUserDao();
                                sysUserDao.insert(LoginActivity.this,username);
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                //这里要注意一下,我暂时把登录成功后得界面放在Main上了
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("出错了"+volleyError.getMessage());
                        }
                    });
                    RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                    requestQueue.add(request);
                }
            }
        });

    }
}
