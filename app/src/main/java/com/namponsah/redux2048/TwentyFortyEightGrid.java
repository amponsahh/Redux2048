/**
 * 2048 Gameboard Tracker
 *  This is where the randomization "algorithm", two dimensional array of specific Tile objects
 *  of each individual tile resides for comparision and manipulations as well the various
 *  test conditions to move the game board of tile objects
 *
 *  Keeps track of the gameboard, tiles[][] and its accompanying methods
 *  In places where I was stuck, I referenced a Rosetta Code wiki for help
 *
 * @author NB Amponsah
 */
package com.namponsah.redux2048;
import java.util.Random;
import java.util.Vector;

public class TwentyFortyEightGrid {
    /**
     * sizeOF
     *  data type: int
     *  access: private
     *  holds the dimensions of the two dimensional array of specific Tile objects
     */
    private static final int SIZEof = 4;

    /**
     * random
     *  to sure we truly get a random value every time, we will use just one instance
     *  of new Random and keep calling it where needed
     */
    private Random random = new Random();

    /**
     * SpecTile
     *  two dimensional array of specific Tile objects
     *  to be manipulated
     *
     */
    private SpecTile[][] tiles = new SpecTile[SIZEof][SIZEof];

    /**
     * Non - parametrized Constructor
     *  TwentyFortyEightGrid constructor that instantiates each element of the array of SpecTile
     *  onjects to 0 i.e. empty tile by calling the parametrized Constructor of SpecTile
     *
     */
    public TwentyFortyEightGrid(){
        for(int i = 0; i < SIZEof; i += 1){
            for(int j = 0; j < SIZEof; j += 1){
                this.tiles[i][j] = new SpecTile(0);
            }
        }
    }

    /**
     * getTile
     *  returns the specific value of a specific tile object of
     *  the two dimensional array of SpecTile objects
     *
     *  @param i    rowIndex of subject object
     *  @param j    columnIndex of subject object
     *  @return     the innerDigit value of the subject element of
     *              the two dimensional array of SpecTile objects
     */
    public int getTile(int i, int j){
        return this.tiles[i][j].getSpecTile();
    }

    /**
     * clean
     *  reset the game board/two dimensional array of tile objects
     *  to 0 i.e. empty tile by calling the parametrized Constructor of SpecTile
     *
     */
    public void clean(){
        for(int i = 0; i < SIZEof; i += 1){
            for(int j = 0; j < SIZEof; j += 1){
                this.tiles[i][j] = new SpecTile(0);
            }
        }
    }

    /**
     * getSize
     *
     * @return the dimensions of the SpecTile array for respective looping and analysis
     */
    public int getSize(){
        return SIZEof;
    }

    /**
     * randALG
     *  using the predefined random value and with set constraints
     *  a randomized value/innerDigit is generated based on user defined difficulty
     *  settings
     *
     *  @param flag user defined integer value based on radio options in MainActivity
     *  @return  randomized innerDigit for each respective tile
     */
    private int randALG(int flag){
        int randValue;
        switch (flag){
            case 0: // vanilla flag
                randValue = (((random.nextFloat() * (1.0 - 0) + 0) < 0.9) ? 2 : 4);
                return randValue;

            case 1: // medium
                randValue = (((random.nextFloat() * (1.0 - 0) + 0) < 0.4) ? 2 : 4);
                return randValue;

            case 2: //isyhcd
                randValue = (((random.nextFloat() * (1.0 - 0) + 0) < 0.2) ? 2 :
                        (((random.nextFloat() * (1.0 - 0) + 0) < 0.3) ? 4 : 1));
                return randValue;

            case 3: //ilmmp
                randValue = (((random.nextFloat() * (1.0 - 0) + 0) < 0.08) ? 4 :
                        (((random.nextFloat() * (1.0 - 0) + 0) < 0.12) ? 2 : 1));
                return randValue;

            default: // if not specified, vanilla
                randValue = (((random.nextFloat() * (1.0 - 0) + 0) < 0.9) ? 2 : 4);
                return randValue;
        }
    }

