package com.mike.givemewingzz.feelinbored.AppModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class RandomJoke extends RealmObject{

    @PrimaryKey
    private int id;

    private String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

}
