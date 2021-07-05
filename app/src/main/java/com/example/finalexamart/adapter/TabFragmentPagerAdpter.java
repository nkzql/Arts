package com.example.finalexamart.adapter;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
public class TabFragmentPagerAdpter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;
    public TabFragmentPagerAdpter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,List<Fragment> list) {
        super(fragmentManager, lifecycle);
        this.fragmentList=list;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}


