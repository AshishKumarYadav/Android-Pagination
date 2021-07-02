package com.ashish.dunzo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.ashish.dunzo.model.Photo;
import com.ashish.dunzo.model.Photos;
import com.ashish.dunzo.model.Root;
import com.ashish.dunzo.network.Api;
import com.ashish.dunzo.network.RetrofitClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Photo> {

    //the size of a page that we want
    public static final int PAGE_SIZE = 20;
    String TAG="ItemDataSource";
    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

    private static final String method = "flickr.photos.search";
    private static final String api_key = "062a6c0c49e4de1d78497d13a7dbb360";
    public static String searchText = "apple";
    private static final int nojsoncallback = 1;
    private static final String format = "json";



    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Photo> callback) {
        RetrofitClient.getInstance()
                .getApi().getResponse(method,api_key,searchText,format,nojsoncallback,PAGE_SIZE,FIRST_PAGE)
                .enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Log.d(TAG,"loadInitial "+response.body().getPhotos().getPhoto());
                        Root root=response.body();
                        if (root != null) {
                            List<Photo> photoList=root.getPhotos().getPhoto();
                            callback.onResult(photoList, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {

                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        RetrofitClient.getInstance()
                .getApi().getResponse(method,api_key,searchText,format,nojsoncallback,PAGE_SIZE,FIRST_PAGE)
                .enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Log.d(TAG,"loadBefore "+response.body());
                        Root root=response.body();
                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        List<Photo> photoList=root.getPhotos().getPhoto();
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (root != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(photoList, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        RetrofitClient.getInstance()
                .getApi().getResponse(method,api_key,searchText,format,nojsoncallback,PAGE_SIZE,FIRST_PAGE)
                .enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Root root=response.body();
                        if (root != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Log.d(TAG,"loadAfter "+response.body());
                            List<Photo> photoList=root.getPhotos().getPhoto();
                            Integer key =response.body().getPhotos().getPage()+1;
                                    //response.body().has_more ? params.key + 1 : null;

                            //passing the loaded data and next page value
                            callback.onResult(photoList, params.key+1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {

                    }
                });
    }
}
