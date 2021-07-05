package com.example.artem.SEARCH;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.artem.Home.ContentActivity;
import com.example.artem.Home.utility.Arts;
import com.example.artem.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    ArrayList<Arts> data;
    Fragment fragment;
    Context Context;
    int Id;
    public SearchAdapter(ArrayList<Arts> dataSet, Fragment mfragment,int id) {
    data=dataSet;
    fragment=mfragment;
    Id=id;
    }
    public SearchAdapter(ArrayList<Arts> dataSet, Context context,int id){
        data=dataSet;
        Context=context;
        Id=id;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView im=holder.Itemview.findViewById(R.id.search_image);
       Arts arts=data.get(position);
        String image_url="https://www.artic.edu/iiif/2/"+arts.getImageId()+"/full/843,/0/default.jpg";
        if (Id==1){
        Glide.with(fragment).load(image_url).diskCacheStrategy( DiskCacheStrategy.ALL ).apply(new RequestOptions().placeholder(R.drawable.grey)).into(im);
            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id=arts.getArt_id();
                    String title=arts.getTitle();
                    String Artist=arts.getArtist();
                    String time=arts.getTime_stamp();
                    Intent intent=new Intent(fragment.getActivity(), ContentActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("title",title);
                    intent.putExtra("artist",Artist);
                    intent.putExtra("time",time);
                    intent.putExtra("imageurl",image_url);


                    fragment.getActivity().startActivityFromFragment(fragment,intent,1);
                    Animatoo.animateZoom(fragment.getActivity());
                }
            });
        }
        if (Id==2){
            Glide.with(Context).load(image_url).diskCacheStrategy( DiskCacheStrategy.ALL ).apply(new RequestOptions().placeholder(R.drawable.grey)).into(im);
            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id=arts.getArt_id();
                    String title=arts.getTitle();
                    String Artist=arts.getArtist();
                    String time=arts.getTime_stamp();
                    Intent intent=new Intent(Context, ContentActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("title",title);
                    intent.putExtra("artist",Artist);
                    intent.putExtra("time",time);
                    intent.putExtra("imageurl",image_url);
                    Context.startActivity(intent);
                    Animatoo.animateZoom(Context);
                }
            });
        }


    }
    public void UpdateData(ArrayList<Arts> mdata){
        data.clear();
        data.addAll(mdata);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View Itemview;
        public ViewHolder(View itemview) {
            super(itemview);
            Itemview=itemview;
        }
    }
}
