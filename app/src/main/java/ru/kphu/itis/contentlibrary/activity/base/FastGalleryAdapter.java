package ru.kphu.itis.contentlibrary.activity.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;
import ru.kphu.itis.contentlibrary.widgets.SquareImageView;

public class FastGalleryAdapter extends RecyclerView.Adapter<FastGalleryAdapter.FastGalleryViewHolder> {

    private List<GalleryItem> galleryItems;

    public interface OnItemClickListener{
        void onItemClick(@NonNull String filePath);
    }

    private OnItemClickListener itemClickListener;

    public FastGalleryAdapter() {
        this.galleryItems = new ArrayList<>();
    }

    public class FastGalleryViewHolder extends RecyclerView.ViewHolder{

        private SquareImageView image;

        public FastGalleryViewHolder(View itemView) {
            super(itemView);

            image = (SquareImageView) itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(v -> {
                if(itemClickListener!=null){
                    itemClickListener.onItemClick(galleryItems.get(getAdapterPosition()).getFilePath());
                }
            });
        }
    }

    @Override
    public FastGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fast_gallery, parent, false);
        return new FastGalleryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FastGalleryViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(galleryItems.get(position).getFilePath())
                .dontAnimate()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return galleryItems.size();
    }

    public void setData(@NonNull List<GalleryItem> galleryItems){
        this.galleryItems = galleryItems;
        notifyDataSetChanged();
    }

    public void setItemClickListener(@NonNull OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
