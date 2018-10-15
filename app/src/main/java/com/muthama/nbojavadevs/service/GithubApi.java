package com.muthama.nbojavadevs.service;

import com.muthama.nbojavadevs.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {
    @GET("search/users?q=language:java+location:nairobi&access_token=1a698cebb6b879ca1f330879b1f3d62e606cd47b")
    Call<GithubUsersResponse> getUsers();
}
