package com.muthama.nbojavadevs.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GithubUsersResponse {
    @SerializedName("items")
    public ArrayList<GithubUsers> users;

    public ArrayList<GithubUsers> getUsers(){
        return users;
    }
}
