package com.leolerasse.milkyway.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leolerasse.milkyway.models.MilkyWayImageModel;
import com.leolerasse.milkyway.network.IAPIService;
import com.leolerasse.milkyway.network.RetrofitClient;
import com.leolerasse.milkyway.network.models.APIResponse;
import com.leolerasse.milkyway.network.models.Item;
import com.leolerasse.milkyway.utils.Constants;
import com.leolerasse.milkyway.utils.DateUtils;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeImagesListViewModel extends ViewModel {

    private MutableLiveData<List<MilkyWayImageModel>> imagesList;

    public HomeImagesListViewModel() {
        imagesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<MilkyWayImageModel>> getImagesCollectionListObserver(){
        return imagesList;
    }

    /**
     * Get List of images from API and map them to our MilkyWayImageModel
     * we don't need unnecessary stuffs in this response
     */
    public void fetchImages(){
        IAPIService iapiService = RetrofitClient.getClient().create(IAPIService.class);
        iapiService.getImages(
                Constants.QUERY,
                Constants.MEDIA_TYPE,
                Constants.YEAR_START,
                Constants.YEAR_END).subscribeOn(Schedulers.io())
                .toObservable()
                .flatMapIterable(response -> response.collection.items)
                .map(item -> new MilkyWayImageModel(item.data.get(0).center, item.data.get(0).title, item.data.get(0).description, DateUtils.getFormattedDate(item.data.get(0).dateCreated), item.links.get(0).href))
                .toList()
                .subscribe(this::onSuccess, this::onFailure);
    }

    private void onSuccess(List<MilkyWayImageModel> imagesModels) {
        imagesList.postValue(imagesModels);
    }

    private void onFailure(Throwable t) {
        imagesList.postValue(null);
    }

}
