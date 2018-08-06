package com.muthama.nbojavadevs;

public class ListItem {

    private static String username;
    private static String joinDate;
    private static String repoNumber;
    private static String imageUrl;

    public ListItem(String username, String joinDate, String repoNumber, String imageUrl) {
        this.username = username;
        this.joinDate = joinDate;
        this.repoNumber = repoNumber;
        this.imageUrl = imageUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static String getJoinDate() {
        return joinDate;
    }

    public static String getRepoNumber() {
        return repoNumber;
    }

    public static String getImageUrl() {
        return imageUrl;
    }
}
