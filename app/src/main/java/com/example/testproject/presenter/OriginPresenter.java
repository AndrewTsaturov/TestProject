package com.example.testproject.presenter;

import com.example.testproject.GithubListApp;
import com.example.testproject.model.AppModel;
import com.example.testproject.view.IOriginView;

/**
 * Created by Дом on 22.02.2018.
 */

public class OriginPresenter implements IOriginPresenter {

    private AppModel model;

    private IOriginView view;

    public OriginPresenter(IOriginView view) {
        model = GithubListApp.getAppModel();

        this.view = view;
    }

    @Override
    public void onViewStarted() {
       view.showPage(model.getShowingLink());
    }

    @Override
    public void shareLink() {
       view.showShareDialog(model.getShowingLink());
    }
}
