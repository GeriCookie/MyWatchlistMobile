package com.example.cookie.mywatchlist.DBModels;

public class LoggedUser {
    public Long _id;
    public Long loggedId;

    public LoggedUser() {

    }

    public LoggedUser(Long loggedId) {
        this.loggedId = loggedId;
    }

    public Long getLoggedId() {
        return this.loggedId;
    }

    public void setLoggedId(Long loggedId) {
        this.loggedId = loggedId;
    }
}
