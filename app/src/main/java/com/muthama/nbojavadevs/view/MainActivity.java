package com.muthama.nbojavadevs.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.muthama.nbojavadevs.R;
import com.muthama.nbojavadevs.adapter.GithubAdapter;
import com.muthama.nbojavadevs.model.GithubUsers;
import com.muthama.nbojavadevs.presenter.GithubPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserView.MainView {


    private final String USERS_LIST_KEY = "users_list";
    private RecyclerView mRecyclerView;
    private static Bundle mBundleRecyclerViewState;
    private GithubAdapter adapter;
    private ArrayList<GithubUsers> users;
    ProgressDialog progressDialog;

    private SwipeRefreshLayout swipeRefreshLayout;
    UserView.MainPresenter presenter = new GithubPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);


//        mBundleRecyclerViewState = savedInstanceState;
//
//        if (mBundleRecyclerViewState != null && mBundleRecyclerViewState.containsKey(USERS_LIST_KEY)){
//
//            restorePreviousState(); // Restore data found in the Bundle
//
//        }
        if (savedInstanceState != null) {
            users = savedInstanceState.getParcelableArrayList(USERS_LIST_KEY);
            displayGithubUsers(users);
        }else {
            // No saved data, get data from remote
            Log.d("TA", "displayGithubUsers: " + users);
            presenter.getGithubUsers();

        }

        swipeRefreshLayout = findViewById(R.id.main_content);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
                Toast.makeText(MainActivity.this, "Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> userList) {
        Log.d("TAG", "displayGithubUsers: " + userList);

        users = userList;

        // Setting up the Orientation
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        RecyclerView.Adapter adapter = new GithubAdapter(this, users);
        mRecyclerView.setAdapter(adapter);



//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.smoothScrollToPosition(0);
//        adapter.notifyDataSetChanged();

        dismissDialog("200");

    }

    @Override
    public void dismissDialog(String fetchStatus) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (swipeRefreshLayout.isRefreshing()) {
            if ("200".equalsIgnoreCase(fetchStatus)) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(this, "users updated",
                        Toast.LENGTH_LONG).show();
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }

    }

    private void fetchData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Github Users...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        presenter.getGithubUsers();
    }

    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

//    public void restorePreviousState(){
//        // restore RecyclerView state
//        if (mBundleRecyclerViewState != null){
//            Parcelable listState = mBundleRecyclerViewState.getParcelable(USERS_LIST_KEY);
//            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        mBundleRecyclerViewState = new Bundle();
//        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
//        mBundleRecyclerViewState.putParcelable(USERS_LIST_KEY, listState);
//        super.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS_LIST_KEY, users);
    }
}
