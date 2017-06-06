package ru.kphu.itis.contentlibrary.activity.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;

public class ContentSourcesAdapter extends RecyclerView.Adapter<ContentSourcesAdapter.ContentSourcesViewHolder> {

    private List<ContentSource> contentSources;

    public interface OnItemClickListener{
        void onItemClick(@NonNull ContentSourceType sourceType);
    }

    private OnItemClickListener itemClickListener;

    public ContentSourcesAdapter(@NonNull List<ContentSource> contentSources) {
        this.contentSources = contentSources;
    }

    public class ContentSourcesViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView image;

        public ContentSourcesViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (itemClickListener!=null){
                    itemClickListener.onItemClick(contentSources.get(getAdapterPosition()).getContentSourceType());
                }
            });

            name = (TextView) itemView.findViewById(R.id.text_view_content_source);

            image = (ImageView) itemView.findViewById(R.id.image_view_content_source);
        }
    }

    @Override
    public ContentSourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_sources, parent, false);
        return new ContentSourcesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContentSourcesViewHolder holder, int position) {
        ContentSource source = contentSources.get(position);

        Glide.with(holder.itemView.getContext())
                .load(source.getIconResId())
                .into(holder.image);

        holder.name.setText(source.getLabelResId());
    }

    @Override
    public int getItemCount() {
        return contentSources.size();
    }

    public void setItemClickListener(@NonNull OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
