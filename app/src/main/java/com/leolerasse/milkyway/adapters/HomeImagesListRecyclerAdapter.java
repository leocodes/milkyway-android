package com.leolerasse.milkyway.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leolerasse.milkyway.databinding.HomeImageListItemBinding;
import com.leolerasse.milkyway.network.models.APIResponse;
import com.leolerasse.milkyway.network.models.Collection;
import com.leolerasse.milkyway.network.models.Item;

import java.util.List;

public class HomeImagesListRecyclerAdapter extends RecyclerView.Adapter<HomeImagesListRecyclerAdapter.HomeImagesListItemViewHolder> {

    private Context context;
    private List<Item> imagesList;

    public HomeImagesListRecyclerAdapter(Context context, List<Item> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    public void setImagesCollectionList(List<Item> imagesList){
        this.imagesList = imagesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeImagesListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeImagesListItemViewHolder(HomeImageListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeImagesListItemViewHolder holder, int position) {
        holder.binding.txtTitle.setText(imagesList.get(position).data.get(0).title);
        holder.binding.txtCenterDate.setText("CCD  |  etrre");
        Glide.with(context)
                .load(imagesList.get(position).links.get(0).href)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.binding.milkyWayImage);

    }

    @Override
    public int getItemCount() {
        return imagesList != null ? imagesList.size() : 0;
    }

    public class HomeImagesListItemViewHolder extends RecyclerView.ViewHolder{

        HomeImageListItemBinding binding;

        public HomeImagesListItemViewHolder(HomeImageListItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
