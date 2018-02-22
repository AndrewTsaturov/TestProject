package com.example.testproject.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testproject.R;
import com.example.testproject.model.pojo.ShowingItem;
import com.example.testproject.presenter.IListPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Дом on 21.02.2018.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.VH> {

    private ArrayList<ShowingItem> itemsToShow;

    private IListPresenter presenter;

    public RepoListAdapter() {
        itemsToShow = new ArrayList<>();
    }

    public void setPresenter(IListPresenter presenter) {
        this.presenter = presenter;
    }

    public void setItemsToShow(ArrayList<ShowingItem> itemsToShow) {
        this.itemsToShow = itemsToShow;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.rv_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setStringData(itemsToShow.get(position));
        holder.itemView.setOnClickListener(v-> presenter.onRepositoryItemClick(position));
    }

    @Override
    public int getItemCount() {
        return itemsToShow.size();
    }


    class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.rv_item_tv_id) TextView tvID;
        @BindView(R.id.rv_item_tv_repo_name) TextView tvRepoName;
        @BindView(R.id.rv_item_tv_description) TextView tvDescription;
        @BindView(R.id.rv_item_tv_owner_login) TextView tvOwnerLogin;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setStringData(ShowingItem item){
            tvID.setText(item.getId());
            tvRepoName.setText(item.getName());
            tvDescription.setText(item.getDescription());
            tvOwnerLogin.setText(item.getOwnerLogin());
        }
    }
}
