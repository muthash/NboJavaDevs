package com.muthama.nbojavadevs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GithubUsersResponse {
    @SerializedName("items")
    private ArrayList<GithubUsers> users;

    public ArrayList<GithubUsers> getUsers(){
        return users;
    }
}
