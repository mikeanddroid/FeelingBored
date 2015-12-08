package com.mike.givemewingzz.feelinbored.Utils;

import com.mike.givemewingzz.feelinbored.AppModel.JokeCategories;
import com.mike.givemewingzz.feelinbored.AppModel.JokeCategoriesWrapper;
import com.mike.givemewingzz.feelinbored.AppModel.RandomJoke;
import com.mike.givemewingzz.feelinbored.AppModel.RandomJokeWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public interface BaseRetrofitInterface {

    @GET("/jokes/random")
    @Headers("X-SI-API-VERSION: 1.0")
    RandomJokeWrapper<RandomJoke> getRandomJokes();

    @GET("/jokes/random")
    @Headers("X-SI-API-VERSION: 1.0")
    void getRandomJokes(Callback<RandomJokeWrapper<RandomJoke>> cb);

    @GET("/categories")
    @Headers("X-SI-API-VERSION: 1.0")
    JokeCategoriesWrapper<JokeCategories> getJokeCategories();

    @GET("/jokes/random/{count}")
    @Headers("X-SI-API-VERSION: 1.0")
    void getMultipleRandomJokes(@Path("serviceOrderNumber") String count, Callback<RandomJokeWrapper<RandomJoke>> cb);

    @GET("/jokes/count")
    @Headers("X-SI-API-VERSION: 1.0")
    void getLimitedJokes(Callback<RandomJokeWrapper<RandomJoke>> cb);

    @GET("/categories")
    @Headers("X-SI-API-VERSION: 1.0")
    void getJokesCategory(Callback<JokeCategoriesWrapper> cb);

}
