package com.muthama.nbojavadevs.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Color;
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
import android.view.View;
import android.widget.TextView;
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
    private ArrayList<GithubUsers> users;
    ProgressDialog progressDialog;

    private SwipeRefreshLayout swipeRefreshLayout;
    UserView.MainPresenter presenter = new GithubPresenter(this);
    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);


        mBundleRecyclerViewState = savedInstanceState;

        if (mBundleRecyclerViewState != null && mBundleRecyclerViewState.containsKey(USERS_LIST_KEY)){

            restorePreviousState(); // Restore data found in the Bundle

        }else {
            if (presenter.getNetworkConnectionState()) {
                presenter.getGithubUsers();
            } else {
                displaySnackBar(false);
            }
        }

        swipeRefreshLayout = findViewById(R.id.main_content);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });
    }

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> userList) {
        users = userList;

        // Setting up the Orientation
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        RecyclerView.Adapter adapter = new GithubAdapter(this, users);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.smoothScrollToPosition(0);
        adapter.notifyDataSetChanged();

        dismissDialog("200");

    }

    @Override
    public void dismissDialog(String fetchStatus) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (swipeRefreshLayout.isRefreshing()) {
            if ("200".equalsIgnoreCase(fetchStatus)) {
                Toast.makeText(this, "users updated",
                        Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
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

    public void restorePreviousState(){
        // restore RecyclerView state
        if (mBundleRecyclerViewState != null){
            Parcelable listState = mBundleRecyclerViewState.getParcelable(USERS_LIST_KEY);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(USERS_LIST_KEY, listState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void displaySnackBar(boolean networkStatus) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
        }
        int status = R.string.no_connection;

        if (networkStatus) {
            status = R.string.failed_connection;
        }
        snackbar = Snackbar
                .make(swipeRefreshLayout, status, Snackbar.LENGTH_INDEFINITE)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.getGithubUsers();
                    }
                });

        snackbar.setActionTextColor(Color.CYAN);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }
}
