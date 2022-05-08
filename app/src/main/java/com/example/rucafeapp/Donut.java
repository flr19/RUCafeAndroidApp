package com.example.rucafeapp;

import java.util.ArrayList;

/**
 * Extends MenuItem and has different types of Donuts that customers can order
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class Donut extends MenuItem {

    private static final int ISYEAST = 1;
    private static final int ISCAKE = 2;
    private static final int ISHOLE = 3;

    private static final double YEASTCOST = 1.59;
    private static final double CAKECOST = 1.79;
    private static final double HOLECOST = 0.39;
//
//    private static ArrayList<String> usualTypeYeast= new ArrayList<String>(
//            Arrays.asList("Glazed Chocolate", "Vanilla Creme", "Strawberry Frosted", "Jelly"));
//
//    private static ArrayList<String> usualTypeCake= new ArrayList<String>(
//            Arrays.asList("Chocolate", "Mango", "Sugar", "Jelly"));
//
//    private static ArrayList<String> usualTypeHole= new ArrayList<String>(
//            Arrays.asList("Glazed", "Vanilla", "Jelly", "Sugar"));

    private int donutType; //1 yeast 2 cake donut 3 donut holes
    private String flavor;
    private int image;

    /**
     * Constructor to create Donut
     *
     * @param donutType tyoe of donut
     * @param flavor    of donut
     * @param numItems  number of items
     */



    public Donut(int donutType, String flavor, int numItems) {
        super(numItems);
        image = 0;
        this.donutType = donutType;
        this.flavor = flavor;
    }


    public Donut(String flavor, String donutType, int numItems) {
        super(numItems);
        this.flavor = flavor;
        image = 0;
        if(donutType.equals("Yeast Donut")){
            this.donutType = ISYEAST;
        }
        else if(donutType.equals("Cake Donut")) {
            this.donutType = ISCAKE;
        }
        else if(donutType.equals("Donut Holes")) {
            this.donutType = ISHOLE;
        }
    }

    public Donut(String donutName, int image){
        super(1);
        this.image = image;
        ArrayList<String> separatedName = flavorTypeSeparator(donutName);
        flavor = separatedName.get(0);
        String type = separatedName.get(1);
        if(type.equals("Yeast")) {
            donutType = ISYEAST;
        }
        else if(type.equals("Cake")) {
            donutType = ISCAKE;
        }
        else {
            donutType = ISHOLE;
        }

    }

    public static ArrayList<String> flavorTypeSeparator (String donutName){
        int currIndex = 0;
        int hyphenIndex = 0;
        ArrayList<String> res = new ArrayList<>();
        while(currIndex < donutName.length()){
            if(donutName.charAt(currIndex) == '-'){
                hyphenIndex = currIndex;
            }
            currIndex++;
        }
        String flavor = donutName.substring(0, hyphenIndex-1);
        String type = donutName.substring(hyphenIndex+2);
        res.add(flavor);
        res.add(type);
        return res;
    }

    /**
     * Method to rcalculate price of donut
     *
     * @return price of donut
     */

    @Override
    public double itemPrice() {
        if (donutType == ISYEAST) {
            return getNumItems() * YEASTCOST;
        } else if (donutType == ISCAKE) {
            return getNumItems() * CAKECOST;
        } else {
            return getNumItems() * HOLECOST;
        }
    }

    /**
     * Method to compare 2 donut items
     *
     * @return true if they are the same, false otherwise
     */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut donut = (Donut) obj;
            if (donutType == donut.donutType) {
                if (flavor.equals(donut.flavor)) {
                    if (getNumItems() == donut.getNumItems()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method to return donut order as string
     *
     * @return the order in string format
     */

    @Override
    public String toString() {
        return getDonutType() + " - " + flavor + " (" + getNumItems() + ")" + " ---> $" + String.format("%.2f" ,itemPrice());
    }

    /**
     * Method to show donut type
     *
     * @return type of donut
     */

    public String getDonutType() {
        if (donutType == ISYEAST) {
            return "Yeast Donut";
        } else if (donutType == ISCAKE) {
            return "Cake Donut";
        }
        return "Donut Holes";
    }

    public int getImage() {
        return image;
    }

    public String getFlavor() {
        return flavor;
    }

    public boolean compareDonuts (String donutName) {
        ArrayList<String> res= flavorTypeSeparator(donutName);
        String flavor = res.get(0);
        String type = res.get(1);
        if(this.flavor.equals(flavor) ) {
            if((type.equals("Yeast Donut") && donutType == ISYEAST) ||
                    (type.equals("Cake Donut") && donutType == ISCAKE) ||
                    (type.equals("Donut Holes") && donutType == ISHOLE)) {
                return true;
            }
        }
        return false;
    }
}