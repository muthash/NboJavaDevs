package com.muthama.nbojavadevs.service;

import com.muthama.nbojavadevs.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {
    @GET("search/users?q=language:java+location:nairobi")
    Call<GithubUsersResponse> getUsers();
}
