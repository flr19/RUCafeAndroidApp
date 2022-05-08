package com.example.rucafeapp;

/**
 * This is the superclass of all menu items, such as donuts and coffee. Any class defined for
 * a menu item must extend this class.
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public abstract class MenuItem {

    private int numItems;

    /**
     * Abstract method to return price of item
     */

    public abstract double itemPrice();

    /**
     * Abstract method to return order in string format
     */

    public abstract String toString();

    /**
     * Method to return number of items in order
     *
     * @param numItems is n umber of items in order
     */

    public MenuItem(int numItems) {
        this.numItems = numItems;
    }

    /**
     * Sets number of items in order
     *
     * @param numItems is number of items
     */

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    /**
     * Gets number of items in an order
     *
     * @return number of items
     */

    public int getNumItems() {
        return numItems;
    }
}
