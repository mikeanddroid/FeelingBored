package com.mike.givemewingzz.feelinbored.servicetest;

import android.test.AndroidTestCase;

import com.mike.givemewingzz.feelinbored.AppModel.JokeCategories;
import com.mike.givemewingzz.feelinbored.AppModel.JokeCategoriesWrapper;
import com.mike.givemewingzz.feelinbored.AppModel.RandomJoke;
import com.mike.givemewingzz.feelinbored.AppModel.RandomJokeWrapper;
import com.mike.givemewingzz.feelinbored.Utils.BaseClient;
import com.mike.givemewingzz.feelinbored.Utils.BaseRetrofitInterface;

/**
 * Created by GiveMeWingzz on 12/8/2015.
 */
public class ApiTest extends AndroidTestCase {

    public void testApiCall(){

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        // Get random jokes
        RandomJokeWrapper<RandomJoke> jokeWrapper = baseRetrofitInterface.getRandomJokes();

        // Checking for data assertion.
        assertNotNull(jokeWrapper);
        assertNotNull(jokeWrapper.getValue().getJoke());

    }

    public void testJokeCategories(){

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();
        // Get random jokes
        JokeCategoriesWrapper<JokeCategories> jokeWrapper = baseRetrofitInterface.getJokeCategories();

        // Checking for data assertion.
        assertNotNull(jokeWrapper);
        assertNotNull(jokeWrapper.getValue());

    }

}
