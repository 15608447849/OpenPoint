package yw.lzp.com.openpoint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.trinea.android.common.util.ShellUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";
    private EditText edit_point;
    private TextView ip;
    private TextView mac;
    private TextView time;
    private TextView info;
    private View image_layer;
    private ImageView image_show;
    private Handler handler;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    //添加信息到日志UI显示
    private void addMessageToText(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                info.setText(info.getText().toString()+"\n"+message);
            }
        });
    }

    private void initView() {
        edit_point = (EditText) findViewById(R.id.edit_point);
        ip = (TextView) findViewById(R.id.ip);
        mac = (TextView) findViewById(R.id.mac);
        time = (TextView) findViewById(R.id.time);
        info = (TextView)findViewById(R.id.info);
        image_layer = findViewById(R.id.image_layer);
        image_show = (ImageView) findViewById(R.id.show_image);
        Typeface fontType = Typeface.create(Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf"), Typeface.BOLD);
        time.setTypeface(fontType);
        handler = new Handler();
        final Runnable RUN_TIME = new Runnable() {
            @Override
            public void run() {
                loadData();
                handler.postDelayed(this,1);
            }
        };
        handler.post(RUN_TIME);

        addMessageToText("开发板版本:"+android.os.Build.MODEL+"\nandroid sdk:"+android.os.Build.VERSION.SDK+"\n版本号:"+android.os.Build.VERSION.RELEASE);
    }

    private void loadData() {

            ip.setText("IP Address: ["+ (AppUtils.getLocalIpAddress().equals("")?"127.0.0.1": AppUtils.getLocalIpAddress())+"]");
            mac.setText("MAC : ["+(AppUtils.getLocalMac(this).equals("")?"00-00-00-00-00": AppUtils.getLocalMac(this))+"]");
            time.setText(sdf.format(new Date()));
    }

    /**
     * 打开端口
     */
    public void openPoint(View view){
        final String pointStr = edit_point.getText().toString();
        Log.e(TAG,"点击打开端口 - "+pointStr);
        BackRunner.runBackground(new Runnable() {
            @Override
            public void run() {
                if (!ShellUtils.checkRootPermission()){
                    addMessageToText("没有root权限,无法执行命令");
                    return;
                }
                final String command = AppUtils.openPointCmd(pointStr);
                addMessageToText("准备执行端口打开:\n"+command);
                ShellUtils.CommandResult result = ShellUtils.execCommand(command,true,true);
                if (result.result == 0){
                    addMessageToText("执行成功");
                }else{
                    addMessageToText("执行失败,结果值:"+result.result);
                }
            }
        });
    }

    /**
     * 截屏
     */
    public void screenCap(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                takeScreenShot();
            }
        }).start();
        BackRunner.runBackground(new Runnable() {
            @Override
            public void run() {
                takeScreenShot();
            }
        });
    }

    public void takeScreenShot(){
        try {
            String mSavedPath = Environment.getExternalStorageDirectory()+ File. separator + Calendar.getInstance().getTime().getTime()+".png" ;
            addMessageToText("截屏图片保存路径:"+mSavedPath);
            ShellUtils.CommandResult result = ShellUtils.execCommand("screencap -p "+mSavedPath,true,true);
            if (result.result == 0){
               //显示一个bitmap
                showImage(mSavedPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showImage(String mSavedPath) {
       final Bitmap bitmap = ImageLoader.getBitmap(mSavedPath,-1,1080*1920, Bitmap.Config.ARGB_8888);
        if (bitmap!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (image_layer.getVisibility() == View.GONE){
                        image_layer.setVisibility(View.VISIBLE);
                    }
                    image_show.setImageBitmap(bitmap);
                }
            });
        }
    }

    //关闭截图显示层
    public void closeImage(View view){
        if (image_layer.getVisibility() == View.VISIBLE){
            image_layer.setVisibility(View.GONE);
        }
    }
    //设置定时开关机
    public void settingTimeTel(View view){

        String param = edit_point.getText().toString();
            //切割字符串
        String strarr[]  = param.split("\\s+");
        if (strarr.length==0){
            addMessageToText("非法参数");
            return;
        }
        if (!strarr[0].equals("time")){
            addMessageToText("无效的参数设置");
            return;
        }
        //设定下次开关机时间 :  time a 2017.03.30.20.30 2017.03.31.07.30
        //设置每天的开关机时间 :  time b 20.30 07.30
        //以周为周期设置开关机时间 : time c 1.3.5.7 20.30 8.35

        if (strarr[1].equals("a")){

            String arr[] = strarr[2].split("\\.");
            int[] poweron = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                poweron[i] = Integer.parseInt(arr[i]);
            }
            arr = strarr[3].split("\\.");
            int[] poweroff = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                poweroff[i] = Integer.parseInt(arr[i]);
            }

            Intent intent = new Intent("android.q-zheng.action.POWERONOFF");
                intent.putExtra("timeon", poweron);
                intent.putExtra("timeoff", poweroff);
                intent.putExtra("enable",true); //使能开关机功能，设为 false,则为关闭,缺省为 true
                sendBroadcast(intent);
        }
        if (strarr[1].equals("b")){

            String arr[] = strarr[2].split("\\.");
            int[] poweron = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                poweron[i] = Integer.parseInt(arr[i]);
            }
            arr = strarr[3].split("\\.");
            int[] poweroff = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                poweroff[i] = Integer.parseInt(arr[i]);
            }
            addMessageToText("开机时间:"+ poweron[0]+":"+poweron[1]);
            addMessageToText("关机时间:"+ poweroff[0]+":"+poweroff[1]);

            Intent intent = new Intent("android.q-zheng.action.PWR_DAILY");//"
            intent.putExtra("timeon", poweron);
            intent.putExtra("timeoff", poweroff);
            intent.putExtra("enable",true); //使能开关机功能，设为 false,则为关闭,缺省为 true
            sendBroadcast(intent);

        }
        if (strarr[1].equals("c")){
            String arr[] = strarr[2].split("\\.");
            int[] weekdays = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                weekdays[i] = Integer.parseInt(arr[i]);
            }
            arr = strarr[3].split("\\.");
            int[] poweron = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                poweron[i] = Integer.parseInt(arr[i]);
            }

            arr = strarr[4].split("\\.");
            int[] poweroff = new int[arr.length];
            for (int i = 0;i<arr.length;i++){
                poweroff[i] = Integer.parseInt(arr[i]);
            }

            Intent intent = new Intent("android.q-zheng.action.PWR_WEEKDAYS");
            intent.putExtra("weekdays", weekdays);
            intent.putExtra("timeon", poweron);
            intent.putExtra("timeoff", poweroff);
            intent.putExtra("enable",true); //使能开关机功能，设为 false,则为关闭,缺省为 true
            sendBroadcast(intent);
        }
        addMessageToText("设置定时开关机完毕");
    }

    public void gotoWebView(View view){
        startActivity(new Intent(this,WebActivity.class));
    }

}
