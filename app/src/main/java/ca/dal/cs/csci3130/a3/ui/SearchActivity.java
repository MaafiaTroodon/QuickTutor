package ca.dal.cs.csci3130.a3.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ca.dal.cs.csci3130.a3.R;
import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.firebase.Item;
import ca.dal.cs.csci3130.a3.firebase.ItemCRUD;
import ca.dal.cs.csci3130.a3.util.SearchRecyclerViewAdapter;

public class SearchActivity extends AppCompatActivity implements SearchRecyclerViewAdapter.ItemClickListener {

    ItemCRUD crud;
    SearchRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.loadCategorySpinner();
        this.setupSearchButton();
        this.crud = new ItemCRUD();
    }

    protected void loadCategorySpinner() {
        List<String> categories = new ArrayList<>();
        categories.add("Select a category");
        categories.add(AppConstants.CLOTHES);
        categories.add(AppConstants.FOOD);
        categories.add(AppConstants.BOOK);
        Spinner catSpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categoryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        catSpinner.setAdapter(categoryAdapter);
    }

    protected String getSelectedCategory() {
        Spinner catSpinner = findViewById(R.id.categorySpinner);
        return catSpinner.getSelectedItem().toString();
    }

    protected void showSearchResults(ArrayList<Item> items, ArrayList<String> headers) {
        //buggy method, fix the bug!
        RecyclerView resultView = findViewById(R.id.resultsRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        resultView.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(resultView.getContext(), manager.getOrientation());
        resultView.addItemDecoration(decoration);
    }


    protected ArrayList<Item> collectRetrievedResults(String category) {
        //buggy method, fix the bug!
        return null;
    }

    protected ArrayList<String> collectItemHeaders() {
        return this.crud.collectItemHeaders();
    }

    protected void setupSearchButton() {
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(view -> {
            String category = getSelectedCategory();
            ArrayList<Item> results = collectRetrievedResults(category);
            ArrayList<String> headers = collectItemHeaders();
            showSearchResults(results, headers);
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        //incomplete method, add the behaviour
    }

    protected void showItemDetails(Item selected) {
        Intent detailIntent = new Intent(getBaseContext(), DetailsActivity.class);
        detailIntent.putExtra("selectedItem", selected);
        startActivity(detailIntent);
    }
}