    /**
     * freeTile
     *  checks for an empty tile i.e. 0
     *  if yes, return true
     *  if no, return false
     *
     *  @return true/false depending on whether there's a free tile object
     */
    private boolean freeTile() {
        for (int i = 0; i < SIZEof; i+= 1){
            for (int j = 0; j < SIZEof; j+= 1){
                if (tiles[i][j].getSpecTile() == 0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * emptyTileArr
     *  parses through the two dimensional array of SpecTile objects
     *  to check for an empty object i.e. 0.
     *  it then stores the index of that empty object in the two dimensional
     *  of SpecTiles into a dynamically growing vector for randomization of
     *  an empty slot when generating a new innerDigit
     *
     *  @return vector array of indexes of empty tile objects
     */
    private Vector<String> emptyTileArr(){
        String emptyTileIndex = "";
        Vector<String> eArrIndexes = new Vector<>();

        for (int i = 0; i < SIZEof; i+= 1){
            for (int j = 0; j < SIZEof; j+= 1){
                if (this.tiles[i][j].getSpecTile() == 0){
                    emptyTileIndex += i;
                    emptyTileIndex += j;
                    eArrIndexes.add(emptyTileIndex);
                    emptyTileIndex = "";
                }
            }
        }
        return eArrIndexes;
    }

    /**
     * generateTile
     *  iff there is at least one empty tile object generate a new innerDigit
     *  based user defined difficulty settings
     *  the generation works by setting an empty SpecTile object i.e. 0
     *  which was determined from a vector of indexes to empty SpecTiles
     *
     *  @param algPref user defined difficulty settings
     *  @return true/false depending on whether we were able to generate a new innerDigit
     */
    public boolean generateTile(int algPref){
        // first check for full tiles
        if(freeTile()){ // first check for an empty tile
            // store index of empty tiles in an array
            Vector<String> emptyTileIndexes;
            emptyTileIndexes = emptyTileArr();
            String tempXY;

            if(emptyTileIndexes.size() > 1){
                tempXY = emptyTileIndexes.get(random.nextInt(emptyTileIndexes.size() - 1));
            }else {
                tempXY = emptyTileIndexes.get(0); // only one slot left
            }

            int X = Integer.parseInt(String.valueOf(tempXY.charAt(0)));
            int Y = Integer.parseInt(String.valueOf(tempXY.charAt(1)));

            this.tiles[X][Y].setSpecTile(randALG(algPref));
            return true;

        }else {
            return false; // couldn't generate new tile
        }
    }

    /**
     * moveBoard
     *  Splits two dimensional array into a group of tiles derived from user defined direction
     *  then pushes the group to the end of vector for manipulation if there is  an empty tile object
     *
     *  @param direction User defined position to push the tile
     */
    public void moveBoard(Direction direction){
        for (int i = 0; i < SIZEof; i+= 1){
            Vector<SpecTile> groupTile = new Vector<>();
            for (int j = 0; j < SIZEof; j+= 1){
                switch(direction){
                    case LEFT:
                        groupTile.add(this.tiles[i][j]);
                        break;
                    case RIGHT:
                        groupTile.add(this.tiles[i][SIZEof - j - 1]);
                        break;
                    case UP:
                        groupTile.add(this.tiles[j][i]);
                        break;
                    case DOWN:
                        groupTile.add(this.tiles[SIZEof - j - 1][i]);
                        break;
                    default: // God I hope we don't get here
                        break;
                }
            }
            if (!(EmptyTile(groupTile))){
                push(groupTile);
            }
        }
    }

    /**
     * EmptyTile
     *  iterates through a group of SpecTiles to check if that section is full
     *  essentially freeTile but with a Vector instead for quick iteration and it doesn't
     *  check the entire array in hindsight, probably wasteful but I'm not allowed to change it
     *
     *  @param groupTile the given group of SpecTiles
     *  @return true/false whether or not there's an empty tile object
     */
    private boolean EmptyTile(Vector<SpecTile> groupTile) {
        for (SpecTile specTile: groupTile){
            if (specTile.getSpecTile() != 0){
                return false; // full
            }
        }
        return true;
    }

    /**
     * push
     *  placeholder call to jumpstart the test conditions for properly
     *  stacking generated tile objects
     *
     * @param groupTile the vector of objects of innerDigits for pushing
     */
    private void push(Vector<SpecTile> groupTile){
        edgePush(groupTile);
        mergeTile(groupTile);
    }

    /**
     * edgePush
     *  simply push all the selected tile objects to the edge of the gameboard because
     *  2048 moves on extremes - we don't necessarily move from field to field
     *  the game snaps to the nearest edge phasing through all empty tiles
     *  while snapping to the edge, push the other adjacent SpecTile objects
     *  the isintMin function is just bad naming because I used to refer to an empty tile
     *  i.e 0 as INTEGER.MIN_VALUE but obviously that was unnecessary when zero could suffice
     *
     *  @param groupTile the selected vector of objects to be pushed to respective positions
     */
    private void edgePush(Vector<SpecTile> groupTile){
        for (int i = 0; i < groupTile.size(); i++){
            if (isIntMin(groupTile, i)){
                return; // immediately cease
            }
            while (groupTile.get(i).getSpecTile() == 0){
                pushToX(groupTile, i);
            }
        }
    }

    /**
     * isIntMin
     *  if the remainder SpecTile objects are zero, group them into a vector
     *  then double check for emptiness and report findings
     *
     * @param groupTile the selected vector of objects for empty check and pushing
     * @param k the index at that point in the edgePush that may contain empty SpecTile objects
     * @return  true/false whether or not there's an empty tile object
     */
    private boolean isIntMin(Vector<SpecTile> groupTile, int k) {
        Vector<SpecTile> remainTiles = new Vector<>();
        for (int j = k; j < groupTile.size(); j++){
            remainTiles.add(groupTile.get(j));
        }
        return (EmptyTile(remainTiles));
    }

    /**
     *pushToX
     *  pushes the vector of objects grouped into the adjacent position based on user defined
     *  direction by calling the setSpecTile of the subject object to "merge" with the
     *  adjacent tile and then clear your tracks for the next move!
     *
     *  @param groupTile the selected vector of objects for pushing
     *  @param p the index of the group of object tiles to push onto
     */
    private void pushToX(Vector<SpecTile> groupTile, int p){
        for (int j = p; j < groupTile.size() - 1; j++){
            groupTile.get(j).setSpecTile(groupTile.get(j + 1).getSpecTile());
        }
        groupTile.get(groupTile.size() - 1).clear();
    }

    /**
     * mergeTile
     *  simply put, check the grouping we have, iterate through and see if we can merge anything
     *  so long as it is merge-able then push them together as one
     *
     *  @param groupTile the selected vector of objects to determine merge status and for pushing
     */
    private void mergeTile(Vector<SpecTile> groupTile){
        for (int i = 0; i < groupTile.size() - 1; i++){
            if (groupTile.get(i).equals(groupTile.get(i + 1))){
                groupTile.get(i).merge(groupTile.get(i + 1));
                groupTile.get(i + 1).clear();
                pushToX(groupTile, i + 1);
            }
        }
    }

    /**
     * adjacentEquals
     *  checks the SpecTile two dimensional array for adjacent and equal innerDigits
     *
     *  @return true/false depending on whether adjacent tiles are equal
     */
    private boolean adjacentEquals() {
        for (int i = 0; i < SIZEof; i++){
            for (int j = 0; j < SIZEof; j++){
                if (j < SIZEof - 1){ // bounds hot fix for core dump
                    if (this.tiles[i][j].equals(this.tiles[i][j + 1])){
                        return true;
                    }
                }
                if (i < SIZEof - 1){
                    if (this.tiles[i][j].equals(this.tiles[i + 1][j])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gamer Over check
     *  checks for a freeTile anywhere and checks to see if there are no adjacent equal
     *  SpecTile objects
     *
     *  @return true/false depending on whether could possible proceed in its current state
     */
    public boolean noPossibleMove(){
        if (freeTile()){
            return false;
        }
        return !(adjacentEquals()); }
}