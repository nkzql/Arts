package com.example.finalexamart.adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.bean.Constans;
import com.example.finalexamart.utils.ButtonClick;
import java.util.ArrayList;
import java.util.List;
public class GridImageAdapter extends RecyclerView.Adapter {
    private List<String> dataList=new ArrayList<>();
    private int selectPosition=-1;
    private ButtonClick buttonClick;
    public GridImageAdapter(List<String> data,ButtonClick buttonClick){
        this.dataList=data;
        this.buttonClick=buttonClick;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_home_images,parent,false);
        GridImageHolder gridImageHolder=new GridImageHolder(view);
        return gridImageHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GridImageHolder gridImageHolder= (GridImageHolder) holder;
        gridImageHolder.textView.setText(dataList.get(position));
        gridImageHolder.image.setImageResource(Constans.getTheme_img_src().get(dataList.get(position)));
        gridImageHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonClick!=null){
                    buttonClick.onButtonClick(dataList.get(position),position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void setSelectPosition(int position){
        this.selectPosition=position;
    }
    static class GridImageHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView textView;
        public GridImageHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.grid_home_image);
            textView=itemView.findViewById(R.id.grid_home_text);
        }
    }
}
