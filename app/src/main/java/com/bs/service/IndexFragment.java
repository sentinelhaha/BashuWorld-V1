package com.bs.service;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bs.bashuworld.R;
import com.bs.common.BashuApplication;

/**
 * Created by admin on 2019/3/4.
 */

public class IndexFragment extends Fragment{
    private WebView webView;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        WebView webView = (WebView) view.findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT){
            WebView.setWebContentsDebuggingEnabled(true);
        }
        String url = BashuApplication.serverIP+"/CROWN-BS-SYS-V1/doIndexUI.do";
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return view;
    }
}
