package com.example.testproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.testproject.R;
import com.example.testproject.model.pojo.ShowingItem;
import com.example.testproject.model.retrofit2_json_utils.GitHubRepo;
import com.example.testproject.presenter.IListPresenter;
import com.example.testproject.presenter.ListPresenter;
import com.example.testproject.view.IAppView;
import com.example.testproject.view.IRepoListView;
import com.example.testproject.view.adapters.RepoListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Дом on 21.02.2018.
 */

public class GitHubListFragment extends Fragment implements IRepoListView{

    @BindView(R.id.swipe_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_list_of_repositories) RecyclerView repositoryList;
    @BindView(R.id.progress_bar_list) ProgressBar progressBar;

    private static int progressBarHeight;

    private LinearLayout.LayoutParams progressBarParams;

    private IListPresenter presenter;

    private RepoListAdapter adapter;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        init();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter.onViewStarted();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    private void init() {
        presenter = new ListPresenter(this);
        presenter.setMainView((IAppView) getActivity());

        progressBarParams = (LinearLayout.LayoutParams) progressBar.getLayoutParams();

        progressBarHeight = progressBarParams.height;

        adapter = new RepoListAdapter();
        adapter.setPresenter(presenter);

        repositoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        repositoryList.setAdapter(adapter);

        initPgination(repositoryList);


        swipeRefreshLayout.setOnRefreshListener(()-> {
            swipeRefreshLayout.setRefreshing(false);
            presenter.onSwipeList();
        });


    }

    private void initPgination(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();


        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if ( (visibleItemCount + firstVisibleItems) >= totalItemCount) {
                          presenter.onListEnded();
                        }
                    }
                };

        recyclerView.setOnScrollListener(scrollListener);
    }

    @Override
    public void showLoadingProgressBar() {
        progressBarParams.height = progressBarHeight;

        progressBar.setLayoutParams(progressBarParams);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgressBar() {
        progressBarParams.height = 0;

        progressBar.setLayoutParams(progressBarParams);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showListOfGitHubRepositories(ArrayList<ShowingItem> repositoryList) {
        adapter.setItemsToShow(repositoryList);
        adapter.notifyDataSetChanged();
    }



}
