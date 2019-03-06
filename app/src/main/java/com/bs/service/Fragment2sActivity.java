package com.bs.service;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bs.bashuworld.R;

/**
 * Created by admin on 2019/3/5.
 */

public class Fragment2sActivity extends  FragmentsActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        IndexFragment indexFragment = new IndexFragment();
        transaction.add(R.id.fragment_container,indexFragment);
        transaction.show(indexFragment);
        transaction.commit();




    }
}
