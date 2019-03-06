package com.bs.bashuworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bs.dao.SysUserDao;
import com.bs.service.LoginActivity;
import com.bs.service.RegisterActivity;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ToLoginThread toLoginThread = new ToLoginThread();
        toLoginThread.start();

    }
    class ToLoginThread extends Thread {
        @Override
        public void run() {
            try {

                sleep(1000);
                SysUserDao sysUserDao = new SysUserDao();
                int count =  sysUserDao.query(Main3Activity.this);
                if(count<=0){
                    Intent intent = new Intent(Main3Activity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                    startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
