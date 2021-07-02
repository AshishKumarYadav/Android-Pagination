package com.ashish.dunzo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.ashish.dunzo.adapter.ItemAdapter;
import com.ashish.dunzo.model.Photo;

//Developed by Ashish Kr Yadav

public class MainActivity extends AppCompatActivity {

    String TAG="MainActivity";
    private RecyclerView recyclerView;
    ItemViewModel itemViewModel;
    ItemAdapter adapter;
    SearchView searchView;
    MenuItem menuSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting up recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        itemViewModel = ViewModelProviders.of(MainActivity.this).get(ItemViewModel.class);

        //creating the Adapter
        adapter = new ItemAdapter(MainActivity.this);


        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> items) {

                //in case of any changes
                //submitting the items to adapter
                //handler added to wait for the response and load once response received
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(items);
                        recyclerView.setAdapter(adapter);

                    }
                }, 2000);



            }
        });

        //setting the adapter

        Log.d(TAG,"VAL_ADAP ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view, menu);

        menuSearch = menu.findItem(R.id.search);

        searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    // Override onQueryTextSubmit method
                    // which is called
                    // when submit query is searched
                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {
                        // If the list contains the search query
                        // than filter the adapter
                        // using the filter method
                        // with the query as its argument
                        if (!query.trim().isEmpty()){
                            performSearch(query);
                        }
                        return false;
                    }
                    // This method is overridden to filter
                    // the adapter according to a search query
                    // when the user is typing search
                    @Override
                    public boolean onQueryTextChange(String newText)
                    {
                        return false;
                    }
                });

        return super.onCreateOptionsMenu(menu);
    }

    private void performSearch(String searchKey) {
        // TODO: Perform the search and update the UI to display the results.
        ItemDataSource.searchText=searchKey;
        itemViewModel.invalidateDataSource();
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> items) {

                //in case of any changes
                //submitting the items to adapter
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        adapter.submitList(items);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        menuSearch.collapseActionView();

                    }
                }, 2000);



            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
    }

}
