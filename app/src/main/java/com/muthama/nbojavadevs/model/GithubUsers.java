package com.muthama.nbojavadevs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {

    @SerializedName("avatar_url")
    private String imageUrl;

    @SerializedName("login")
    private String username;

    @SerializedName("html_url")
    private String profileUrl;

    protected GithubUsers(Parcel in) {
        username = in.readString();
        imageUrl = in.readString();
        profileUrl = in.readString();

    }

    public static final Parcelable.Creator<GithubUsers> CREATOR = new Parcelable.Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileUrl() { return profileUrl; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(imageUrl);
        parcel.writeString(profileUrl);
    }
}
