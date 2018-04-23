package com.andiroot.restousinglibrary.stickerview;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListNames extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;

    public ListNames() {
    }

    public ListNames(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
