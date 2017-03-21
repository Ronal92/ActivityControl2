package com.jinwoo.android.activitycontrol2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BrowserActivity extends AppCompatActivity implements View.OnClickListener{

    // 뒤로가기와 url로 이동하기
    Button btnBack, btnGo;
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        // 1. 사용할 위젯을 가져온다.
        btnBack = (Button) findViewById(R.id.btnBack);
        btnGo = (Button) findViewById(R.id.btnGo);
        webView = (WebView) findViewById(R.id.webView);
        editText = (EditText) findViewById(R.id.editText);

        // 2. 리스너 달기
        btnBack.setOnClickListener(this);
        btnGo.setOnClickListener(this);

        // 캐시없이 사용하기 -- 느림......
        //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // script 사용 설정(필수)
        webView.getSettings().setJavaScriptEnabled(true);
        // 줌 사용설정
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        // 3. 웹뷰 클라이언트를 지정
        webView.setWebViewClient(new WebViewClient());
        // 3.1 둘다 세팅할 것 : 프로토콜에 따라 클라이언트가 설정되는것으로 파악됨....
        webView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnBack:
                if(webView.canGoBack()){
                    webView.goBack();
                } else{
                    Toast.makeText(this,"Don't go back",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGo:
                String url = editText.getText().toString();
                webView.loadUrl(url);
                break;

        }
    }
}
