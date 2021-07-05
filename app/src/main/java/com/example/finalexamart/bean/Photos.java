package com.example.finalexamart.bean;

import android.graphics.Bitmap;

public class Photos {
    private String path;
    private  Bitmap bitmap;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    // TODO 从网上加载图片
    public  Bitmap toBitmap(){
        return bitmap;
    }
}
