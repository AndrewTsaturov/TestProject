package com.example.testproject;

import android.app.Application;

import com.example.testproject.model.AppModel;

/**
 * Created by Андрей on 21.02.2018.
 */

public class GithubListApp extends Application {
    private static AppModel appModel;

    @Override
    public void onCreate() {
        super.onCreate();

        if(appModel == null) appModel = new AppModel();
    }

    public static AppModel getAppModel() {
        return appModel;
    }
}
