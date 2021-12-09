package com.leolerasse.milkyway.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leolerasse.milkyway.network.IAPIService;
import com.leolerasse.milkyway.network.RetrofitClient;
import com.leolerasse.milkyway.network.models.APIResponse;
import com.leolerasse.milkyway.network.models.Item;
import com.leolerasse.milkyway.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeImagesListViewModel extends ViewModel {

    private MutableLiveData<List<Item>> imagesList;

    public HomeImagesListViewModel() {
        imagesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Item>> getImagesCollectionListObserver(){
        return imagesList;
    }

    public void fetchImages(){
        IAPIService iapiService = RetrofitClient.getClient().create(IAPIService.class);
        iapiService.getImages(
                Constants.QUERY,
                Constants.MEDIA_TYPE,
                Constants.YEAR_START,
                Constants.YEAR_END).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                imagesList.postValue(response.body().collection.items);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                imagesList.postValue(null);
            }
        });
    }
}
