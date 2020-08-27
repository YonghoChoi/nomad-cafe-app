package com.yonghochoi.nomadcafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import static com.yonghochoi.nomadcafeapp.R.id.webView;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appSecret = System.getenv("APP_CENTER_SECRET");

        AppCenter.start(getApplication(), appSecret,
                Analytics.class, Crashes.class);

        // 웹뷰 셋팅
        mWebView = (WebView) findViewById(webView);             //xml 자바코드 연결
        mWebView.getSettings().setJavaScriptEnabled(true);      //자바스크립트 허용
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);  // 로컬 저장소 허용
        mWebView.getSettings().setGeolocationEnabled(true); // GPS 허용
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);  // 자바스크립트 새창 띄우기 허용 여부
        mWebView.getSettings().setUseWideViewPort(true);    // 화면 사이즈 맞추기 허용
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용안함
        mWebView.getSettings().setSupportMultipleWindows(true); // 새창 띄우기 여부
        mWebView.getSettings().setBuiltInZoomControls(true);    // 화면 확대/축소 허용
        mWebView.getSettings().setSupportZoom(true);    // 줌 허용
        mWebView.getSettings().setLoadWithOverviewMode(true);   // 메타 태그 허용
        mWebView.setWebChromeClient(new GeoWebChromeClient());

        mWebView.loadUrl("https://workingspace.netlify.app/");  //웹뷰 실행
//        mWebView.setWebChromeClient(new WebChromeClient());     //웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
//        mWebView.setWebViewClient(new WebViewClientClass());    //새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히 하기위해 사용

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {                     //뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {       //웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {        //페이지 이동

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;
        }
    }
}

