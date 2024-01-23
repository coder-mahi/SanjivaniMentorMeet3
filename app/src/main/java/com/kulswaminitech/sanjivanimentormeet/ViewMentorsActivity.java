package com.kulswaminitech.sanjivanimentormeet;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewMentorsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
//    private MyAdapter adapter;
    private List<ClipData.Item> itemList = new ArrayList<>();
    private List<ClipData.Item> filteredList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mentors);

//// Initialize RecyclerView and adapter
//    recyclerView = findViewById(R.id.recyclerView);
//    adapter = new MyAdapter(this, filteredList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    // Prepare sample data
//    prepareData();
}
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//
//    {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.actionSearch);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean
//            onQueryTextSubmit(String query)
//            {
//                return false;
//            }
//            @Override
//            public boolean
//            onQueryTextChange(String newText)
//            {
//                filterList(newText);
//                return false;
//            }
//        });
//        return true;
//    }
//
//    private
//
//    void
//
//    filterList(String query) {
//        filteredList.clear();
//
//        for (ClipData.Item item : itemList) {
//            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
//                filteredList.add(item);
//            }
//        }
//
//        adapter.notifyDataSetChanged();
//    }
//
//    private void prepareData() {
//        // Add sample items to the list (replace with your actual data)
//        itemList.add(new ClipData.Item("Item 1"));
//        itemList.add(new ClipData.Item("Item 2"));
//        itemList.add(new ClipData.Item("Item 3"));
//        // ...
//
//        filteredList.addAll(itemList); // Initially display all items
//    }
}
