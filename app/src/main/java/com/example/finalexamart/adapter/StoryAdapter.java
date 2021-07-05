package com.example.finalexamart.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.bean.Story;
import com.example.finalexamart.utils.ChooseClick;
import java.util.ArrayList;
import java.util.List;
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{
    private List<Story> dataList=new ArrayList<>();
    private int selectPosition=-1;
    private ChooseClick chooseClick;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_learn,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story s=dataList.get(position);
        holder.command.setText(s.getTitle());
        holder.command.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseClick!=null){
                    chooseClick.onChooseClick(s.getId(),position);
                }
            }
        });
    }
    public void setChooseClick(ChooseClick chooseClick) {
        this.chooseClick = chooseClick;
    }

    public void initDataList(List<Story> dataList) {
        if(dataList==null)
            return;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
    }
    public void addDataList(List<Story> dataList){
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
        private TextView command;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            command=itemView.findViewById(R.id.learn_recycle_command);
        }
    }
}
