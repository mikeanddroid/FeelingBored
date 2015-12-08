package com.mike.givemewingzz.feelinbored.AppModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class JokeCategories extends RealmObject {

    @PrimaryKey
    private String jokeCategory;

    public JokeCategories(){}

    public JokeCategories(String jokeCategory){
        this.jokeCategory = jokeCategory;
    }

    public JokeCategories(JokeCategories copyJokeCategories){
        this.jokeCategory = copyJokeCategories.getJokeCategory();
    }

    public String getJokeCategory() {
        return jokeCategory;
    }

    public void setJokeCategory(String jokeCategory) {
        this.jokeCategory = jokeCategory;
    }
}
