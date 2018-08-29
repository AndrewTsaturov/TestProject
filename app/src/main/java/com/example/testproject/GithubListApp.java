package com.example.testproject;

import android.app.Application;

import com.example.testproject.model.AppModel;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by Андрей on 21.02.2018.
 */

public class GithubListApp extends Application {
    private static GithubListApp instance;

    private static AppModel appModel;

    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();

        if(appModel == null) appModel = new AppModel();

        initCicerone();

        instance = this;
    }

    public static GithubListApp getInstance(){
        return instance;
    }

    public static AppModel getAppModel() {
        return appModel;
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }
}
