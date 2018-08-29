package com.example.testproject.view;

import com.arellomobile.mvp.MvpView;
import com.example.testproject.model.pojo.ShowingItem;

import java.util.ArrayList;

/**
 * Created by Андрей on 21.02.2018.
 */

public interface IRepoListView extends MvpView {
   void showLoadingProgressBar();
   void hideLoadingProgressBar();

   void showListOfGitHubRepositories(ArrayList<ShowingItem> repositoryList);
}
