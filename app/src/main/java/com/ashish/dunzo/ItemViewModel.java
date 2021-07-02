package com.ashish.dunzo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.ashish.dunzo.model.Photo;
import com.ashish.dunzo.model.Photos;

public class ItemViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    LiveData<PagedList<Photo>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Photo>> liveDataSource;

    //constructor
    public ItemViewModel() {
        //getting our data source factory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    void invalidateDataSource(){
        itemPagedList.getValue().getDataSource().invalidate();

    }
}

