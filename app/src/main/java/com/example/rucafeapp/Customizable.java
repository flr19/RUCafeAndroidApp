package com.example.rucafeapp;

/**
 * customizable interface to add and remove add-ins
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public interface Customizable {

    /**
     * adds an add-in to an object
     * @param obj to be added
     * @return true if added, false otherwise
     */
    boolean add(Object obj);

    /**
     * removes an add-in to an object
     * @param obj to be removed
     * @return true if removed, false otherwise
     */
    boolean remove(Object obj);
}