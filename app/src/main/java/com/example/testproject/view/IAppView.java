package com.example.testproject.view;

/**
 * Created by Андрей on 21.02.2018.
 */

public interface IAppView {
    byte LIST_SCREEN_ID = 0;
    byte ORIGIN_SCREEN_ID = 1;

    void showGithubListScreen();
    void showRepositoryPageScreen();

    void stopView();

    byte getScreenID();
}
