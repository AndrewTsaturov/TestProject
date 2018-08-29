package com.example.testproject.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.testproject.GithubListApp;
import com.example.testproject.model.IAppModel;
import com.example.testproject.model.retrofit2_json_utils.GitHubRepo;
import com.example.testproject.view.IAppScreens;
import com.example.testproject.view.IRepoListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.terrakok.cicerone.Router;

/**
 * Created by Дом on 22.02.2018.
 */

@InjectViewState
public class ListPresenter extends MvpPresenter<IRepoListView> implements IListPresenter{

    private final IAppModel model = GithubListApp.getAppModel();

    private final Router router = GithubListApp.getInstance().getRouter();

    public ListPresenter() {

    }

    @Override
    public void onViewStarted() {
        if(model.getItemsForShow().size() == 0) loadAndShow();

        else {
            getViewState().hideLoadingProgressBar();
            getViewState().showListOfGitHubRepositories(model.getItemsForShow());
        }
    }

    private void loadAndShow() {

        getViewState().showLoadingProgressBar();

        model.getRetrofit().getRepositories(model.provideTargetUrl()).enqueue(new Callback<ArrayList<GitHubRepo>>() {

            @Override
            public void onResponse(Call<ArrayList<GitHubRepo>> call,
                                   Response<ArrayList<GitHubRepo>> response) {
                model.convertReposToListItems(response.body());

                getViewState().hideLoadingProgressBar();
                getViewState().showListOfGitHubRepositories(model.getItemsForShow());
            }

            @Override
            public void onFailure(Call<ArrayList<GitHubRepo>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onRepositoryItemClick(int position) {
        model.setShowingLink(model.getItemsForShow().get(position).getLink());

        router.navigateTo(IAppScreens.ORIGIN_SCREEN_KEY);
    }

    @Override
    public void onSwipeList() {
       loadAndShow();
    }

    @Override
    public void onListEnded() {
        loadAndShow();
    }
}
