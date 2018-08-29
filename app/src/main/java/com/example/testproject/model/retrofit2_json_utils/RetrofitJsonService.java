package com.example.testproject.model.retrofit2_json_utils;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Дом on 21.02.2018.
 */

public interface RetrofitJsonService {
    @GET
    Observable<ArrayList<GitHubRepo>> getRepositories(@Url String gitHubLink);
}
