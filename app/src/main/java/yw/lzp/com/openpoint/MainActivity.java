package yw.lzp.com.openpoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.trinea.android.common.util.ShellUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";
    private EditText edit_point;
    private TextView ip;
    private TextView mac;
    private TextView info;
    private String point = null;
    //预留远程端口号
    public static final String [] commands = new String[]{
            "su\n",
            "setprop service.adb.tcp.port ",
            "stop adbd\n",
            "start adbd\n",
            "exit\n"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();

    }



    private void initView() {
          edit_point = (EditText) findViewById(R.id.edit_point);
          ip = (TextView) findViewById(R.id.ip);
          mac = (TextView) findViewById(R.id.mac);
          info = (TextView)findViewById(R.id.info);
    }

    private void loadData() {
        ip.setText(appUtils.getLocalIpAddress().equals("")?"127.0.0.1":appUtils.getLocalIpAddress());
        mac.setText(appUtils.getLocalMacAddressFromBusybox().equals("")?"127.0.0.1":appUtils.getLocalMacAddressFromBusybox());
    }

    /**
     * 打开端口
     */
    public void openPoint(View view){
        point = edit_point.getText().toString();
        Log.d(TAG,point);
        if (point!=null && !point.equals("")){

            try{

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openPointImp();
                    }
                }).start();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void openPointImp() {
        StringBuffer sb = new StringBuffer();
        String cmd[] = new String[commands.length];
        sb.append("准备执行...\n");
        for (int i=0;i<commands.length;i++){

            if (i == 1){
                cmd[i]= commands[i]+point+"\n";
            }else{
                cmd[i] = commands[i];
            }
            sb.append(cmd[i]);

        }
        Log.d(TAG,sb.toString());
        final String str = sb.toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                info.setText(str);

            }
        });
       ShellUtils.execCommand(cmd,true,true);
    }


}
