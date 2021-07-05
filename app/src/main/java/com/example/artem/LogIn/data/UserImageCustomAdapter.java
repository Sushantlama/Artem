package com.example.artem.LogIn.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artem.R;

import java.util.ArrayList;


public class UserImageCustomAdapter extends RecyclerView.Adapter<UserImageCustomAdapter.MyviewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<Integer> items;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public UserImageCustomAdapter(Context context,ArrayList<Integer> items,OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.items=items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.images_item, parent, false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        ImageView view;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.images);
        }

        public ImageView getView() {
            return view;
        }
        public void bind(final int id, final OnItemClickListener listener) {
            view.setImageResource(getdrawable(id));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(getdrawable(id));
                }
            });
            
        }
        public int getdrawable(int position){
            switch (position){
                case 0:return R.drawable.avatar1;
                case 1:return R.drawable.avatar2;
                case 2:return R.drawable.avatar3;
                case 3:return R.drawable.avatar4;
                case 4:return R.drawable.avatar5;
                case 5:return R.drawable.avatar6;
                case 6:return R.drawable.avatar7;
                case 7:return R.drawable.avatar8;
                case 8:return R.drawable.avatar9;
                case 9:return R.drawable.avatar10;
                case 10:return R.drawable.avatar11;
                case 11:return R.drawable.avatar12;
                default:return R.drawable.avatar1;
            }

        }
        
    }
}
