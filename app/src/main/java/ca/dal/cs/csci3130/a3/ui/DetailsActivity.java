package ca.dal.cs.csci3130.a3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    }

    protected Item getSelectedItem() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_item")) {
            return (Item) intent.getSerializableExtra("selected_item");
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
        GridLayout gridLayout = findViewById(R.id.detailContainerGL);
        if (item instanceof Clothes) {
            addWoolen(((Clothes) item).isWoolen());
        } else if (item instanceof Food) {
            addPerishable(((Food) item).isPerishable());
        } else if (item instanceof Book) {
            addAudible(((Book) item).isPaperback());
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

    protected void addAudible(boolean isPaperback) {
        GridLayout gridLayout = findViewById(R.id.detailContainerGL);
        RadioButton audibleRadioButton = new RadioButton(this);
        audibleRadioButton.setChecked(isPaperback);
        audibleRadioButton.setEnabled(false);
        audibleRadioButton.setText(AppConstants.AUDIBLE);
        gridLayout.addView(audibleRadioButton);
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
