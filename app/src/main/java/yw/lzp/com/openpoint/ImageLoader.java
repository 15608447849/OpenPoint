package yw.lzp.com.openpoint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by user on 2017/3/30.
 */

public class ImageLoader {
    /**
     * 获取一个 bitmap 成功返回turn
     */
    public static Bitmap getBitmap(String filePath, int minSideLength, int maxNumOfPixels, Bitmap.Config imageQuality) { //最小边长 最大像素
        FileInputStream is = null;
        Bitmap bitmap = null;
        File file = new File(filePath);
        final String TAG = "图片加载";
        try {
            if (file != null && file.exists()) {
                is = new FileInputStream(file);
                //设置压缩比例
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(is.getFD(),null, opts);

                    //1 图片宽高
                double w = opts.outWidth;
                double h = opts.outHeight;
                Log.e(TAG,"图片宽高 : "+w+" * "+h);
                int boundValue;
                //计算
                int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
                int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

                Log.e(TAG,"lowerBound : "+lowerBound);
                Log.e(TAG,"upperBound : "+upperBound);
                if (upperBound < lowerBound) {
                    // return the larger one when there is no overlapping zone.
                    boundValue = lowerBound;
                }else
                if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
                    boundValue = 1;
                } else if (minSideLength == -1) {
                    boundValue = lowerBound;
                } else {
                    boundValue = upperBound;
                }
                Log.e(TAG,"boundValue : "+boundValue);
                int roundedSize;
                if (boundValue <= 8) {
                    roundedSize = 1;
                    while (roundedSize < boundValue) {
                        roundedSize <<= 1;
                    }
                } else {
                    roundedSize = (boundValue + 7) / 8 * 8;
                }
                Log.e(TAG,"roundedSize : "+roundedSize);
                opts.inSampleSize = roundedSize;
                opts.inJustDecodeBounds = false;

                opts.inPreferredConfig = imageQuality;
                opts.inPurgeable = true;
                opts.inInputShareable = true;
                opts.inDither = false;
                opts.inTempStorage = new byte[12 * 1024];
                bitmap = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
