package com.example.artem.Home;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.artem.Home.utility.Arts;
import com.example.artem.R;
import com.example.artem.SEARCH.SearchAdapter;
import com.example.artem.SEARCH.SearchLoader;

import java.util.ArrayList;
import java.util.List;

public class ArtistContentActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Arts>> {

    RecyclerView recyclerView;
    String murl="https://api.artic.edu/api/v1/artworks/search?";
    String query;
    ArrayList<Arts> searchItems;
    SearchAdapter searchAdapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_content);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.d_1));
        }
        Intent i=getIntent();
        query= i.getStringExtra("artist");
        recyclerView=findViewById(R.id.search_recycle_artist);
        startconnection();




    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startconnection(){
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            androidx.loader.app.LoaderManager.getInstance(this).initLoader(1,null,this);
        } else {
            Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
        }
        searchItems=new ArrayList<>();
        searchAdapter=new SearchAdapter(new ArrayList<>(),this,2);
        recyclerView.setAdapter(searchAdapter);
        StaggeredGridLayoutManager gridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
//        addBottomOffset();
    }

    @NonNull
    @Override
    public Loader<List<Arts>> onCreateLoader(int id, @Nullable Bundle args) {
        String q=appendCustomparameter(murl,query,"100","1","id,title,,api_link,artist_title,timestamp,image_id,description");
        return new SearchLoader(this,q);
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
}