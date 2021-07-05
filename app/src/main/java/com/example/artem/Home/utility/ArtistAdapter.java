package com.example.artem.Home.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.artem.Home.ArtistContentActivity;
import com.example.artem.R;

import java.util.HashMap;

public class ArtistAdapter  extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    HashMap<String ,String > mdata;
    Context mcontext;
    Fragment mfragment;


    public ArtistAdapter(HashMap<String ,String > data, Context context, Fragment fragment) {
        this.mdata=data;
        this.mcontext=context;
        this.mfragment=fragment;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String  key = (String) mdata.keySet().toArray()[position];
        String  value= mdata.get(key);
        Glide.with(mcontext).load(value).diskCacheStrategy( DiskCacheStrategy.ALL ).
                apply(new RequestOptions().placeholder(R.drawable.grey)).into(holder.getImageView());

        Animation fadeIn= AnimationUtils.loadAnimation(mcontext,R.anim.fade_in);
        Animation fadeOut=AnimationUtils.loadAnimation(mcontext,R.anim.fade_out);
        holder.imageView.setClickable(true);
        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector=new GestureDetector(mcontext,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    holder.textView.setText(key);
                    holder.textView.setVisibility(View.VISIBLE);
                    holder.imageView.startAnimation(fadeOut);
                    holder.textView.startAnimation(fadeIn);
                    holder.imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        holder.textView.setAnimation(fadeOut);
                        holder.imageView.startAnimation(fadeIn);
                        holder.textView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.textView.setVisibility(View.INVISIBLE);
                            }
                        },900);
                        }
                    },900);
                    return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    Intent intent=new Intent(mfragment.getActivity(), ArtistContentActivity.class);
                    intent.putExtra("artist",key);


                    mfragment.getActivity().startActivityFromFragment(mfragment,intent,1);
                    Animatoo.animateZoom(mfragment.getActivity());
                    return true;
                }
            });
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               return gestureDetector.onTouchEvent(event);
            }
        });




    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.artist_image);
            textView=itemView.findViewById(R.id.martist_name);
        }

        public ImageView getImageView() {
            return imageView;
        }
        public TextView getTextView(){
            return textView;
        }

    }
}
