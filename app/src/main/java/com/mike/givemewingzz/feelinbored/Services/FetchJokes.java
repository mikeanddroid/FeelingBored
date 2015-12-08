package com.mike.givemewingzz.feelinbored.Services;

import android.content.Context;
import android.util.Log;

import com.mike.givemewingzz.feelinbored.AppModel.RandomJoke;
import com.mike.givemewingzz.feelinbored.AppModel.RandomJokeWrapper;
import com.mike.givemewingzz.feelinbored.Utils.BaseClient;
import com.mike.givemewingzz.feelinbored.Utils.BaseRetrofitInterface;
import com.mike.givemewingzz.feelinbored.Utils.OttoHelper;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class FetchJokes {

    private static final String TAG = FetchJokes.class.getSimpleName();

    public static void call(final Context context) {

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        baseRetrofitInterface.getRandomJokes(new Callback<RandomJokeWrapper<RandomJoke>>() {
            @Override
            public void success(RandomJokeWrapper<RandomJoke> jokesRandomJokeWrapper, Response response) {

                RandomJoke randomJokes1 = jokesRandomJokeWrapper.getValue();

                Log.d(TAG, randomJokes1.getJoke());

                try {

                    Realm realm = Realm.getInstance(context);

                    RandomJoke randomJokes = jokesRandomJokeWrapper.getValue();

                    realm.beginTransaction();

                    realm.clear(RandomJoke.class);

                    if (randomJokes != null) {
                        realm.copyToRealmOrUpdate(randomJokes);
                    }

                    realm.commitTransaction();

                    OttoHelper.post(new SuccessEvent(jokesRandomJokeWrapper, response));

                } catch (NullPointerException npe) {
                    Log.e(TAG + ":: Error :: ", "Missing element somewhere in getPlans response", npe);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG + "Error : ", error.getLocalizedMessage());
            }
        });

    }

    // Our localized error class for passing errors along the event bus to ourselves.
    public static class SuccessEvent {
        private RandomJokeWrapper<RandomJoke> siRandomJokeWrapper;
        private Response response;

        private SuccessEvent(RandomJokeWrapper<RandomJoke> siRandomJokeWrapper, Response response) {
            this.siRandomJokeWrapper = siRandomJokeWrapper;
            this.response = response;
        }

        public RandomJokeWrapper<RandomJoke> getSiRandomJokeWrapper() {
            return siRandomJokeWrapper;
        }

        public Response getResponse() {
            return response;
        }
    }

}
