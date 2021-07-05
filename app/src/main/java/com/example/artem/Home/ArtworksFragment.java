
package com.example.artem.Home;

import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.artem.Home.utility.Arts;
import com.example.artem.Home.utility.ArtworksAdapter;
import com.example.artem.Home.utility.ArtworksLoader;
import com.example.artem.R;
import com.example.artem.databinding.FragmentArtworksBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtworksFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Arts>> {
    ArrayList<Arts> arts;
    ArtworksAdapter artworksAdapter;
    boolean isScrolling=false;
    FragmentArtworksBinding binding;
    String url="https://api.artic.edu/api/v1/artworks?";
    float Px;
    private int page;
    private int loader_id=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentArtworksBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View mview, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(mview, savedInstanceState);
        page=generateRandomNumber(1,61);

        start_connection();
        arts=new ArrayList<>();
        artworksAdapter=new ArtworksAdapter(new ArrayList<>(),this,binding.artworksSpinKit);
        binding.artworksRecycleView.setAdapter(artworksAdapter);
        StaggeredGridLayoutManager gridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.artworksRecycleView.setLayoutManager(gridLayoutManager);
        addBottomOffset();
        binding.artworksRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                   isScrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItems=gridLayoutManager.getChildCount();
                int totalitems=gridLayoutManager.getItemCount();
                int scrolloutposition=gridLayoutManager.findFirstVisibleItemPositions(null)[0];

                if (isScrolling&&(currentItems+scrolloutposition==totalitems)){
                    isScrolling=false;
                    start_connection();

                }
            }
        });
    }

    public int generateRandomNumber(int low,int high){
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void start_connection(){
        binding.artworksSpinKit.setVisibility(View.VISIBLE);
        ConnectivityManager connectivityManager =this.getContext().getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            androidx.loader.app.LoaderManager.getInstance(this).initLoader(loader_id,null,this);
            loader_id++;
        } else {
            binding.artworksSpinKit.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "unable to load ", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public Loader<List<Arts>> onCreateLoader(int id, @Nullable Bundle args) {
        while (page>60){
            page=generateRandomNumber(1,61);
        }
        String query=appendCustomparameter(url,"100",String.valueOf(page),"id,title,,api_link,artist_title,timestamp,image_id");
        page++;
    return  new ArtworksLoader(this.getContext(),query);

    }

    public String  appendCustomparameter(String mUri,String limit,String page,String fields){
        StringBuilder s=new StringBuilder(mUri);
        s.append("limit"+"="+limit);
        s.append("&"+"page"+"="+page);
        s.append("&"+"fields"+"="+fields);
        return s.toString() ;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Arts>> loader, List<Arts> data) {
        if (data!=null && !data.isEmpty()){
            arts.addAll(data);
            artworksAdapter.UpdateData(arts);
            binding.artworksSpinKit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Arts>> loader) {
        arts.clear();
        artworksAdapter.UpdateData(arts);
    }

    public void addBottomOffset(){
        Px=getResources().getDimension(R.dimen.bottom_offset);
        ArtistFragment.BottomOffsetDecoration bottomOffsetDecoration=new ArtistFragment.BottomOffsetDecoration((int) Px);
        binding.artworksRecycleView.addItemDecoration(bottomOffsetDecoration);

    }
    static class BottomOffsetDecoration extends RecyclerView.ItemDecoration{
        private int mBottomOffset;
        public BottomOffsetDecoration(int bottomOffset) {
            mBottomOffset = bottomOffset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int dataSize = state.getItemCount();
            int position = parent.getChildAdapterPosition(view);
            if (dataSize > 0 && position == dataSize - 1&&position==dataSize-2) {
                outRect.set(0, 0, 0, mBottomOffset);
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }
}