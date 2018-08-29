package com.example.testproject.view.activites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.testproject.GithubListApp;
import com.example.testproject.R;

import com.example.testproject.view.IAppScreens;
import com.example.testproject.view.fragments.GitHubListFragment;
import com.example.testproject.view.fragments.OriginPageFragment;


import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends MvpAppCompatActivity implements IAppScreens {
    private SupportAppNavigator navigator = new SupportAppNavigator(this, R.id.layout_fragment_container) {
        @Override
        protected Intent createActivityIntent(Context context, String screenKey, Object data) {
            return null;
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey){
                case LIST_SCREEN_KEY: return new GitHubListFragment();

                case ORIGIN_SCREEN_KEY: return new OriginPageFragment();

                default: throw  new RuntimeException("WRONG SCREEN KEY");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().popBackStack();

            navigator.applyCommands(new Command[]{new Replace(LIST_SCREEN_KEY, null)});
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        GithubListApp.getInstance().getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();

        GithubListApp.getInstance().getNavigatorHolder().removeNavigator();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) GithubListApp.getInstance().getRouter().exit();

        else moveTaskToBack(true);
    }
}
