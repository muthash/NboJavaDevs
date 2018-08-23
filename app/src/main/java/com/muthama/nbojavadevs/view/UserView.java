package com.muthama.nbojavadevs.view;

import android.content.Context;

import com.muthama.nbojavadevs.model.GithubUsers;

import java.util.ArrayList;

public interface UserView {
    interface MainView {
        void displayGithubUsers(ArrayList<GithubUsers> userList);

        void dismissDialog(String fetchStatus);

        void displaySnackBar(boolean networkStatus);

        Context getViewContext();
    }
    interface MainPresenter {
        void getGithubUsers();

        boolean getNetworkConnectionState();
    }
}
