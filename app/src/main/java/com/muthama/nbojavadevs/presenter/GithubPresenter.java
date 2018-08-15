package com.muthama.nbojavadevs.presenter;

import com.muthama.nbojavadevs.model.GithubUsers;
import com.muthama.nbojavadevs.model.GithubUsersResponse;
import com.muthama.nbojavadevs.service.GithubService;
import com.muthama.nbojavadevs.view.UserView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                }
            }
            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                try {
                    throw new InterruptedException("Something went wrong!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean getNetworkConnectionState() {
        return false;
    }
}
