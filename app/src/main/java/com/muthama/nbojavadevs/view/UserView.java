package com.muthama.nbojavadevs.view;

import android.content.Context;

import com.muthama.nbojavadevs.model.GithubUsers;

import java.util.ArrayList;

public interface UserView {
    interface MainView {
        void displayGithubUsers(ArrayList<GithubUsers> userList);

        void dismissDialog(String fetchStatus);
    }
    interface MainPresenter {
        void getGithubUsers();

        boolean getNetworkConnectionState();
    }
}
