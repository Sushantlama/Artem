package com.example.artem.SEARCH;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.artem.Home.utility.Arts;

import java.net.MalformedURLException;
import java.util.List;

public class SearchLoader extends AsyncTaskLoader<List<Arts>> {
    String murl;
    public SearchLoader(@NonNull Context context,String url) {
        super(context);
        murl=url;
    }

    @Nullable
    @Override
    public List<Arts> loadInBackground() {
        List<Arts>  searchItems=null;
        if(murl==null){

            return null;
        }
        try {
            searchItems= SearchQueryUtils.fetchSearchData(murl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return searchItems;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
