package yw.lzp.com.openpoint;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by user on 2016/10/25.
 */
public class AppUtils {
    /**
     * get ip address
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && (inetAddress instanceof Inet4Address)) {
                        //Log.i("","getLocalIpAddress() _ local IP : "+ inetAddress.getHostAddress().toString());
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "";
    }
    /**
     * 生成机器码
     *
     * @return
     */
    public static String getLocalMac(Context context) {
        String mac="";
        if (context!=null)
            mac = getLocalMacAddressFromWifiInfo(context);
        if (mac == null || "".equals(mac))
            mac = getMacAddress();
        return mac;
    }
    //根据Wifi信息获取本地Mac
    public static String getLocalMacAddressFromWifiInfo(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }
    //本地以太网mac地址文件
    private static String getMacAddress()
    {
        String strMacAddr = "";
        byte[] b;
        try
        {
            NetworkInterface NIC = NetworkInterface.getByName("eth0");
            b = NIC.getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++)
            {
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
                if (i != 0 && i!=(b.length-1))
                {
                    buffer.append('-');
                }
            }
            strMacAddr = buffer.toString().toUpperCase();
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        return strMacAddr;
    }


    //打开端口
    public static String openPointCmd(String point){
        String p = point;
        if(p == null || "".equals(p)){
            p ="9999";
        }
        return
        "setprop service.adb.tcp.port "+p+"\n"+
                "stop adbd\n"+
                "start adbd";
    }
    public static void ToalsMessage(Context context,String message,int type){
        Toast.makeText(context,message,type);
    }




}
