package com.example.testproject.model;

import com.example.testproject.model.pojo.ShowingItem;
import com.example.testproject.model.retrofit2_json_utils.GitHubRepo;
import com.example.testproject.model.retrofit2_json_utils.RetrofitJsonService;

import java.util.ArrayList;

/**
 * Created by Андрей on 21.02.2018.
 */

public interface IAppModel {
    String TARGET_URL_FULL = "https://api.github.com/repositories?since=364";
    String TARGET_BASE_URL = "https://api.github.com";
    String TARGET_URL = "/repositories?since=364";

    RetrofitJsonService getRetrofit();

    ArrayList<ShowingItem> getItemsForShow();
    void convertReposToListItems(ArrayList<GitHubRepo> repos);

    void setShowingLink(String link);
    String getShowingLink();

    String provideTargetUrl();
}
