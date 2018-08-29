package com.example.testproject.model;

import android.util.Log;

import com.example.testproject.model.pojo.ShowingItem;
import com.example.testproject.model.retrofit2_json_utils.GitHubRepo;
import com.example.testproject.model.retrofit2_json_utils.RetrofitJsonService;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Дом on 21.02.2018.
 */

public class AppModel implements IAppModel {
    String TAG = "AppModel";

    private String showingLink = "";
    private ArrayList<ShowingItem> showingItems;

    public AppModel() {
        showingItems = new ArrayList<>();
    }

    @Override
    public RetrofitJsonService getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(TARGET_BASE_URL).
                client(new OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();

        return retrofit.create(RetrofitJsonService.class);
    }

    @Override
    public ArrayList<ShowingItem> getItemsForShow() {
        return showingItems;
    }

    @Override
    public void convertReposToListItems(ArrayList<GitHubRepo> repos) {
        showingItems.clear();

        Log.d(TAG, "response size + " + repos.size());

        if(repos.size() > 0) {
            for (int i = 0; i < repos.size(); i++) {
                 ShowingItem showingItem = new ShowingItem();
                 showingItem.setId(repos.get(i).getId());
                 showingItem.setName(repos.get(i).getName());
                 showingItem.setOwnerLogin(repos.get(i).getOwner().getOwnerLogin());
                 showingItem.setDescription(repos.get(i).getDescription());
                 showingItem.setLink(repos.get(i).getLink());

                 showingItems.add(showingItem);
            }
        }

    }

    @Override
    public void setShowingLink(String link) {
        showingLink = link;
    }

    @Override
    public String getShowingLink() {
        return showingLink;
    }

    @Override
    public String provideTargetUrl() {
        return TARGET_URL;
    }
}
