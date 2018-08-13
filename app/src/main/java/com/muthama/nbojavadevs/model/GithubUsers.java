package com.muthama.nbojavadevs.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("avatar_url")
    private String imageUrl;

    @SerializedName("login")
    private String username;

    @SerializedName("followers_url")
    private String followers;

    public GithubUsers(String imageUrl, String username, String followers) {
        this.imageUrl = imageUrl;
        this.username = username;
        this.followers = followers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getFollowers() {
        return followers;
    }
}
