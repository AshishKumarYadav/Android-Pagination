package com.ashish.dunzo.adapter;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.dunzo.R;
import com.ashish.dunzo.model.Photo;
import com.ashish.dunzo.model.Photos;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class ItemAdapter extends PagedListAdapter<Photo, ItemAdapter.ItemViewHolder> {

    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Photo item = getItem(position);
        if (item != null) {
            holder.textView.setText(item.getTitle());
            Glide.with(mCtx)
                    .load(item.constructURL())
                    .centerCrop()
                    .into(holder.imageView);
            holder.imageView.setAdjustViewBounds(true);


        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Photo>() {

                @Override
                public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
