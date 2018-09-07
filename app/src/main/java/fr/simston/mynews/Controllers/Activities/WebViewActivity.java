package fr.simston.mynews.Controllers.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 20/06/2018.
 *
 * @version 1.0
 */
public class WebViewActivity extends AppCompatActivity {

    public static String EXTRA_URL = "url";

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressBarWebViewActivity)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ButterKnife.bind(this);

        String url = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_URL);
        MyWebViewClient myWebViewClient = new MyWebViewClient();
        myWebViewClient.shouldOverrideUrlLoading(mWebView, url);

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                mProgressBar.setProgress(progress);
                if (progress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.mWebView.destroy();
    }


    private class MyWebViewClient extends WebViewClient {
        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            WebSettings webSettings = mWebView.getSettings();
            // Access to dom to avoid the bug, in the webview activity
            webSettings.setDomStorageEnabled(true);
            // Enable Javascript
            webSettings.setJavaScriptEnabled(true);

            // Force links and redirects to open in the WebView instead of in a browser
            mWebView.setWebViewClient(new WebViewClient());
            return true;
        }
    }


}