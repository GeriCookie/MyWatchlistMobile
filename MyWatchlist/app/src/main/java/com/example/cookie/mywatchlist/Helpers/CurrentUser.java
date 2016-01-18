package com.example.cookie.mywatchlist.Helpers;

public class CurrentUser {
    private static Long id;

    public static void setId(Long anotherId) {
        id = anotherId;
    }

    public static Long getId() {
        return id;
    }
}
