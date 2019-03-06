package com.bs.bashuworld;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private PullToRefreshListView pl;
    private ArrayAdapter<String> adaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pl = (PullToRefreshListView)findViewById(R.id.l);

        List<String> arr = new ArrayList<String>();

        adaper = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);//初始化数据


        pl.setAdapter(adaper);
        pl.setMode(PullToRefreshBase.Mode.BOTH);
        pl.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void,Void,Void>(){

                    @Override
                    protected Void doInBackground(Void... voids) {//后台执行操作,此处用延时模拟同步过程
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {//与数据库交互,添加数据
                        super.onPostExecute(aVoid);
                        adaper.addAll("hello","success");
                        pl.onRefreshComplete();   //通知同步完成
                    }
                }.execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void,Void,Void>(){

                    @Override
                    protected Void doInBackground(Void... voids) {//后台执行操作,此处用延时模拟同步过程
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {//与数据库交互,添加数据
                        super.onPostExecute(aVoid);
                        adaper.addAll("hello","success");
                        pl.onRefreshComplete();   //通知同步完成
                    }
                }.execute();
            }
        });
    }
}
