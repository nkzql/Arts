package com.example.finalexamart.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.activity.ContentActivity;
import com.example.finalexamart.adapter.StoryAdapter;
import com.example.finalexamart.asyncs.StoryCommandAsync;
import com.example.finalexamart.utils.ChooseClick;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
public class LearnFragment extends Fragment {
    private RecyclerView mRecycle;
    private StoryAdapter storyAdapter;
    private SmartRefreshLayout refreshLayout;
    private int page=1;
    public LearnFragment(){
    }
    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.learn,container,false);
        mRecycle=view.findViewById(R.id.learn_recycle);
        initRecycle();
        refreshLayout=view.findViewById(R.id.learn_smart);
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                page=1;
                new StoryCommandAsync(storyAdapter).execute(page);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
                page++;
                new StoryCommandAsync(storyAdapter).execute(page);
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public static LearnFragment newInstance() {
        LearnFragment fragment = new LearnFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private void initRecycle(){
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycle.setLayoutManager(manager);
        storyAdapter=new StoryAdapter();
        mRecycle.setAdapter(storyAdapter);
        new StoryCommandAsync(storyAdapter).execute(page);
        ChooseClick chooseClick=new ChooseClick() {
            @Override
            public void onChooseClick(String id, int position) {
                // 1表示是故事
                Intent intent=new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        };
        storyAdapter.setChooseClick(chooseClick);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
