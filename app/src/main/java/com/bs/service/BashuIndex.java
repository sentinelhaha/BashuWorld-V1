package com.bs.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bs.bashuworld.R;
import com.bs.common.BashuApplication;
import com.bs.common.CityAdapter;
import com.bs.entity.ServerResult;
import com.bs.entity.SysCity;

import java.util.ArrayList;

/**
 * Created by admin on 2019/3/7.
 */

public class BashuIndex extends AppCompatActivity{
    private ArrayList<SysCity> cityList = new ArrayList<SysCity>();
    Bitmap bitmap=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_index);
        /*Toast.makeText(LoginActivity.this,"我是飞猪",Toast.LENGTH_SHORT).show();*/
      /*  http://192.168.2.21:8080/CROWN-BS-SYS-V1/city/findPageObjects.do*/

        String url = BashuApplication.serverIP+"/CROWN-BS-SYS-V1/city/findPageObjects.do?pageCurrent=1";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.print(s+"city result");
                System.out.print(s.toString()+"city result");
                ServerResult result = (ServerResult) JSON.parseObject(s, ServerResult.class);
                if (result.getState() == 1) {

                    SysCity city;
                    JSONObject data = (JSONObject)result.getData();
                    JSONArray records = (JSONArray) data.get("records");
                    for(int i = 0; i < records.size(); i++) {
                        city = new SysCity();
                        JSONObject citylist= (JSONObject) records.get(i);
                        String city_name = (String) citylist.get("city_name");
                        String city_jj = (String)  citylist.get("city_jj");
                        String city_img = (String)citylist.get("city_img");
                        city.setCity_name(city_name);
                        city.setCity_jj(city_jj);
                       city.setCity_img(city_img);
                       /* String imagurl = "http://180.178.56.50:8002/static/lunbo/"+city_img;
                        MyImageView myImageView = (MyImageView) findViewById(R.id.cityImage5);
                        myImageView.setImageURL(imagurl);*/
                        cityList.add(i,city);
                    }
                    CityAdapter adapter =
                     new CityAdapter(BashuIndex.this,R.layout.activity_bashu_city,cityList);
                    ListView listView = (ListView) findViewById(R.id.city_list_view);
                    listView.setAdapter(adapter);





                    Toast.makeText(BashuIndex.this, "城市信息获取成功", Toast.LENGTH_SHORT).show();
                    //这里要注意一下,我暂时把登录成功后得界面放在Main上了

                } else {
                    Toast.makeText(BashuIndex.this, "城市信息获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("出错了"+volleyError.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(BashuIndex.this);
        requestQueue.add(request);
    }

}
