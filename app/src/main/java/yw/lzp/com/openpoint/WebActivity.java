package yw.lzp.com.openpoint;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebActivity extends AppCompatActivity {



    private ViewGroup viewGroup;
    private TextView webInfo;
    private EditText editText;
    private IWebview webView;
    private WebChromeClient chrom;
    private WebViewClient client;
    private Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN,WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web);
        viewGroup = (ViewGroup) findViewById(R.id.web_framelayout);
        editText = (EditText) findViewById(R.id.web_edittext);
        webInfo = (TextView) findViewById(R.id.web_info);
        createWebView();
    }

    private void createWebView() {
        if (webView == null) {
            webView = new IWebview(this);
//            webView.enablecrossdomain();
//            webView.enablecrossdomain41();
            webView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
            viewGroup.addView(webView);
            //设置js交互

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);//js
            webSettings.setAllowFileAccess(true); //设置可以访问文件
            webSettings.setAllowFileAccessFromFileURLs(true);// js读取本地文件内容

            webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                webSettings.setAllowUniversalAccessFromFileURLs(true);//关于跨域
            }else{
                try {
                    Class<?> clazz = webView.getSettings().getClass();
                    Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);
                    if (method != null) {
                        method.invoke(webView.getSettings(), true);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级

            webSettings.setDefaultTextEncodingName("UTF-8");//设置编码格式
            webSettings.setSupportZoom(false); //支持缩放，默认为true
            webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件

            //设置自适应屏幕，两者合用
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

            webSettings.setAppCacheEnabled(true); //启用应用缓存
            webSettings.setDomStorageEnabled(true); //启用或禁用DOM缓存。
            webSettings.setDatabaseEnabled(true); //启用或禁用DOM缓存。
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
            webSettings.supportMultipleWindows();  //多窗口
            webSettings.setPluginState(WebSettings.PluginState.ON);//flash 有关系
            //webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());

            webView.addJavascriptInterface(new JavaActionJsInterface(), "android");
            webView.loadUrl("file:///android_asset/index.html");


        }
    }
    //按钮1
    public void button1click(View view){
        // 无参数调用webView.loadUrl("javascript:sendMessageToHtml()");
        // 传递参数调用
        String param = editText.getText().toString();
        if (param == null || param==""){
            webView.loadUrl("javascript:callToHtml()");
        }else{
            webView.loadUrl("javascript:sendMessageToHtml('" + param + "')");
        }
    }
    //js - java interface
    private final class JavaActionJsInterface{
        //JavaScript调用
        @android.webkit.JavascriptInterface
        public void sendMessageToActivity(final String message) {
            if (message.equals("openactivity")){
             openActivitys();

            }else if (webInfo!=null){
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       webInfo.setText(webInfo.getText().toString()+message);
                   }
               });
            }
        }
    }

    private void openActivitys() {
        startActivityForResult(new Intent(this,WebOtherActivity.class),200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 200:
                if(resultCode == RESULT_OK) {
                    webView.loadUrl("javascript:sendMessageToHtml(" + "'js打开一个android页面执行任务完成,回传信息!'" + ")");
                }
                break;
            default:
        }
    }

}
