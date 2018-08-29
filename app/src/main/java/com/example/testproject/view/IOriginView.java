package com.example.testproject.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Андрей on 21.02.2018.
 */

public interface IOriginView extends MvpView {
    void showPage(String link);
    void showShareDialog(String linkToShare);
}
