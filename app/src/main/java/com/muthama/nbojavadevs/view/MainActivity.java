package com.muthama.nbojavadevs.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.muthama.nbojavadevs.R;
import com.muthama.nbojavadevs.adapter.GithubAdapter;
import com.muthama.nbojavadevs.model.GithubUsers;
import com.muthama.nbojavadevs.presenter.GithubPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserView.MainView {

    ArrayList<GithubUsers> users;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static final String USERS_LIST_KEY = "users_list";
    UserView.MainPresenter presenter = new GithubPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerv_view);
        mRecyclerView.setHasFixedSize(true);

        if (savedInstanceState != null) {
            users = savedInstanceState.getParcelableArrayList(USERS_LIST_KEY);
            this.displayGithubUsers(users);
        }
        else {
            presenter.getGithubUsers();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(USERS_LIST_KEY, users);
    }

    public void displayGithubUsers(ArrayList<GithubUsers> userList){
        users = userList;
        mLayoutManager =  new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new GithubAdapter(users, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void dismissDialog(String fetchStatus) {

    }

    @Override
    public void displaySnackBar(boolean networkStatus) {

    }

    @Override
    public Context getViewContext() {
        return null;
    }
}
