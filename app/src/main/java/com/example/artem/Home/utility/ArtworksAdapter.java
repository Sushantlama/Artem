package com.example.artem.Home.utility;

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
import com.example.artem.R;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;

public class ArtworksAdapter extends RecyclerView.Adapter<ArtworksAdapter.ViewHolder>{


    private final ArrayList<Arts> arts;
    private final
    Fragment fragment;
    SpinKitView mprogressbar;
    public ArtworksAdapter(ArrayList<Arts> dataSet, Fragment mfragment, SpinKitView progressbar){
        arts=dataSet;
        fragment=mfragment;
        mprogressbar=progressbar;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Arts art=arts.get(position);
        String image_url="https://www.artic.edu/iiif/2/"+art.getImageId()+"/full/843,/0/default.jpg";

        Glide.with(fragment).load(image_url).diskCacheStrategy( DiskCacheStrategy.ALL ).apply(new RequestOptions().placeholder(R.drawable.grey)).into(holder.getImageView());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arts mydata=arts.get(position);
                long id=mydata.getArt_id();
                String title=mydata.getTitle();
                String Artist=mydata.getArtist();
                String time=mydata.getTime_stamp();
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







    public void UpdateData(ArrayList<Arts> data){
       arts.clear();
       arts.addAll(data);
       notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        View Itemview;
        public ViewHolder(View itemview) {
            super(itemview);
            Itemview=itemview;
            imageView=itemview.findViewById(R.id.image_frame);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }


}
