package com.example.artem.SEARCH;

import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.artem.R;
import com.example.artem.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Arts>> {

    String murl="https://api.artic.edu/api/v1/artworks/search?";
    FragmentSearchBinding binding;
    String query;
    ArrayList<Arts> searchItems;
    SearchAdapter searchAdapter;
    float Px;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentSearchBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                query=binding.edtSearch.getText().toString();
                ConnectivityManager connectivityManager = getActivity().getSystemService(ConnectivityManager.class);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    androidx.loader.app.LoaderManager.getInstance(SearchFragment.this).initLoader(1,null,SearchFragment.this);
                } else {
                    Toast.makeText(getActivity(), "no internet connection", Toast.LENGTH_SHORT).show();
                }
                searchItems=new ArrayList<>();
                searchAdapter=new SearchAdapter(new ArrayList<>(),SearchFragment.this,1);
                binding.searchRecycle.setAdapter(searchAdapter);
                StaggeredGridLayoutManager gridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                binding.searchRecycle.setLayoutManager(gridLayoutManager);
                addBottomOffset();
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<Arts>> onCreateLoader(int id, @Nullable Bundle args) {
      String q=appendCustomparameter(murl,query,"100","1","id,title,,api_link,artist_title,timestamp,image_id,description");
      return new SearchLoader(getActivity(),q);
    }

    public String  appendCustomparameter(String mUri,String mquery,String limit,String page,String fields){
        StringBuilder s=new StringBuilder(mUri);
        s.append("q"+"="+mquery);
        s.append("&"+"fields"+"="+fields);
        s.append("&"+"limit"+"="+limit);
        s.append("&"+"page"+"="+page);
        return s.toString() ;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Arts>> loader, List<Arts> data) {
        if (data!=null && !data.isEmpty()){
            searchItems.addAll(data);
            searchAdapter.UpdateData(searchItems);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Arts>> loader) {
        searchItems.clear();
        searchAdapter.UpdateData(searchItems);
    }

    public void addBottomOffset(){
        Px=getResources().getDimension(R.dimen.bottom_offset);
        SearchFragment.BottomOffsetDecoration bottomOffsetDecoration=new SearchFragment.BottomOffsetDecoration((int) Px);
        binding.searchRecycle.addItemDecoration(bottomOffsetDecoration);
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
            if (dataSize > 0 && position == dataSize - 1) {
                outRect.set(0, 0, 0, mBottomOffset);
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }
}