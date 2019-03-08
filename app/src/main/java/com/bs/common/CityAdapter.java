package com.bs.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.bashuworld.R;
import com.bs.entity.SysCity;
import com.bs.service.BashuIndex;
import com.bs.service.LoginActivity;
import com.bs.service.MyImageView;

import java.util.List;

/**
 * Created by admin on 2019/3/7.
 */

public class CityAdapter extends ArrayAdapter<SysCity> implements View.OnClickListener{
    private int resourceId;
    private OnItemSelectedListener citylistener;

    public void setCitylistener(OnItemSelectedListener citylistener) {
        this.citylistener = citylistener;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemSelectedListener {
        public void onItemSelect(int index,String indexString);
    }
    public CityAdapter(Context context, int resource, List<SysCity> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SysCity city = getItem(position);
        View cityView = LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView  cityName = (TextView) cityView.findViewById(R.id.cityName);
        TextView cityJj = (TextView) cityView.findViewById(R.id.cityjj);
        /* button.setOnClickListener((View.OnClickListener) this);*/




        cityName.setText(city.getCity_name());
        cityJj.setText(city.getCity_jj());
        MyImageView myImageView = (MyImageView) cityView.findViewById(R.id.cityImage5);
        String imagurl = "http://180.178.56.50:8002/static/lunbo/"+city.getCity_img();
        myImageView.isUseCache = true;
        myImageView.setImageURL(imagurl);


        return cityView;
    }


}
