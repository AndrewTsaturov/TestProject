package com.example.testproject.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.testproject.GithubListApp;
import com.example.testproject.model.IAppModel;
import com.example.testproject.model.retrofit2_json_utils.GitHubRepo;
import com.example.testproject.view.IAppScreens;
import com.example.testproject.view.IRepoListView;
import java.util.ArrayList;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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


        Observable<ArrayList<GitHubRepo>> observable = model.getRetrofit().getRepositories(model.provideTargetUrl());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(gitHubRepos -> {
                    model.convertReposToListItems(gitHubRepos);

            getViewState().hideLoadingProgressBar();
            getViewState().showListOfGitHubRepositories(model.getItemsForShow());
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
