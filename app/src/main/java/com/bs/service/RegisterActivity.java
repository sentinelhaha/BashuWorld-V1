package com.bs.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.bs.bashuworld.R;
import com.bs.common.BashuApplication;
import com.bs.entity.ServerResult;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = (Button)
                findViewById(R.id.register_submit_btn);
        Button loginBtn = (Button)
                findViewById(R.id.register_login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(RegisterActivity.this,"我是跑跑",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText usernameEt = (EditText) findViewById(R.id.register_username_editText);
                    EditText passwordEt = (EditText) findViewById(R.id.register_password_editText);
                    EditText confirmPasswordEt = (EditText) findViewById(R.id.register_confirmPassword_editText);
                    EditText emailEt = (EditText) findViewById(R.id.register_email_editText);
                    EditText phoneEt = (EditText) findViewById(R.id.register_phone_editText);

                    String username = usernameEt.getText().toString();
                    String passowrd = passwordEt.getText().toString();
                    String confirmPassword = confirmPasswordEt.getText().toString();
                    String phone = phoneEt.getText().toString();
                    String email = emailEt.getText().toString();

                    //localhost:8080/CROWN-BS-SYS-V1/user/
                    // register.do?user=jackson&password=123456
                    if (passowrd.equals(confirmPassword)) {
                        String url = BashuApplication.serverIP +
                                "/CROWN-BS-SYS-V1/user/register.do?" +
                                "user=" + username +
                                "&password=" + passowrd;
                               /* "&phone=" + phone +
                                "&email=" + email*/
                        MyListener myListener = new MyListener();
                        MyErrorListener myErrorListener = new MyErrorListener();
                        StringRequest request = new StringRequest(url, myListener, myErrorListener);

                        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                        requestQueue.add(request);
                    } else {
                        Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    //内部类
    class MyListener implements Response.Listener<String> {

        @Override
        public void onResponse(String s) {
            Log.i("bs", s);
            //4.3 收数据
            //4.4 把json转成对象
            ServerResult result = JSON.parseObject(s, ServerResult.class);
            if (result.getState() == 1) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent  = new Intent( RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();

            }
        }
    }
    class MyErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.i("bs", volleyError.getMessage());
        }
    }


}
