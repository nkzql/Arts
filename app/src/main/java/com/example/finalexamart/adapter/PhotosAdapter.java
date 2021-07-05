package com.example.finalexamart.adapter;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.bean.Photos;
import com.example.finalexamart.utils.OnItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class PhotosAdapter extends RecyclerView.Adapter {
    private HashMap<String, Bitmap> mData=new HashMap<>();
    private List<Photos> mList=new ArrayList<>();
    private int selectPosition=-1;
    private OnItemClickListener mOnItemClickListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_photos,parent,false);
        PhotosHolder photosHolder=new PhotosHolder(view);
        return photosHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhotosHolder photosHolder= (PhotosHolder) holder;
        Photos photos=mList.get(position);
        photosHolder.image.setImageBitmap(mList.get(position).toBitmap());
        if(selectPosition==position&&mOnItemClickListener!=null){
            photosHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO 跳转到不同图片的内容
                    mOnItemClickListener.onItemClick(photos,position);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void setData(List<Photos> data){
        if(data!=null){
            mList.addAll(data);
        }
    }
    public void setSelectPosition(int position){
        this.selectPosition=position;
    }
    static class PhotosHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public PhotosHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.item_photos);
        }
    }
}
