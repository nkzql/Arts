package com.example.finalexamart.adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalexamart.R;
import com.example.finalexamart.activity.ContentActivity;
import com.example.finalexamart.bean.Commands;
import com.example.finalexamart.utils.ChooseClick;
import com.example.finalexamart.utils.MBitmap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.ViewHolder>{
    private List<Commands> dataList=new ArrayList<>();
    private ChooseClick chooseClick;
    private Context context;
    public CommandAdapter(ChooseClick chooseClick,Context context){
        this.context=context;
        //this.dataList=dataList;
        this.chooseClick=chooseClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_home_command,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Commands commands=dataList.get(position);
        Glide.with(context).load(commands.getBitmap()).into(holder.image);
        holder.content.setText(commands.getDescription());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseClick!=null){
                    Log.e("hahah","choose");
                    chooseClick.onChooseClick(commands.getId(),position);
                }
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseClick!=null){
                    chooseClick.onChooseClick(commands.getId(),position);
                }
            }
        });
    }

    public void initDataList(List<Commands> dataList) {
        if(dataList==null)
            return;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
    }
    public void addDataList(List<Commands> dataList){
        if(dataList==null)
            return;
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.home_command_image);
            content=itemView.findViewById(R.id.home_command_content);
        }
    }
}
