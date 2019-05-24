package kkkj.android.esafety.menu.institution.view;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.PhoneDocInstitutionModelView;
import kkkj.android.esafety.menu.institution.contract.InstitutionContentContract;
import kkkj.android.esafety.menu.institution.model.GetdoCinsModel;
import kkkj.android.esafety.menu.institution.presenter.InstitutionContentPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

/**
 * 制度内容
 */
public class InstitutionContentActivity extends MvpBaseActivity<InstitutionContentPresenter> implements InstitutionContentContract.View {
    @BindView(R.id.mFrameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.ll_actionbar)
    LinearLayout ll_actionbar;
    String title;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    String insid = "";
    @Override
    protected int getLayout() {
        return R.layout.activity_institution_content;
    }

    @Override
    protected InstitutionContentPresenter getPresenter() {
        return new InstitutionContentPresenter();
    }

    @Override
    protected void initMonitorAndData() {

        insid = getIntent().getStringExtra("insid");
        String  name = getIntent().getStringExtra("name");
        if(!TextUtils.isEmpty(name))
        {
            action_bar_title.setText(name);
        }
        else {
            action_bar_title.setText("制度");
        }
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        webView.setLayerType(1, null);
        webView.setDrawingCacheEnabled(true);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
//        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
//        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
//        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        InsideWebChromeClient mInsideWebChromeClient = new InsideWebChromeClient();
//        InsideWebViewClient mInsideWebViewClient = new InsideWebViewClient();
//        webView.setWebViewClient(mInsideWebViewClient);
//        webView.setWebChromeClient(mInsideWebChromeClient);
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void getdocinsSuc(PhoneDocInstitutionModelView data) {
        String mimeType = "text/html";
        String enCoding = "utf-8";
        webView.loadDataWithBaseURL(null, data.getContent(), mimeType, enCoding, null);
    }

    public class InsideWebChromeClient extends WebChromeClient {
        private View mCustomView;
        private IX5WebChromeClient.CustomViewCallback mCustomViewCallback;

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
                progressbar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                progressbar.setProgress(newProgress);//设置进度值
            }
        }

        @Override
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            mFrameLayout.addView(mCustomView);
            mCustomViewCallback = callback;
            webView.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        public void onHideCustomView() {
            webView.setVisibility(View.VISIBLE);
            if (mCustomView == null) {
                return;
            }
            mCustomView.setVisibility(View.GONE);
            mFrameLayout.removeView(mCustomView);
            mCustomViewCallback.onCustomViewHidden();
            mCustomView = null;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            super.onHideCustomView();
        }
    }

    private class InsideWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //mWebView.loadUrl(javascript);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        switch (config.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                ll_actionbar.setVisibility(View.GONE);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                ll_actionbar.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
        GetdoCinsModel.Request request =new GetdoCinsModel.Request();
        request.setInsid(insid);
        mPresenter.getdocins(request);
    }


    @Override
    public void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
