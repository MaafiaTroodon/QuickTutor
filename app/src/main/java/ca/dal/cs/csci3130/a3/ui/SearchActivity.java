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
    RecyclerView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.crud = new ItemCRUD();
        this.resultView = findViewById(R.id.resultsRecyclerView);

        this.loadCategorySpinner();
        this.setupSearchButton();
        setupRecyclerView();
    }

    // Load spinner with categories
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

    // Get selected category from spinner
    protected String getSelectedCategory() {
        Spinner catSpinner = findViewById(R.id.categorySpinner);
        return catSpinner.getSelectedItem().toString();
    }

    // Fix the RecyclerView setup method
    protected void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        resultView.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(resultView.getContext(), manager.getOrientation());
        resultView.addItemDecoration(decoration);
    }

    // Fix the method to display search results
    protected void showSearchResults(ArrayList<Item> items, ArrayList<String> headers) {
        if (items == null || items.isEmpty() || headers == null || headers.isEmpty()) {
            return; // Avoid setting an empty or null adapter
        }

        if (adapter == null) { // Initialize adapter if it's null
            adapter = new SearchRecyclerViewAdapter(this, items, headers);
            adapter.setClickListener(this); // Set the click listener
            resultView.setAdapter(adapter);
        } else {
            adapter.updateData(items, headers); // Update adapter with new data
            adapter.notifyDataSetChanged();
        }
    }




    // Fix the method to collect retrieved results based on the category
    protected ArrayList<Item> collectRetrievedResults(String category) {
        if (category.equals("Select a category")) {
            return new ArrayList<>(); // Return empty list if no category is selected
        }

        ArrayList<Item> items = crud.getItemsByCategory(category);
        return (items != null) ? items : new ArrayList<>();
    }


    // Retrieve item headers
    protected ArrayList<String> collectItemHeaders() {
        return this.crud.collectItemHeaders();
    }

    // Setup search button to perform a search and display results
    protected void setupSearchButton() {
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(view -> {
            String category = getSelectedCategory();
            ArrayList<Item> results = collectRetrievedResults(category);
            ArrayList<String> headers = collectItemHeaders();
            showSearchResults(results, headers);
        });
    }

    // Handle item click event
    @Override
    public void onItemClick(View view, int position) {
        if (adapter != null) {
            Item selectedItem = adapter.getItem(position);
            showItemDetails(selectedItem);
        }
    }

    // Open details activity with the selected item
    protected void showItemDetails(Item selected) {
        Intent detailIntent = new Intent(getBaseContext(), DetailsActivity.class);
        detailIntent.putExtra("selected_item", selected); // Ensure the key matches the receiver
        startActivity(detailIntent);
    }

}
