package com.mike.givemewingzz.feelinbored.Services;

import android.content.Context;
import android.util.Log;

import com.mike.givemewingzz.feelinbored.AppModel.JokeCategories;
import com.mike.givemewingzz.feelinbored.AppModel.JokeCategoriesWrapper;
import com.mike.givemewingzz.feelinbored.Utils.BaseClient;
import com.mike.givemewingzz.feelinbored.Utils.BaseRetrofitInterface;
import com.mike.givemewingzz.feelinbored.Utils.OttoHelper;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class FetchCategories {

    private static final String TAG = FetchCategories.class.getSimpleName();

    public static void call(final Context context) {

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        baseRetrofitInterface.getJokesCategory(new Callback<JokeCategoriesWrapper>() {
            @Override
            public void success(JokeCategoriesWrapper jokeCategoryWrapper, Response response) {

                Object[] objects = jokeCategoryWrapper.getValue();

                RealmList<JokeCategories> realmList = new RealmList<>();

                for (Object cat : objects) {
                    String categories = (String) cat;
                    realmList.add(new JokeCategories(categories));
                }

                try {

                    Realm realm = Realm.getInstance(context);

                    realm.beginTransaction();

                    realm.clear(JokeCategories.class);

                    if (realmList.size() > 0 && realmList != null) {
                        realm.copyToRealmOrUpdate(realmList);
                    }

                    realm.commitTransaction();

                    OttoHelper.post(new SuccessEvent(realmList, response));

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

    public static class SuccessEvent {
        private RealmList<JokeCategories> jokeCategoriesRealmList;
        private Response response;

        private SuccessEvent(RealmList<JokeCategories> jokeCategoriesRealmList, Response response) {
            this.jokeCategoriesRealmList = jokeCategoriesRealmList;
            this.response = response;
        }

        public RealmList<JokeCategories> getJokeCategoriesRealmList() {
            return jokeCategoriesRealmList;
        }

        public Response getResponse() {
            return response;
        }
    }


}
