package com.example.testproject.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testproject.R;
import com.example.testproject.presenter.IOriginPresenter;
import com.example.testproject.presenter.OriginPresenter;
import com.example.testproject.view.IOriginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Дом on 21.02.2018.
 */

public class OriginPageFragment extends MvpAppCompatFragment implements IOriginView{
    //TODO --> меню с кнопокй шаринга

    @BindView(R.id.web_view_origin) WebView webViewOrigin;

    @InjectPresenter
    OriginPresenter presenter;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_share) presenter.shareLink();

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_github_origin_page, container, false);

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

    private void init(){
        webViewOrigin.setWebViewClient(new WebViewClient());
        webViewOrigin.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void showPage(String link) {
        webViewOrigin.loadUrl(link);
    }

    @Override
    public void showShareDialog(String linkToShare) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, linkToShare);

        startActivity(Intent.createChooser(share, getString(R.string.share_dialog_title)));
    }


}
