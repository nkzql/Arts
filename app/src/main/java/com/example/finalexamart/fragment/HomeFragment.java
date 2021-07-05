package com.example.finalexamart.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.activity.CardActivity;
import com.example.finalexamart.activity.ContentActivity;
import com.example.finalexamart.adapter.CommandAdapter;
import com.example.finalexamart.adapter.GridImageAdapter;
import com.example.finalexamart.asyncs.CommandAsync;
import com.example.finalexamart.bean.Commands;
import com.example.finalexamart.utils.ButtonClick;
import com.example.finalexamart.utils.ChooseClick;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.finalexamart.activity.MainActivity.BASE_URL;
public class HomeFragment extends Fragment implements ButtonClick, ChooseClick {
    private RecyclerView recyclerView1,recyclerView2;
    private GridImageAdapter gridImageAdapter;
    private CommandAdapter commandAdapter;
    private SmartRefreshLayout refreshLayout;
    private int page=1;
    private ProgressBar bar;
    public HomeFragment(){}
    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.home,container,false);
        refreshLayout=view.findViewById(R.id.home_smart);
        bar=view.findViewById(R.id.home_progressbar);
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                page=1;
                new CommandAsync(commandAdapter,bar).execute(page,0,-1);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
                page++;
                new CommandAsync(commandAdapter,bar).execute(page,0,-1);
            }
        });
        recyclerView1=view.findViewById(R.id.home_recycle1);
        recyclerView2=view.findViewById(R.id.home_recycle2);
        initRecycle();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void initRecycle(){
        GridLayoutManager manager1=new GridLayoutManager(getActivity(),3);
        recyclerView1.setLayoutManager(manager1);
        List<String> list1=new ArrayList<>();
        list1.add("中华民族");
        list1.add("其它民俗");
        list1.add("东南中南");
        list1.add("东北");
        list1.add("西南");
        list1.add("西北");
        gridImageAdapter=new GridImageAdapter(list1,this::onButtonClick);
        recyclerView1.setAdapter(gridImageAdapter);
        LinearLayoutManager manager2=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView2.setLayoutManager(manager2);
        commandAdapter=new CommandAdapter(this::onChooseClick,getActivity().getApplicationContext());
        recyclerView2.setAdapter(commandAdapter);
        new CommandAsync(commandAdapter,bar).execute(page,0,-1);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onButtonClick(String message, int position) {
        Intent intent=new Intent(getActivity(), CardActivity.class);
        switch (message){
            case "中华民族":
                intent.putExtra("type",0);
                startActivity(intent);
                break;
            case "其它民俗":
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case "东南中南":
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case "东北":
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case "西南":
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            case "西北":
                intent.putExtra("type",5);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onChooseClick(String id, int position) {
        //TODO 通过id获取数据并跳转页面
        Intent intent=new Intent(getActivity(), ContentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("type",0);
        startActivity(intent);
    }
    private void getFromData(){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(BASE_URL+"/getAreaCulture?type=0&page=1")
                .get()
                .build();
        Response response=null;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Log.e("getfromdata",responseData);
            }
        });
    }
}
