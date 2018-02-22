package com.example.testproject.presenter;

import com.example.testproject.view.IAppView;

/**
 * Created by Дом on 22.02.2018.
 */

public class AppPresenter implements IAppPresenter {

    private IAppView view;

    public AppPresenter(IAppView view) {
        this.view = view;
    }

    @Override
    public void onAppStarted() {
        view.showGithubListScreen();
    }

    @Override
    public void onBackButtonPressed() {
        if (view.getScreenID() != 0) view.showGithubListScreen();
        else view.stopView();
    }
}
