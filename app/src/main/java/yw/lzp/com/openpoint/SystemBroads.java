package yw.lzp.com.openpoint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2017/5/17.
 *
 */

public class SystemBroads extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("开机广播","李世平的工具应用 - "+intent.getAction());
    }
}
