package com.mike.givemewingzz.feelinbored.AppModel;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class RandomJokeWrapper<T> {

    private String type;

    private T value;

    public T getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
