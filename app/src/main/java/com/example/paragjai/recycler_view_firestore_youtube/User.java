package com.example.paragjai.recycler_view_firestore_youtube;

public class User {

    String userName_;
    String userStatus_;

    public User(String userName, String userStatus) {
        this.userName_ = userName;
        this.userStatus_ = userStatus;
    }

    public String getUserName() {
        return userName_;
    }

    public void setUserName(String userName) {
        this.userName_ = userName;
    }

    public String getUserStatus() {
        return userStatus_;
    }

    public void setUserStatus(String userStatus)
    {
        this.userStatus_ = userStatus;
    }

}
