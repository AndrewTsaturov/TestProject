package com.example.testproject.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.testproject.GithubListApp;
import com.example.testproject.model.AppModel;
import com.example.testproject.view.IOriginView;

/**
 * Created by Дом on 22.02.2018.
 */
@InjectViewState
public class OriginPresenter extends MvpPresenter<IOriginView> implements IOriginPresenter{

    private final AppModel model = GithubListApp.getAppModel();

    public OriginPresenter() {

    }

    @Override
    public void onViewStarted() {
       getViewState().showPage(model.getShowingLink());
    }

    @Override
    public void shareLink() {
       getViewState().showShareDialog(model.getShowingLink());
    }
}
