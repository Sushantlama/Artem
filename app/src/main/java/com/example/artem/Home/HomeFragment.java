package com.example.artem.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.artem.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    View mview;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview= inflater.inflate(R.layout.fragment_home, container, false);
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tabLayout=mview.findViewById(R.id.my_tab_layout);
        viewPager=mview.findViewById(R.id.my_viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab_content(position,tab);
                    }
                }).attach();
    }

    private MyAdapter createCardAdapter() {
        MyAdapter adapter = new MyAdapter(getActivity(),2);
        return adapter;
    }
    public void tab_content(int Position,TabLayout.Tab tabLayout){
        switch (Position){
            case 0: tabLayout.setText(R.string.tab_artworks);break;
            case 1:tabLayout.setText(R.string.tab_artist);break;
        }
    }
}