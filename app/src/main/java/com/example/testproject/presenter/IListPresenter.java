package com.example.testproject.presenter;

import com.example.testproject.view.IAppScreens;

/**
 * Created by Андрей on 21.02.2018.
 */

public interface IListPresenter {
    void onViewStarted();
    void onRepositoryItemClick(int position);
    void onSwipeList();

    void onListEnded();
}
