package com.example.artem.Home;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {

   int Total_tabs;

    public MyAdapter(@NonNull FragmentActivity fragmentActivity, int total_tabs) {
        super(fragmentActivity);
        Total_tabs=total_tabs;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                ArtworksFragment artworksFragment = new ArtworksFragment();
                return artworksFragment;
            case 1:
                ArtistFragment artistFragment = new ArtistFragment();
                return artistFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return Total_tabs;
    }
}
