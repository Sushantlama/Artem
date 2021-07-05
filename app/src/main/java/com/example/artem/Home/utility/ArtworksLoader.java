package com.example.artem.Home.utility;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.MalformedURLException;
import java.util.List;

public class ArtworksLoader extends AsyncTaskLoader<List<Arts>> {
    String murl;
    public ArtworksLoader(@NonNull Context context, String url) {
        super(context);
        murl=url;
    }

    @Nullable
    @Override
    public List<Arts> loadInBackground() {
        List<Arts>  arts=null;
        if(murl==null){

            return null;
        }
        try {

            arts= QueryUtils.fetchArtworksData(murl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return arts;
    }

    @Override
    protected void onStartLoading()
    {

        forceLoad();
    }
}

