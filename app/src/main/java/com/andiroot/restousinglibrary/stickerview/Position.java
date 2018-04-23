package com.andiroot.restousinglibrary.stickerview;

import android.graphics.drawable.Drawable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jyorjipatel on 2018-03-28.
 */

public class Position extends RealmObject{
    @PrimaryKey
    private int id;
    private int type;
    private float angle, x, y;
    private int height, width;
    private  boolean isFlippedHorizontally, isFlippedVertically;

    public Position() {
    }

    public Position(int id, int type, float angle, float x, float y, int height, int width, boolean isFlippedHorizontally, boolean isFlippedVertically) {
        setId(id);
        setType(type);
        setAngle(angle);
        setX(x);
        setY(y);
        setHeight(height);
        setWidth(width);
        setFlippedHorizontally(isFlippedHorizontally);
        setFlippedVertically(isFlippedVertically);
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFlippedHorizontally() {
        return isFlippedHorizontally;
    }

    public void setFlippedHorizontally(boolean flippedHorizontally) {
        isFlippedHorizontally = flippedHorizontally;
    }

    public boolean isFlippedVertically() {
        return isFlippedVertically;
    }

    public void setFlippedVertically(boolean flippedVertically) {
        isFlippedVertically = flippedVertically;
    }
}
