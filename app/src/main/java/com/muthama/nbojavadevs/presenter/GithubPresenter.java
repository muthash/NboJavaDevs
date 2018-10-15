package com.muthama.nbojavadevs.presenter;

import android.util.Log;

import com.muthama.nbojavadevs.model.GithubUsers;
import com.muthama.nbojavadevs.model.GithubUsersResponse;
import com.muthama.nbojavadevs.service.GithubService;
import com.muthama.nbojavadevs.view.UserView;
import com.muthama.util.NetworkCheck;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class GithubPresenter implements UserView.MainPresenter{

    private UserView.MainView userView;
    private GithubService githubService;

    public GithubPresenter(UserView.MainView  view) {
        this.userView = view;
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    @Override
    public void getGithubUsers() {

        githubService.getAPI().getUsers().enqueue(new Callback<GithubUsersResponse>() {
            @Override
            public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                ArrayList<GithubUsers> githubUsers = response.body().getUsers();
                if (githubUsers != null) {
                    userView.displayGithubUsers(githubUsers);
                }else {
                    userView.displaySnackBar(true);
                }
            }
            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());

                userView.displaySnackBar(false);
            }
        });

    }

    @Override
    public boolean getNetworkConnectionState() {

        return NetworkCheck.getConnectionStatus(userView.getViewContext());
    }
}