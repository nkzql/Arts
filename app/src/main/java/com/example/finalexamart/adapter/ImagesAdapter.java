package com.example.finalexamart.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalexamart.R;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter{
    private List<String> dataList=new ArrayList<>();
    private Context context;
    private boolean isAll=false;
    public ImagesAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_xinan_double,parent,false);
        ImageHolder imageHolder=new ImageHolder(view);
        return imageHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageHolder imageHolder=(ImageHolder)holder;
        Log.e("size",dataList.get(position));
        Log.e("position", String.valueOf(position));
        Glide.with(context).load(dataList.get(position)).into(imageHolder.image);
    }

    public void setAll(boolean all) {
        isAll = all;
    }
    @Override
    public int getItemCount() {
        if(isAll)
            return dataList.size();
        else if(dataList.size()<4)
            return dataList.size();
        else
            return 3;
    }
    public void setData(List<String> data){
        if(data==null)
            return;
        this.dataList.clear();
        this.dataList.addAll(data);
        this.notifyDataSetChanged();
    }
    static class ImageHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.xinan_recycle_double_image);
        }
    }
}
