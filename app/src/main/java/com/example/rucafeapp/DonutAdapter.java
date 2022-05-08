package com.example.rucafeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.ItemsHolder> {

    private Order order = MainActivity.order;

    private Context context; //need the context to inflate the layout
    public static ArrayList<Donut> items; //need the data binding to each row of RecyclerView

    public DonutAdapter(Context context, ArrayList<Donut> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DonutAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        //return new ItemsHolder(view);
        return new DonutAdapter.ItemsHolder(view);
        //change this from video if not working
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     *
     * @param holder   the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.tv_donutName.setText(items.get(position).getFlavor() + " - " +
                items.get(position).getDonutType());
        holder.tv_donutPrice.setText("$ " + items.get(position).itemPrice());
        holder.donutPic.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     *
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        ImageView donutPic;
        TextView tv_donutName, tv_donutPrice;
        //Button bttn_add;
        private ConstraintLayout parentLayout;


        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            donutPic = itemView.findViewById(R.id.donutImage);
            tv_donutName = itemView.findViewById(R.id.donutName);
            tv_donutPrice = itemView.findViewById(R.id.donutPrice);
           // bttn_add = itemView.findViewById(R.id.addDonut);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            //setAddButtonOnClick(itemView);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ItemSelectedActivity.class);
                    intent.putExtra("ITEM", tv_donutName.getText());
                    //intent.putExtra("IMAGE", (Parcelable) donutPic);

                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }
}





