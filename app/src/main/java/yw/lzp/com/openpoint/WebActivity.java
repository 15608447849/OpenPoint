package yw.lzp.com.openpoint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.R.attr.type;

public class WebActivity extends AppCompatActivity {



    private ViewGroup viewGroup;
    private TextView webInfo;
    private EditText editText;
    private WebView webView;
    private WebChromeClient chrom;
    private WebViewClient client;
    private Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        viewGroup = (ViewGroup) findViewById(R.id.web_framelayout);
        editText = (EditText) findViewById(R.id.web_edittext);
        webInfo = (TextView) findViewById(R.id.web_info);
        createWebView();
    }

    private void createWebView() {
        if (webView == null) {
            webView = new WebView(this);
            webView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
            viewGroup.addView(webView);
            //设置js交互
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.addJavascriptInterface(new JavaActionJsInterface(), "android");
            webView.loadUrl("file:///android_asset/index.html");
//            webView.loadUrl("http://172.16.0.51:8080?123");

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
