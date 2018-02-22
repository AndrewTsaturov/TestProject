package com.example.testproject.presenter;


import com.example.testproject.GithubListApp;
import com.example.testproject.model.IAppModel;
import com.example.testproject.model.retrofit2_json_utils.GitHubRepo;
import com.example.testproject.view.IAppView;
import com.example.testproject.view.IRepoListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Дом on 22.02.2018.
 */

public class ListPresenter implements IListPresenter {

    private IAppModel model;

    private IRepoListView view;

    private IAppView mainView;

    public ListPresenter(IRepoListView view) {
        model = GithubListApp.getAppModel();

        this.view = view;
    }

    @Override
    public void onViewStarted() {
        if(model.getItemsForShow().size() == 0) loadAndShow();

        else {
            view.hideLoadingProgressBar();
            view.showListOfGitHubRepositories(model.getItemsForShow());
        }
    }

    private void loadAndShow() {

        view.showLoadingProgressBar();

        model.getRetrofit().getRepositories(model.provideTargetUrl()).enqueue(new Callback<ArrayList<GitHubRepo>>() {

            @Override
            public void onResponse(Call<ArrayList<GitHubRepo>> call,
                                   Response<ArrayList<GitHubRepo>> response) {
                model.convertReposToListItems(response.body());

                view.hideLoadingProgressBar();
                view.showListOfGitHubRepositories(model.getItemsForShow());
            }

            @Override
            public void onFailure(Call<ArrayList<GitHubRepo>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onRepositoryItemClick(int position) {
        model.setShowingLink(model.getItemsForShow().get(position).getLink());

        mainView.showRepositoryPageScreen();
    }

    @Override
    public void onSwipeList() {
       loadAndShow();
    }

    @Override
    public void setMainView(IAppView appView) {
        this.mainView = appView;
    }

    @Override
    public void onListEnded() {
        loadAndShow();
    }
}
