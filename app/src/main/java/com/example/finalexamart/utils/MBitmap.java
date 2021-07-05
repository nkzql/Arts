package com.example.finalexamart.utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MBitmap {
    public static Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baops);
        return baops.toByteArray();
    }
//    public static Bitmap getHttpBitmap(String url) {
//        final Bitmap[] bitmap = {null};
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL uri=new URL(url);
//                    bitmap[0] = BitmapFactory.decodeStream(uri.openStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        return bitmap[0];
//    }
}
