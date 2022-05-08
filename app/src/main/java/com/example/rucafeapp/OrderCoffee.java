package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Activity class for Order Coffee layout
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class OrderCoffee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Coffee currCoffee;

    private Order order = MainActivity.order;

    private Spinner coffeeSize;

    private Spinner quantitySpinner;

    private CheckBox milk, caramel, syrup, whippedCream, cream;

    private EditText coffee_subtotal;

    private String[] sizeNames = {"Short", "Tall", "Grande", "Venti"};

    private Integer[] quantity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private ArrayAdapter<String> sizes;

    private ArrayAdapter<Integer> quantity_array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        currCoffee = new Coffee(1, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);
        milk = findViewById(R.id.checkBox_milk);
        caramel = findViewById(R.id.checkBox_caramel);
        syrup = findViewById(R.id.checkBox_syrup);
        whippedCream = findViewById(R.id.checkBox_whippedCream);
        cream = findViewById(R.id.checkBox_cream);

        coffeeSize = findViewById(R.id.size);
        sizes = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizeNames);
        coffeeSize.setAdapter(sizes);
        coffeeSize.setOnItemSelectedListener(this);

        quantitySpinner = findViewById(R.id.quantity);
        quantity_array = new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantity);
        quantitySpinner.setAdapter(quantity_array);
        quantitySpinner.setOnItemSelectedListener(this);

        coffee_subtotal = findViewById(R.id.subtotal);
        coffee_subtotal.setEnabled(false);

    }

    /**
     * Sets the quantity on the quantity spinner and the size of the coffee
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String sizeSelected = coffeeSize.getSelectedItem().toString();
        String numItems = quantitySpinner.getSelectedItem().toString();
        currCoffee.setSize(sizeSelected);
        System.out.println(sizeSelected);
        currCoffee.setNumItems(Integer.parseInt(numItems));
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * add method for add button, adds a coffee to the
     * orders list and resets the checkboxes
     *
     * @param view is the view object
     */

    public void addToOrder(View view) {
        order.add(currCoffee);
        Context context = getApplicationContext();
        CharSequence text = "Added to order!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        currCoffee = new Coffee(1, 1);
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
        cream.setChecked(false);
        milk.setChecked(false);
        syrup.setChecked(false);
        caramel.setChecked(false);
        whippedCream.setChecked(false);
        coffeeSize.setSelection(0);
        quantitySpinner.setSelection(0);

    }

    /**
     * adds milk if milk is ticked
     */

    public void updateMilk(View view) {
        if (milk.isChecked()) {
            currCoffee.add("Milk");
        } else {
            currCoffee.remove("Milk");
        }
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
    }

    /**
     * adds cream if cream is ticked
     */

    public void updateCream(View view) {
        if (cream.isChecked()) {
            currCoffee.add("Cream");
        } else {
            currCoffee.remove("Cream");
        }
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
    }

    /**
     * adds syrup if syrup is ticked
     */

    public void updateSyrup(View view) {
        if (syrup.isChecked()) {
            currCoffee.add("Syrup");
        } else {
            currCoffee.remove("Syrup");
        }
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
    }

    /**
     * adds caramel if caramel is ticked
     */

    public void updateCaramel(View view) {
        if (caramel.isChecked()) {
            currCoffee.add("Caramel");
        } else {
            currCoffee.remove("Caramel");
        }
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
    }

    /**
     * adds whipped cream if whipped cream is ticked
     */

    public void updateWhippedCream(View view) {
        if (whippedCream.isChecked()) {
            currCoffee.add("Whipped Cream");
        } else {
            currCoffee.remove("Whipped Cream");
        }
        coffee_subtotal.setText(String.format("%.2f", currCoffee.itemPrice()));
    }
}