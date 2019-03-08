package com.bs.bashuworld;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.bs.common.BashuApplication;
import com.bs.service.BackHandledFragment;
import com.bs.service.FragmentsActivity;
import com.bs.service.IndexFragment;
import com.bs.service.MeFragment;
import com.bs.service.MessageFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView webView;
    private FragmentManager fragmentManager;

    private BackHandledFragment mBackHandledFragment;
    private boolean hadIntercept;

    Fragment[] fragments = new Fragment[3];
    Button[] buttons = new Button[3];

    Fragment[] navigations=new Fragment[4];

    int currentNavigationIndex=0;
    int clickNavigationIndex=0;
    int currentFragmentIndex=0;
    int clickBtnIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager manager =  getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        IndexFragment indexFragment = new IndexFragment();
        transaction.add(R.id.fragment_container,indexFragment);
        transaction.show(indexFragment);
        transaction.commit();

        MessageFragment messageFragment = new MessageFragment();
        MeFragment meFragment = new MeFragment();
        fragments[0]=indexFragment;
        fragments[1]=messageFragment;
        fragments[2]=meFragment;


        navigations[0]=indexFragment;
        navigations[1]=messageFragment;
        navigations[2]=meFragment;
        navigations[3]=meFragment;


        buttons[0]= (Button) findViewById(R.id.btn_main_fragment_home1);
        buttons[1]= (Button) findViewById(R.id.btn_main_fragment_message1);
        buttons[2]= (Button) findViewById(R.id.btn_main_fragment_me1);
        MyBtnListener myBtnListener = new MyBtnListener();

        for(Button button:buttons){
            button.setOnClickListener(myBtnListener);
        }
        buttons[currentFragmentIndex].setSelected(true);

       /* getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_main2,new MainFragment())
                .commit();
*/

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Toast.makeText(MainActivity.this, "我是小肥猪登录", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id==R.id.action_register){
            Toast.makeText(MainActivity.this, "我是小肥猪注册", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_index:
                clickNavigationIndex=0;
                break;
            case R.id.nav_city:
                clickNavigationIndex=1;
                break;
            case R.id.nav_spot:
                clickNavigationIndex=2;
                break;
            case R.id.nav_travel:
                clickNavigationIndex=3;
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                 break;
        }
       /* if (id == R.id.nav_index) {



            // Handle the camera action
            *//*Toast.makeText(MainActivity.this, "我是小肥猪首页", Toast.LENGTH_SHORT).show();*//*
        } else if (id == R.id.nav_city) {

        } else if (id == R.id.nav_spot) {

        } else if (id == R.id.nav_travel) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment hideFragment1 = navigations[currentNavigationIndex];
        transaction.hide(hideFragment1);
        Fragment hideFragment2 = fragments[currentFragmentIndex];
        transaction.hide(hideFragment2);

        Fragment showFragment = navigations[clickNavigationIndex];
        if(showFragment.isAdded()==false){
            transaction.add(R.id.fragment_container,showFragment);
        }
        transaction.show(showFragment);
        transaction.commit();
            /*buttons[currentFragmentIndex].setSelected(false);*/
        currentFragmentIndex=0;
        clickBtnIndex=0;
        currentNavigationIndex=clickNavigationIndex;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class MyBtnListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_main_fragment_home1:
                    clickBtnIndex=0;
                    break;
                case  R.id.btn_main_fragment_message1:
                    clickBtnIndex=1;
                    break;
                case R.id.btn_main_fragment_me1:
                    clickBtnIndex=2;
                    break;
            }
            if(currentFragmentIndex!=clickBtnIndex){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment hideFragment = fragments[currentFragmentIndex];
                transaction.hide(hideFragment);
                Fragment showFragment = fragments[clickBtnIndex];
                if(showFragment.isAdded()==false){
                    transaction.add(R.id.fragment_container,showFragment);
                }
                transaction.show(showFragment);
                transaction.commit();
                buttons[currentFragmentIndex].setSelected(false);
                buttons[clickBtnIndex].setSelected(true);
                currentFragmentIndex=clickBtnIndex;
            }

        }
    }

}
