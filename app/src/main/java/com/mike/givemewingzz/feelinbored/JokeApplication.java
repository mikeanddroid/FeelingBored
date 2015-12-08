package com.mike.givemewingzz.feelinbored;

import android.app.Application;

import com.mike.givemewingzz.feelinbored.Utils.DBHelper;

import io.realm.Realm;

/**
 * Created by GiveMeWingzz on 12/8/2015.
 */
public class JokeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.setDefaultConfiguration(DBHelper.getRealmConfig(this));
    }

}
