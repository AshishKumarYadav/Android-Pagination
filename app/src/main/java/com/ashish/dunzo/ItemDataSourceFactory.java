package com.ashish.dunzo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.ashish.dunzo.model.Photo;
import com.ashish.dunzo.model.Photos;

public class ItemDataSourceFactory extends DataSource.Factory {
    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> itemLiveDataSource = new MutableLiveData<>();
    private String searchKey;


    @Override
    public DataSource<Integer, Photo> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource();

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);
        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}