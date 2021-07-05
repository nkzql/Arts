package com.example.finalexamart.bean;


import android.net.Uri;

public class Commands {
    private String id;
    private String description;
    private String bitmap;
    private String article;
    public Commands(String id,String description,String bitmap){
        this.id=id;
        this.description=description;
        this.bitmap=bitmap;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String content) {
        this.description = content;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
