package com.example.finalexamart.utils;

import android.graphics.Bitmap;
import android.util.LruCache;
public class ImageManageUtils {
    private static LruCache<String, Bitmap> mCache;
    private static ImageManageUtils mImageManageUtils;
    public static ImageManageUtils getInstance(){
        if(mImageManageUtils==null){
            mImageManageUtils=new ImageManageUtils();
        }
        return mImageManageUtils;
    }
    public void addImageToCache(String key,Bitmap bitmap){
        if(getImageFromCache(key)==null){
            mCache.put(key,bitmap);
        }
    }
    public Bitmap getImageFromCache(String key) {
        return mCache.get(key);
    }
}

