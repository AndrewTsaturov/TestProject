package com.example.testproject.view.activites;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testproject.R;
import com.example.testproject.presenter.AppPresenter;
import com.example.testproject.presenter.IAppPresenter;
import com.example.testproject.view.IAppView;
import com.example.testproject.view.fragments.GitHubListFragment;
import com.example.testproject.view.fragments.OriginPageFragment;

public class MainActivity extends AppCompatActivity implements IAppView{
    private static byte screenID = 0;

    private static GitHubListFragment listFragment = new GitHubListFragment();
    private static OriginPageFragment pageFragment = new OriginPageFragment();

    private IAppPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new AppPresenter(this);
        presenter.onAppStarted();
    }

    @Override
    public void showGithubListScreen() {
        screenID = LIST_SCREEN_ID;

        showFragment(listFragment);
    }

    @Override
    public void showRepositoryPageScreen() {
        screenID = ORIGIN_SCREEN_ID;

        showFragment(pageFragment);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackButtonPressed();
    }

    @Override
    public void stopView() {
        super.onBackPressed();
    }

    @Override
    public byte getScreenID() {
        return screenID;
    }

    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container,
                fragment).commit();
    }
}
