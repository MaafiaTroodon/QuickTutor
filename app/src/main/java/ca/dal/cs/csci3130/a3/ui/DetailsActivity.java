package ca.dal.cs.csci3130.a3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import ca.dal.cs.csci3130.a3.R;
import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.firebase.Book;
import ca.dal.cs.csci3130.a3.firebase.Clothes;
import ca.dal.cs.csci3130.a3.firebase.Food;
import ca.dal.cs.csci3130.a3.firebase.Item;
import ca.dal.cs.csci3130.a3.map.GoogleMapActivity;
import ca.dal.cs.csci3130.a3.util.NumberUtility;

public class DetailsActivity extends AppCompatActivity {

    public Item selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.selectedItem = getSelectedItem();
        if (this.selectedItem != null) {
            this.showItemDetails(this.selectedItem);
            this.showSpecificDetails(this.selectedItem);
        } else {
            Log.e("DetailsActivity", "Selected item is null. Cannot display details.");
            showItemName("Item not found");
        }

        this.setupBuyButton();
        this.setupMapButton();

        TextView audibleOption = findViewById(R.id.audibleOption);

        // Get the intent and extract the "Audible" flag
        boolean isAudible = getIntent().getBooleanExtra("Audible", false);

        // Debugging log
        Log.d("DetailsActivity", "Received item: " + getIntent().getStringExtra("itemName") + ", Audible: " + isAudible);

        // If Audible is true, make it visible
        if (isAudible) {
            audibleOption.setVisibility(View.VISIBLE);
        }
    }

    protected Item getSelectedItem() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("selected_item")) {
                Item item = intent.getParcelableExtra("selected_item");
                if (item != null) {
                    Log.d("DetailsActivity", "Received item: " + item.getName() +
                            ", Audible: " + item.isAudible());
                } else {
                    Log.e("DetailsActivity", "Failed to deserialize 'selected_item'.");
                }
                return item;
            } else {
                Log.e("DetailsActivity", "Intent does not contain 'selected_item'");
            }
        } else {
            Log.e("DetailsActivity", "Intent is null");
        }
        return null;
    }





    public void showItemDetails(Item item) {
        if (item != null) {
            showItemName(item.getName());
            showItemPrice(item.getPrice());
            showItemLocation(item.getLocation());
            showItemAvailability(item.isAvailable());
        }
    }

    public void showSpecificDetails(Item item) {
        Log.d("DetailsActivity", "Checking specific details for: " + item.getName());

        if (item instanceof Clothes) {
            Log.d("DetailsActivity", "Item is Clothes, adding Woolen...");
            addWoolen(item.isWoolen());
        } else if (item instanceof Food) {
            Log.d("DetailsActivity", "Item is Food, adding Perishable...");
            addPerishable(item.isPerishable());
        } else if (item instanceof Book) {
            Log.d("DetailsActivity", "Item is Book, Audible value: " + item.isAudible());
            addAudible(item.isAudible());
        }
    }






    protected void showItemName(String name) {
        TextView itemName = findViewById(R.id.itemName);
        itemName.setText(name);
    }

    protected void showItemPrice(double price) {
        TextView itemPrice = findViewById(R.id.itemPrice);
        itemPrice.setText(NumberUtility.format2Currency(price));
    }

    protected String getItemLocation() {
        TextView itemLocation = findViewById(R.id.itemLocation);
        return itemLocation.getText().toString();
    }

    protected void showItemLocation(String location) {
        TextView itemLocation = findViewById(R.id.itemLocation);
        itemLocation.setText(location);
    }

    protected void showItemAvailability(boolean availability) {
        RadioButton availButton = findViewById(R.id.itemAvailability);
        availButton.setChecked(availability);
        availButton.setEnabled(false);
    }

    protected void addWoolen(boolean isWoolen) {
        GridLayout gridLayout = findViewById(R.id.detailContainerGL);
        RadioButton woolenRadioButton = new RadioButton(this);
        woolenRadioButton.setChecked(isWoolen);
        woolenRadioButton.setEnabled(false);
        woolenRadioButton.setText(AppConstants.WOOLEN);
        gridLayout.addView(woolenRadioButton);
    }

    protected void addPerishable(boolean isPerishable) {
        GridLayout gridLayout = findViewById(R.id.detailContainerGL);
        RadioButton perishRadioButton = new RadioButton(this);
        perishRadioButton.setChecked(isPerishable);
        perishRadioButton.setEnabled(false);
        perishRadioButton.setText(AppConstants.PERISHABLE);
        gridLayout.addView(perishRadioButton);
    }

    protected void addAudible(boolean isAudible) {
        Log.d("DetailsActivity", "Attempting to add Audible option. isAudible: " + isAudible);

        if (!isAudible) {
            Log.w("DetailsActivity", "Audible option is false. Skipping UI update.");
            return;
        }

        runOnUiThread(() -> {
            GridLayout gridLayout = findViewById(R.id.detailContainerGL);
            if (gridLayout == null) {
                Log.e("DetailsActivity", "GridLayout is NULL! Check activity_details.xml");
                return;
            }

            RadioButton audibleRadioButton = new RadioButton(this);
            audibleRadioButton.setChecked(true);
            audibleRadioButton.setEnabled(false);
            audibleRadioButton.setText("Audible");

            gridLayout.addView(audibleRadioButton);
            Log.d("DetailsActivity", "Audible option successfully added to UI.");
        });
    }



    protected void setupBuyButton() {
        Button buyNowButton = findViewById(R.id.buyButton);
        buyNowButton.setOnClickListener(view -> move2Payment());
    }

    protected void setupMapButton() {
        Button showOnMapButton = findViewById(R.id.showOnMapButton);
        showOnMapButton.setOnClickListener(view -> {
            String location = getItemLocation();
            showOnGoogleMap(location);
        });
    }

    protected void move2Payment() {
        Intent paymentIntent = new Intent(this, PaymentActivity.class);
        startActivity(paymentIntent);
    }

    protected void showOnGoogleMap(String location) {
        Intent mapIntent = new Intent(this, GoogleMapActivity.class);
        mapIntent.putExtra("itemLocation", location);
        startActivity(mapIntent);
    }
}
