// Greg Heggie
// MDF3 - 1708
// Treasure

package com.example.gheggie.DigDug;

import java.io.Serializable;

class Treasure implements Serializable{

    private final String name;
    private final String amount;
    private int locX;
    private int locY;

    Treasure(String _name, String _amount){
        name = _name;
        amount = _amount;
    }

    Treasure(String _name, String _amount, int x, int y){
        name = _name;
        amount = _amount;
        locX = x;
        locY = y;
    }

    String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + "      " + amount;
    }

    String getName() {
        return name;
    }

    String getNameAndAmt() {
        return name + "(" + amount + ")";
    }

    int getLocX() {
        return locX;
    }

    int getLocY() {
        return locY;
    }
}
