package com.andiroot.restousinglibrary.stickerview;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jyorjipatel on 2018-03-28.
 */

public class Position extends RealmObject{
    @PrimaryKey
    private int id;
    private int type;
    private double x,y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}
