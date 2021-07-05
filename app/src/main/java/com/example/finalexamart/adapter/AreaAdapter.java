package com.example.finalexamart.adapter;
import android.content.Context;
import android.content.Intent;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.finalexamart.R;
import com.example.finalexamart.activity.ImagesActivity;
import com.example.finalexamart.activity.XINanActivity;
import com.example.finalexamart.bean.AreaCulture;
import com.example.finalexamart.utils.ButtonClick;
import org.commonmark.node.Node;
import java.util.ArrayList;
import java.util.List;
import io.noties.markwon.Markwon;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.noties.markwon.linkify.LinkifyPlugin;
public class AreaAdapter extends RecyclerView.Adapter {
    private List<AreaCulture> dataList=new ArrayList<>();
    private Context mContext;
    private RecyclerView mRecycle;
    private ButtonClick buttonClick;
    private int selectPosition = -1;
    private Markwon markwon;
    public AreaAdapter(Context context) {
        this.mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_xinan, parent, false);
        AreaHolder holder = new AreaHolder(view);
        return holder;
    }
    private void setMarkwon(String content,TextView textView){
        markwon= Markwon.builder(mContext)
                .usePlugin(GlideImagesPlugin.create(mContext))
                .usePlugin(LinkifyPlugin.create())
                .build();
        Node node= markwon.parse(content);
        Spanned markdown = markwon.render(node);
        markwon.setParsedMarkdown(textView, markdown);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AreaHolder areaHolder = (AreaHolder) holder;
        AreaCulture item = dataList.get(position);
        areaHolder.title.setText(item.getTitle());
        Glide.with(mContext).load(item.getBackground()).into(areaHolder.background);
        setMarkwon(item.getContent(),areaHolder.content);
        if(areaHolder.mImagesAdapter==null){
            areaHolder.mImagesAdapter = new ImagesAdapter(mContext);
            areaHolder.mImagesAdapter.setData(item.getPhotos());
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            areaHolder.recyclerView.setLayoutManager(layoutManager);
            areaHolder.recyclerView.setAdapter(areaHolder.mImagesAdapter);
        }
//        if (areaHolder.mImagesAdapter == null) {
//            areaHolder.mImagesAdapter = new ImagesAdapter(mContext,item.getHotPhotos());
//            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            areaHolder.recyclerView.setLayoutManager(layoutManager);
//            areaHolder.recyclerView.setAdapter(areaHolder.mImagesAdapter);
//        } else {
//            areaHolder.mImagesAdapter.setData(item.getHotPhotos());
//        }
        areaHolder.allWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClick != null) {
                    Log.e("onclick",item.getId());
                    buttonClick.onButtonClick(item.getId(), position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
        //return 1;
    }
    public void setButtonClick(ButtonClick buttonClick) {
        this.buttonClick = buttonClick;
    }
    public void addData(AreaCulture data) {
        if (data == null)
            return;
        this.dataList.clear();
        this.dataList.add(data);
        this.notifyDataSetChanged();
    }
    class AreaHolder extends RecyclerView.ViewHolder{
        private ImageView background;
        private TextView title,content,allWork;
        private View view;
        private RecyclerView recyclerView;
        private ImagesAdapter mImagesAdapter;
        private List<String> imageList=new ArrayList<>();
        public AreaHolder(@NonNull View itemView) {
            super(itemView);
            background=itemView.findViewById(R.id.xinan_background);
            title=itemView.findViewById(R.id.xinan_title);
            content=itemView.findViewById(R.id.xinan_content);
            recyclerView=itemView.findViewById(R.id.xinan_recycle_double);
            allWork=itemView.findViewById(R.id.xinan_all_work);
            view=itemView.findViewById(R.id.xinan_recycle_seperate);
        }
    }
}
