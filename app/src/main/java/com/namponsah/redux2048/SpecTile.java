/**
 * Specific Tile class on the gameBoard
 *  Keeps track of each specific tile and its accompanying methods
 *  A value of 0 indicates an empty tile
 *
 * @author NB Amponsah
 */

package com.namponsah.redux2048;

import java.util.Objects;

public class SpecTile {
    /**
     * innerDigit
     *  data type: int
     *  access: private
     *  holds the true integer value of an object in the Specific Tile class
     */
    private int innerDigit;

    /**
     * Parametrized Constructor
     *  SpecTile sets the innerDigit of a subject object of SpecTile to 0 i.e. empty Tile
     *
     *  @param value
     */
    public SpecTile(int value){
        this.innerDigit = value;
    }

    /**
     * setSpecTile
     *  {re}initializes a subject tile object with a given int value
     *
     *  @param givenDigit the given digit
     */
    public void setSpecTile(int givenDigit){
        this.innerDigit = givenDigit;
    }

    /**
     * getSpecTile
     *  returns the specific value of a specific tile object
     *
     *  @return innerDigit
     */
    public int getSpecTile(){
        return this.innerDigit;
    }

    /**
     * clear
     *  calls the aforementioned setSpecTile above to redefined a subject tile
     *  object to 0 i.e. empty Tile
     */
    public void clear(){
        this.setSpecTile(0);
    }

    /**
     * overloaded equals
     *  compares and returns true iff the innerDigits are the same
     *
     *  @param specTile the "other" class
     *  @return true(match)/false(!match) depending of whether the two tiles match
     */
    public boolean equals(SpecTile specTile){
        //https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
        //https://www.ideyatech.com/effective-java-equals-and-hashcode/
        if (this == specTile){
            return true;
        }

        if (specTile == null){ // null check
            return false;
        }

        final SpecTile that;
        that = specTile;
        return Objects.equals(this.getSpecTile(), that.getSpecTile());

    }

    /**
     * merge
     *  sums two "neighbour" tile objects by calling the aforementioned setSpecTile
     *  with the addition of the subject tile object and other tile object performed inline
     *  preceding condition: iff the two tiles are equal
     *
     *  @param specTile the "other" class of the tile
     */
    public void merge(SpecTile specTile){
        this.setSpecTile(this.innerDigit + specTile.getSpecTile());
    }
}
