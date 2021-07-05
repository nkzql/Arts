package com.example.finalexamart.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.adapter.CardAdapter;
import com.example.finalexamart.adapter.CommandAdapter;
import com.example.finalexamart.asyncs.CommandAsync;
import com.example.finalexamart.bean.Commands;
import com.example.finalexamart.utils.ChooseClick;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
public class CardActivity extends AppCompatActivity implements ChooseClick {
    private RecyclerView recyclerView;
    private CommandAdapter mAdapter;
    private SmartRefreshLayout refreshLayout;
    private ProgressBar bar;
    private TextView textView;
    private int page=1;
    private int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        bar=findViewById(R.id.card_progressbar);
        type=getIntent().getIntExtra("type",0);
        Log.e("type", String.valueOf(type));
        textView=findViewById(R.id.card_title);
        init(type);
        recyclerView=findViewById(R.id.card_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new CommandAdapter(this::onChooseClick,this);
        recyclerView.setAdapter(mAdapter);
        refreshLayout=findViewById(R.id.card_smart);
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        refreshLayout.setRefreshFooter(new BallPulseFooter(this));
        new CommandAsync(mAdapter,bar).execute(page,1,type);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                page=1;
                new CommandAsync(mAdapter,bar).execute(page,1,type);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
                page++;
                new CommandAsync(mAdapter,bar).execute(page,1,type);
            }
        });
    }
    private void init(int type){
        switch (type){
            case 0:
                textView.setText("中华民族");
                break;
            case 1:
                textView.setText("其它民俗");
                break;
            case 2:
                textView.setText("东南中南");
                break;
            case 3:
                textView.setText("东北");
                break;
            case 4:
                textView.setText("西南");
                break;
            case 5:
                textView.setText("西北");
                break;
        }
    }
    @Override
    public void onChooseClick(String id, int position) {
        Intent intent=new Intent(CardActivity.this,XINanActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
