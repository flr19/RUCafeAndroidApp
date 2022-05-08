package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is the Activity to be started when an item on the
 * RecyclerView was clicked
 * Get the name of the item from an intent extra. The text of the button is set to the item name.
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class ItemSelectedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Donut donut;
    private ImageView img;
    private TextView donutName;
    private Spinner quantitySpinner;
    private Integer[] quantity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private ArrayAdapter<Integer> quantity_array;
    private TextView donutPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        Intent intent = getIntent();
        String name = getIntent().getStringExtra("ITEM");
        donutName = findViewById(R.id.donutActivityName);
        donutName.setText(name);

        donutPrice = findViewById(R.id.price);
        donutPrice.setEnabled(false);

        Donut fakeDonut = getDonutObj(name);
        img = findViewById(R.id.donutActivityPic);
        img.setImageResource(fakeDonut.getImage());

        donut = new Donut(fakeDonut.getFlavor(), fakeDonut.getDonutType(), 1);

        quantitySpinner = findViewById(R.id.donutQuantity);
        quantity_array = new ArrayAdapter<Integer>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantity);
        quantitySpinner.setAdapter(quantity_array);
        quantitySpinner.setOnItemSelectedListener(this);


        donutPrice.setText(String.format("%.2f", donut.itemPrice()));

    }

    /**
     * Gets a donut object from items list
     *
     * @param donutName is the donut name
     * @return matching donut object
     */

    public Donut getDonutObj(String donutName) {
        for (int i = 0; i < DonutAdapter.items.size(); i++) {
            if (DonutAdapter.items.get(i).compareDonuts(donutName)) {
                return DonutAdapter.items.get(i);
            }
        }
        return null;
    }

    /**
     * Sets the quantity on the quantity spinner
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
        donut.setNumItems(quantity);
        //DISPLAY PRICE
        donutPrice.setText("$ " + String.format("%.2f", donut.itemPrice()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * add method for add button, adds an order to the
     * storeOrders list
     *
     * @param view is the view object
     */

    public void addToOrder(View view) {
        MainActivity.order.add(donut);

        Context context = getApplicationContext();
        CharSequence text = "Added to order!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        donut = new Donut(donut.getFlavor(), donut.getDonutType(), 1);
        quantitySpinner.setSelection(0);
        donutPrice.setText("$ " + String.format("%.2f", donut.itemPrice()));


    }
}