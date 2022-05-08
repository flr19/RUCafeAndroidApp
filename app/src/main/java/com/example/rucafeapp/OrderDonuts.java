package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Activity class for Order Donut layout
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class OrderDonuts extends AppCompatActivity {
    //Declare an instance of ArrayList to hold the items to be display with the RecyclerView
    private ArrayList<Donut> items = new ArrayList<>();

    private int [] itemImages = {R.drawable.chococake, R.drawable.glazedchocolate,R.drawable.glazedhole,
    R.drawable.jelly,R.drawable.jellycake,R.drawable.mangocake,R.drawable.strawberryfrosted,
    R.drawable.vanillahole,R.drawable.jellyholedonut,R.drawable.sugarcakedonut,
    R.drawable.sugarholedonut, R.drawable.vanillacremedonut};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donuts);
        RecyclerView rcview = findViewById(R.id.donutsList);
        setupMenuItems(); //add the list of items to the ArrayList
        DonutAdapter adapter = new DonutAdapter(this,items);
        rcview.setAdapter(adapter);

        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Sets up donut items for display
     */

    private void setupMenuItems() {

        String [] itemNames = getResources().getStringArray(R.array.donutNames);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Donut(itemNames[i],itemImages[i]));
        }
    }
}
