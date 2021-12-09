package com.leolerasse.milkyway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leolerasse.milkyway.adapters.HomeImagesListRecyclerAdapter;
import com.leolerasse.milkyway.databinding.ActivityHomeImagesListBinding;
import com.leolerasse.milkyway.models.MilkyWayImageModel;
import com.leolerasse.milkyway.network.models.Item;
import com.leolerasse.milkyway.viewmodels.HomeImagesListViewModel;

import java.util.List;

public class HomeImagesListActivity extends AppCompatActivity {

    private ActivityHomeImagesListBinding binding;
    private List<MilkyWayImageModel> imagesList;
    private HomeImagesListRecyclerAdapter adapter;
    private HomeImagesListViewModel homeImagesListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeImagesListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initViews();
    }

    private void initViews(){
        adapter = new HomeImagesListRecyclerAdapter(this, imagesList);
        homeImagesListViewModel = new ViewModelProvider(this).get(HomeImagesListViewModel.class);

        binding.recyclerImagesList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerImagesList.setAdapter(adapter);

        homeImagesListViewModel.getImagesCollectionListObserver().observe(this, new Observer<List<MilkyWayImageModel>>() {
                    @Override
                    public void onChanged(List<MilkyWayImageModel> milkyWayImageModels) {
                        imagesList  = milkyWayImageModels;
                        adapter.setImagesCollectionList(milkyWayImageModels);
                    }
        });
        homeImagesListViewModel.fetchImages();

    }
}